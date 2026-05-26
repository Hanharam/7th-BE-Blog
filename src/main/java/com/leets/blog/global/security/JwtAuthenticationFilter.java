package com.leets.blog.global.security;

import com.leets.blog.authentication.domain.exception.AuthenticationDomainException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String JWT_ERROR_ATTRIBUTE = "jwt.error";
    public static final String JWT_UNKNOWN_ERROR_ATTRIBUTE = "jwt.error.unknown";

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = resolveToken(request);

        if (token != null) {
            try {
                if (jwtTokenProvider.validateAccessToken(token)) {
                    Long memberId = jwtTokenProvider.parseAccessToken(token);
                    List<String> roles = jwtTokenProvider.getRolesFromAccessToken(token);

                    MemberPrincipal principal = MemberPrincipal.builder()
                            .memberId(memberId)
                            .build();

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            principal,
                            null,
                            roles.stream()
                                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                                    .toList()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (AuthenticationDomainException e) {
                request.setAttribute(JWT_ERROR_ATTRIBUTE, e);
            } catch (Exception e) {
                request.setAttribute(JWT_UNKNOWN_ERROR_ATTRIBUTE, e);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
