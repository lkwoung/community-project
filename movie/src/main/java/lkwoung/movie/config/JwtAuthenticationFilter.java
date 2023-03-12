package lkwoung.movie.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider gv_jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider gv_jwtTokenProvider) {
        this.gv_jwtTokenProvider = gv_jwtTokenProvider;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String lv_token = gv_jwtTokenProvider.resolveToken((HttpServletRequest) request);

        if (lv_token != null /* && gv_jwtTokenProvider.validateToken(lv_token)*/ ) {
            Authentication lv_authentication = gv_jwtTokenProvider.getAuthentication(lv_token);
            if (lv_authentication != null && lv_authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(lv_authentication);
                log.info("SecurityContextHolder : " + SecurityContextHolder.getContext().toString());
            }
        }
        chain.doFilter(request, response);

    }
}
