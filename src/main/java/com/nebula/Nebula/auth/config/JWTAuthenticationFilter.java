package com.nebula.Nebula.auth.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {


    private final UserDetailsService adminUserDetailsService;
    private final UserDetailsService learnerUserDetailsService;
    private final JWTTokenHelper jwtTokenHelper;

    public JWTAuthenticationFilter(JWTTokenHelper jwtTokenHelper, UserDetailsService adminUserDetailsService, UserDetailsService learnerUserDetailsService) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.adminUserDetailsService = adminUserDetailsService;
        this.learnerUserDetailsService = learnerUserDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        String authToken = authHeader.substring(7);

        try {
            if (authToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                String username = jwtTokenHelper.getUserNameFromToken(authToken);
                String site = jwtTokenHelper.getClaimFromToken(authToken, "site");

                UserDetailsService selectedService =
                        "TUTORIAL".equalsIgnoreCase(site) ? learnerUserDetailsService : adminUserDetailsService;

                UserDetails userDetails = selectedService.loadUserByUsername(username);

                if (jwtTokenHelper.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(request.getRemoteAddr());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
            return;
        } finally {
            filterChain.doFilter(request, response);
        }
    }}
