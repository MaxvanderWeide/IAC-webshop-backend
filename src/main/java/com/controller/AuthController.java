package com.controller;

import com.service.ConfigSelector;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static String secretKey = ConfigSelector.SECRET_KEY;

    @PostMapping()
    public String createAuthentication(@RequestBody String user) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject("1")
                .setIssuer("hu-com-iac")
                .claim("username", user)
                .signWith(signatureAlgorithm, signingKey);

        long expMillis = nowMillis + 3600000;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        return builder.compact();
    }

    public static Claims decodeJWT(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(jwt.split("\\s+")[1]).getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
