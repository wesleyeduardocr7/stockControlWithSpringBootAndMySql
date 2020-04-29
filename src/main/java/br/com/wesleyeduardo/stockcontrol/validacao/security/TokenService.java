package br.com.wesleyeduardo.stockcontrol.validacao.security;

import br.com.wesleyeduardo.stockcontrol.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${wesley.jwt.expiration}")
    private String expiration;

    @Value("${wesley.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {

        User userLogged = (User) authentication.getPrincipal();

        Date today = new Date();

        Date expirationDate = new Date(today.getTime()+ Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("Wesley Eduardo")
                .setSubject(userLogged.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();

    }

    public boolean isValidToken(String token) {

        try {

            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);

            return true;

        }catch (Exception e){
            return false;
        }
    }


    public Long getIdUser(String token) {

        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }


}
