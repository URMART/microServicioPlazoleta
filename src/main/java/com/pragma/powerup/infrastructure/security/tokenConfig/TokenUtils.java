package com.pragma.powerup.infrastructure.security.tokenConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

public class TokenUtils {



    private static final String ACCESS_TOKEN_SECRET = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private static final Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_00L;

    //Produce el token que sera enviado al cliente
    public static  String createToken(String nombre,String email,List<String> roles ){

        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime) ;

        Map<String,Object> extra = new HashMap<>();
        extra.put("nombre", nombre);
        extra.put("roles", roles);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();

    }


    //validar token que el cliente envia para autorizacion
    public static UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        try {
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();
            List<String> roles = claims.get("roles", ArrayList.class);

            return new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList()));
        }
        catch (JwtException e) {

            return  null;
        }


    }
}
