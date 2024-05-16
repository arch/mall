/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.security.jwt;

import com.mall.security.core.BearerTokenResolver;
import com.mall.security.core.JwtService;
import com.mall.security.core.TokenResolver;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter implements InitializingBean {
    private final TokenResolver tokenResolver = new BearerTokenResolver();

    private UserDetailsService userDetailsService;
    private JwtService jwtService;

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        Assert.notNull(userDetailsService, "missing UserDetailsService bean");
        Assert.notNull(jwtService, "missing JwtService bean");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = tokenResolver.resolve(request);
            if (StringUtils.hasText(token)) {
                // verify the token & get the username
                String username = jwtService.verify(token);

                // loading the user details
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // creating authentication
                UsernamePasswordAuthenticationToken authResult =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authResult.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // logging
                if (logger.isDebugEnabled()) {
                    logger.debug("Authentication success. Updating SecurityContextHolder to contain: " + authResult);
                }

                // (login success) updating the security context holder
                SecurityContextHolder.getContext().setAuthentication(authResult);
            }
        } catch (Throwable cause) {
            SecurityContextHolder.clearContext();

            // logging for debugging
            logger.error(cause.getMessage(), cause);
        }

        // continue
        filterChain.doFilter(request, response);
    }
}