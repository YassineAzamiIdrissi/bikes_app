package com.yassine.practice.UtilityBeans;

import com.yassine.practice.Utility.AppAuditorAware;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {
    private final UserDetailsService userDetailsService;
    @Value("${application.cors.origins:*}")
    private List<String> allowedOrigins;

    // password encoder :
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // authentication provider :
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService
                (userDetailsService);
        authenticationProvider.setPasswordEncoder
                (passwordEncoder());
        return authenticationProvider;
    }

    // authentication manager :
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig
    ) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // app auditing :
    @Bean
    public AppAuditorAware appAuditorAware() {
        return new AppAuditorAware();
    }

    // cors filter :
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        // settings :
        config.setAllowCredentials(true);
        config.setAllowedOrigins(
                Collections.singletonList("http://localhost:4200")
        );
        config.setAllowedHeaders(
                Arrays.asList("*"));
        config.setAllowedMethods(
                Arrays.asList("*")
        );
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

