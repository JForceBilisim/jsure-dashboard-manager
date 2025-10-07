package com.jforce.jsure.dashboard.manager.config;

import com.jforce.jsure.base.exceptions.rest.ApiErrorBeanController;
import com.jforce.jsure.base.security.filter.JwtXUserInfoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String SECURE_API_PATH="/api/";
    private static final String SECURE_CONTENT_API=SECURE_API_PATH+"**";
    public static final String API_LOGIN="/api/authenticate";
    private static final String SWAGGER_PATTERN="/swagger-ui/**";
    private static final String SWAGGER_PATTERN_x="/v3/**";

    private static final String [] PERMIT_PATTERNS=new String [] {API_LOGIN,SWAGGER_PATTERN_x,SWAGGER_PATTERN};

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, @Autowired ApiErrorBeanController apiBeanController) throws Exception {
        http.csrf(csrf -> csrf.disable());


        http.authorizeHttpRequests(authorize->authorize.requestMatchers(PERMIT_PATTERNS).permitAll());
        http.authorizeHttpRequests(authorize->authorize.requestMatchers(SECURE_CONTENT_API).authenticated()).addFilterBefore(authFilter(apiBeanController), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    JwtXUserInfoFilter authFilter(ApiErrorBeanController apiBeanController) {
        return new JwtXUserInfoFilter(apiBeanController);
    }

}
