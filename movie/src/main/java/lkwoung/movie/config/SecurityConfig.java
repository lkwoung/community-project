package lkwoung.movie.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider gv_jwtTokenProvider;
    private final CustomAccessDeniedHandler gv_customAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .formLogin().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/lib/**").permitAll()
                .antMatchers("/api/main/**").permitAll()
                .antMatchers("/api/member/**").permitAll()
                .antMatchers("/api/movie/**").permitAll()
                .and()
                .apply(new JwtConfigurer(gv_jwtTokenProvider));
        http.cors();
        http.exceptionHandling().accessDeniedHandler(gv_customAccessDeniedHandler);

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration lv_configuration = new CorsConfiguration();
        lv_configuration.setAllowedOrigins(Collections.singletonList("*"));
        lv_configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE", "OPTIONS", "HEAD"));
        lv_configuration.setAllowedHeaders(Arrays.asList("Origin", "x-authentication-token", "X-Requested-With", "Content-Type", "Accept", "XMLHttpRequest", "Authorization"));
        UrlBasedCorsConfigurationSource lv_source = new UrlBasedCorsConfigurationSource();
        lv_source.registerCorsConfiguration("/**", lv_configuration);
        return lv_source;
    }

}
