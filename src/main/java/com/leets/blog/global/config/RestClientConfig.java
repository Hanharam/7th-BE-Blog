package com.leets.blog.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean(name = "kakaoRestClient")
    public RestClient kakaoRestClient(
            RestClient.Builder restClientBuilder,
            @Value("${app.oauth.kakao.connect-timeout-millis:3000}") int connectTimeoutMillis,
            @Value("${app.oauth.kakao.read-timeout-millis:5000}") int readTimeoutMillis
    ) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeoutMillis);
        requestFactory.setReadTimeout(readTimeoutMillis);

        return restClientBuilder
                .requestFactory(requestFactory)
                .build();
    }
}
