package com.ppsolution.ecovendas.service.token.provider.jjwt;

import com.ppsolution.ecovendas.service.token.TokenService;
import com.ppsolution.ecovendas.service.token.TokenServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class JjwtTokenServiceImpl implements TokenService {

    private final JjwtConfigProperties properties;

    @Override
    public String gerarAccessToken(String subject) {

        return gerarToken(properties.getAccessTokenExpirationInSeconds(), properties.getAccessTokenSigingKey());
    }



    @Override
    public String getSubjectDoAccessToken(String accessToken) {
        return getSubject(accessToken, properties.getAccessTokenSigingKey()).getSubject();
    }



    @Override
    public String gerarRefreshToken(String subject) {
        return gerarToken(properties.getRefreshTokenExpirationInSeconds(), properties.getRefreshTokenSigingKey());
    }

    @Override
    public String getSubjectDoRefreshToken(String refreshToken) {
        return getSubject(refreshToken, properties.getRefreshTokenSigingKey()).getSubject();
    }


    private String gerarToken(Long expirationInSeconds, String sigingKey) {
        var dataHoraAtual = Instant.now();
        var dataHoraExpiration = dataHoraAtual.plusSeconds(expirationInSeconds);

        return Jwts.builder()
                .setClaims(new HashMap<String, Object>())
                .setSubject(sigingKey)
                .setIssuedAt(Date.from(dataHoraExpiration))
                .signWith(Keys.hmacShaKeyFor(properties.getAccessTokenSigingKey().getBytes()))
                .compact();
    }

    private Claims getSubject(String token, String sigingKey) {
        try {
            return tryGetClaims(token, sigingKey);
        }catch (JwtException e){
            throw new TokenServiceException(e.getLocalizedMessage());
        }

    }

    private static Claims tryGetClaims(String token, String sigingKey) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(sigingKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
