package com.example.demo.security;

import com.example.demo.security.presentation.filter.JwtAuthenticationFilter;
import com.example.demo.security.presentation.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class configures authentication and authorisation
 * for the application.
 *
 * The configure method
 *   - permits all POSTs to the registration and login endpoints
 *   - requires all requests other URLs to be authenticated
 *   - sets up JWT-based authentication and authorisation
 *   - enforces sessions to be stateless (see: REST)
 *
 *  We make sure user data is securely stored
 *  by utilizing a BcryptPasswordEncoder.
 *  We don't store passwords, only hashes of passwords.
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public final static String LOGIN_PATH = "/login";
    public final static String REGISTER_PATH = "/register";

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.expiration-in-ms}")
    private Integer jwtExpirationInMs;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.POST, REGISTER_PATH).permitAll()
                .antMatchers(HttpMethod.POST, LOGIN_PATH).permitAll()
                .antMatchers(HttpMethod.POST,"mail").hasRole("gebruiker")
                .antMatchers(HttpMethod.GET,"mail/*").hasRole("gebruiker")
                .antMatchers(HttpMethod.GET,"mail/*/*").hasRole("gebruiker")
                .antMatchers(HttpMethod.POST,"account").hasRole("gebruiker")
                .antMatchers(HttpMethod.DELETE,"account").hasRole("gebruiker")
                .antMatchers(HttpMethod.GET,"account").hasRole("gebruiker")
                .antMatchers(HttpMethod.DELETE,"blocked").hasRole("gebruiker")
                .antMatchers(HttpMethod.POST,"blocked").hasRole("gebruiker")
                .antMatchers(HttpMethod.GET,"blocked").hasRole("gebruiker")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(
                        new JwtAuthenticationFilter(
                                LOGIN_PATH,
                                this.jwtSecret,
                                this.jwtExpirationInMs,
                                this.authenticationManager()
                        ),
                        UsernamePasswordAuthenticationFilter.class
                )
                .addFilter(new JwtAuthorizationFilter(this.jwtSecret, this.authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
