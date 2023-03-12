package lkwoung.movie.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    @Value("Authentication")
    private String secretKey;

    private final CustomUserDetailService gv_userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    long lv_validMilliseconds = 1000L * 60 * 60 * 24;

    public String createToken(String username) {
        log.info("username : " + username);
        Claims lv_claims = Jwts.claims().setSubject(username);
        Date lv_now = new Date();

        return Jwts.builder()
                .setClaims(lv_claims)
                .setIssuer("lkwoung")
                .setIssuedAt(lv_now)
                .setExpiration(new Date(lv_now.getTime() + lv_validMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {

        UserDetails lv_userDetails = gv_userDetailsService.loadUserByUsername(this.getUsername(token));

        if (lv_userDetails != null) {
            return new UsernamePasswordAuthenticationToken(lv_userDetails, null, lv_userDetails.getAuthorities());
        } else {
            return null;
        }

    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {

        String lv_bearerToken = request.getHeader("Authentication");

        if (StringUtils.hasText(lv_bearerToken) && lv_bearerToken.startsWith("Bearer")) {
            return lv_bearerToken.substring(7);
        }

        return null;

    }

    public boolean validateToken(String token) {
        return false;
    }
}
