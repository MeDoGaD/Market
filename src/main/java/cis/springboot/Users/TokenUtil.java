package cis.springboot.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    @Value("${auth.expiration}")
    private Long EXPIRATION;

    @Value("${auth.header}")
    private String HEADER;

    @Value("${auth.secret}")
    private String SECRET_KEY;

    public String generateToken(UserDetails userDetails){
       Map<String,Object> claims=new HashMap<>();
       claims.put("sub",userDetails.getUsername());
       claims.put("created",new Date());
        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate()).
                signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis()+EXPIRATION*1000);
    }

    public String getUsernameFromToken(String token){
        Claims claims=getClaims(token);
        return claims.getSubject();
    }


    public boolean isTokenValid(UserDetails userDetails,String token){
        Claims claims=getClaims(token);
        if(claims.getSubject().equals(userDetails.getUsername())&&isTokenNotExpired(token))
        {
            return true;
        }
        else
            return false;

    }
    public boolean isTokenNotExpired(String token)
    {
        Claims claims=getClaims(token);
        if(claims.getExpiration().after(new Date()))
            return true;
        else
            return false;
    }
    public Claims getClaims(String token)
    {
        Claims claims;
        try{
            claims=Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        }
        catch (Exception ex){return null;}
    }
}
