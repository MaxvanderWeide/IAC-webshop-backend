package com.controller;


import com.model.customer.Customer;
import com.model.customer.CustomerService;
import com.model.customer.CustomerServices;
import com.service.ConfigSelector;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static String secretKey = ConfigSelector.SECRET_KEY;

    @PostMapping()
    public ResponseEntity<String> createAuthentication(@RequestParam("user") String email) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        CustomerService customerService = new CustomerServices();
        Customer account = customerService.getAccountWithEmail(email);

        if (account == null) {
            return new ResponseEntity<>("User information not found", HttpStatus.UNAUTHORIZED);
        }
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject("1")
                .setIssuer("hu-com-iac")
                .claim("username", account.getEmail())
                .claim("name", account.getFirstName() + " " + account.getLastName())
                .signWith(signatureAlgorithm, signingKey);

        long expMillis = nowMillis + 3600000;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        return new ResponseEntity<>(builder.compact(), HttpStatus.OK);
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
