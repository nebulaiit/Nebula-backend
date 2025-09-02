package com.nebula.Nebula.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Qualifier("myUserDetailsService") // for admin panel
    private final UserDetailsService myUserDetailsService;

    @Qualifier("learnerUserDetailsService") // for tutorial site
    private final UserDetailsService learnerUserDetailsService;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    private static final String[] PUBLIC_APIS = {
            "/auth/**",
            "/auth/tutorial/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_APIS).permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/tutorial/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/tutorial").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/reference/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/reference").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/languages/**").permitAll()
                        .requestMatchers("/api/languages/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/topics/**").permitAll()
                        .requestMatchers("/api/topics/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/pages/**").permitAll()
                        .requestMatchers("/api/pages/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/courses/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/course/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/jobs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/jobs/**").authenticated()
                        .requestMatchers("/api/blogs/**").permitAll()
                        .requestMatchers("/api/community/**").permitAll()
                        .requestMatchers("/api/wishlist/**").permitAll()
                        .requestMatchers("/api/ats-score/**").permitAll()
                        .requestMatchers("/api/interview/**").permitAll()

                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JWTAuthenticationFilter(jwtTokenHelper, myUserDetailsService, learnerUserDetailsService),
                        UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider adminProvider = new DaoAuthenticationProvider();
        adminProvider.setUserDetailsService(myUserDetailsService);
        adminProvider.setPasswordEncoder(passwordEncoder());

        DaoAuthenticationProvider tutorialProvider = new DaoAuthenticationProvider();
        tutorialProvider.setUserDetailsService(learnerUserDetailsService);
        tutorialProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(Arrays.asList(adminProvider, tutorialProvider));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
                "https://nebula-admin-iota.vercel.app",
                "https://nebula-main-site.vercel.app",
                "http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
