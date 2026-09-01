package br.com.gestao.vagas.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {

    private static final Logger logger = LoggerFactory.getLogger(JWTProvider.class);

    @Value("${security.token.secret}")
    private String secretKey;

    public DecodedJWT validatedToken(String token) {
        token = token.replace("Bearer", "").trim();

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            var tokenDecoded = JWT.require(algorithm)
                    .build()
                    .verify(token);
            return tokenDecoded;
        } catch (JWTVerificationException e) {
            logger.warn("Failed to validated token JWT: {} ", e.getMessage());
            return null;
        }
    }
}
