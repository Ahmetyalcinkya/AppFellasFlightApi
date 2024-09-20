package com.appfellas.flightApi.security;

import com.appfellas.flightApi.security.filter.JwtAuthenticationFilter;
import com.appfellas.flightApi.security.provider.JwtAuthenticationProvider;
import com.appfellas.flightApi.security.service.JwtAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationService jwtAuthenticationService;

    @Autowired
    public SecurityConfig(JwtAuthenticationService jwtAuthenticationService) {
        this.jwtAuthenticationService = jwtAuthenticationService;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(new JwtAuthenticationProvider(jwtAuthenticationService));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());
        http.authorizeHttpRequests(registry -> { // TODO : 401 all requests !!! Check this !!
            registry.requestMatchers(
                    new AntPathRequestMatcher("/AppFellas/api/v1/flight/**", HttpMethod.GET.name()),
                    new AntPathRequestMatcher("/AppFellas/api/v1/airport/**", HttpMethod.GET.name()),
                    new AntPathRequestMatcher("/AppFellas/api/v1/airline/**", HttpMethod.GET.name()),
                    new AntPathRequestMatcher("/AppFellas/api/v1/auth/**")
            ).permitAll();
//            registry.anyRequest().authenticated();
        });
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager), LogoutFilter.class);
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpStatus.UNAUTHORIZED.value(), authException.getMessage())));
        return http.build();
    }

}
