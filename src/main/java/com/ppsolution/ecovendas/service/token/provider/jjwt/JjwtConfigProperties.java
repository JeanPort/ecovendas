package com.ppsolution.ecovendas.service.token.provider.jjwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.ppsolution.ecovendas.service.token.jjwt")
public class JjwtConfigProperties {

    private final String accessTokenSigingKey;
    private final Long accessTokenExpirationInSeconds;
    private final String refreshTokenSigingKey;
    private final Long refreshTokenExpirationInSeconds;
}
