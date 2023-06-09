package com.example.springkuzmin.config;


import com.example.springkuzmin.component.JwtAuthenticationEntryPoint;
import com.example.springkuzmin.component.SuccessLogoutHandlerImpl;
import com.example.springkuzmin.filter.ConfirmFilter;
import com.example.springkuzmin.filter.JwtRequestFilter;
import com.example.springkuzmin.filter.UserVerificationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;
    private final UserVerificationFilter userVerificationFilter;
    private final ConfirmFilter confirmFilter;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService,
                             JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                             JwtRequestFilter jwtRequestFilter,
                             UserVerificationFilter userVerificationFilter, ConfirmFilter confirmFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtRequestFilter = jwtRequestFilter;
        this.userVerificationFilter = userVerificationFilter;
        this.confirmFilter = confirmFilter;
    }
    @Bean
    SecurityFilterChain web(HttpSecurity http, SuccessLogoutHandlerImpl successLogoutHandler) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers("/auth/login", "/auth/reg", "/auth/confirm" )
                                .permitAll()
                                .requestMatchers("/currency", "/users", "/posts/images/", "/posts/")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST)
                                .hasAnyRole("ROLE_ADMIN", "ROLE_MODERN")
                                .requestMatchers(HttpMethod.PUT)
                                .hasAnyRole("ROLE_ADMIN", "ROLE_MODERN")
                                .requestMatchers(HttpMethod.DELETE)
                                .hasAnyRole("ROLE_ADMIN", "ROLE_MODERN")
                                .anyRequest().authenticated()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/auth/logout")
                                .deleteCookies("JSESSION")
                                .invalidateHttpSession(true)
                                .logoutSuccessHandler(successLogoutHandler)
                )
                .userDetailsService(userDetailsService)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(userVerificationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(confirmFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

