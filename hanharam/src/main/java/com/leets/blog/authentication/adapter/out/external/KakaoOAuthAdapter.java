package com.leets.blog.authentication.adapter.out.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leets.blog.authentication.application.port.out.VerifyKakaoOAuthPort;
import com.leets.blog.authentication.application.port.out.dto.KakaoOAuthUserInfo;
import com.leets.blog.authentication.domain.exception.AuthenticationDomainException;
import com.leets.blog.authentication.domain.exception.AuthenticationErrorCode;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class KakaoOAuthAdapter implements VerifyKakaoOAuthPort {

    private static final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    private final RestClient restClient;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;

    public KakaoOAuthAdapter(
            RestClient.Builder restClientBuilder,
            @Value("${app.oauth.kakao.client-id:}") String clientId,
            @Value("${app.oauth.kakao.client-secret:}") String clientSecret,
            @Value("${app.oauth.kakao.redirect-uri:}") String redirectUri
    ) {
        this.restClient = restClientBuilder.build();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

    @Override
    public KakaoOAuthUserInfo verifyAuthorizationCode(String authorizationCode) {
        validateKakaoConfig();

        String accessToken = exchangeAuthorizationCode(authorizationCode);
        KakaoUserResponse userResponse = getUserInfo(accessToken);

        if (userResponse == null || userResponse.id() == null) {
            throw new AuthenticationDomainException(AuthenticationErrorCode.OAUTH_TOKEN_VERIFICATION_FAILED);
        }

        String email = null;
        String nickname = null;

        if (userResponse.kakaoAccount() != null) {
            email = userResponse.kakaoAccount().email();

            if (userResponse.kakaoAccount().profile() != null) {
                nickname = userResponse.kakaoAccount().profile().nickname();
            }
        }

        return new KakaoOAuthUserInfo(
                String.valueOf(userResponse.id()),
                email,
                nickname
        );
    }

    private String exchangeAuthorizationCode(String authorizationCode) {
        try {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("grant_type", "authorization_code");
            formData.add("client_id", clientId);
            formData.add("redirect_uri", redirectUri);
            formData.add("code", authorizationCode);

            if (clientSecret != null && !clientSecret.isBlank()) {
                formData.add("client_secret", clientSecret);
            }

            KakaoTokenResponse response = restClient.post()
                    .uri(KAKAO_TOKEN_URL)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(formData)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        log.error("Kakao 토큰 교환 실패: status={}", res.getStatusCode());
                        throw new AuthenticationDomainException(AuthenticationErrorCode.OAUTH_TOKEN_VERIFICATION_FAILED);
                    })
                    .body(KakaoTokenResponse.class);

            if (response == null || response.accessToken() == null || response.accessToken().isBlank()) {
                throw new AuthenticationDomainException(AuthenticationErrorCode.OAUTH_TOKEN_VERIFICATION_FAILED);
            }

            return response.accessToken();
        } catch (AuthenticationDomainException e) {
            throw e;
        } catch (Exception e) {
            log.error("Kakao 토큰 교환 중 오류 발생", e);
            throw new AuthenticationDomainException(AuthenticationErrorCode.OAUTH_TOKEN_VERIFICATION_FAILED);
        }
    }

    private KakaoUserResponse getUserInfo(String accessToken) {
        try {
            return restClient.get()
                    .uri(KAKAO_USER_INFO_URL)
                    .header("Authorization", "Bearer " + accessToken)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        log.error("Kakao 사용자 정보 조회 실패: status={}", res.getStatusCode());
                        throw new AuthenticationDomainException(AuthenticationErrorCode.OAUTH_TOKEN_VERIFICATION_FAILED);
                    })
                    .body(KakaoUserResponse.class);
        } catch (AuthenticationDomainException e) {
            throw e;
        } catch (Exception e) {
            log.error("Kakao 사용자 정보 조회 중 오류 발생", e);
            throw new AuthenticationDomainException(AuthenticationErrorCode.OAUTH_TOKEN_VERIFICATION_FAILED);
        }
    }

    private void validateKakaoConfig() {
        if (clientId == null || clientId.isBlank() || redirectUri == null || redirectUri.isBlank()) {
            throw new AuthenticationDomainException(
                    AuthenticationErrorCode.OAUTH_CONFIGURATION_MISSING,
                    "카카오 OAuth 설정이 누락되었습니다."
            );
        }
    }

    private record KakaoTokenResponse(
            @JsonProperty("access_token")
            String accessToken
    ) {
    }

    private record KakaoUserResponse(
            Long id,
            @JsonProperty("kakao_account")
            KakaoAccount kakaoAccount
    ) {
    }

    private record KakaoAccount(
            String email,
            Profile profile
    ) {
    }

    private record Profile(
            String nickname
    ) {
    }
}
