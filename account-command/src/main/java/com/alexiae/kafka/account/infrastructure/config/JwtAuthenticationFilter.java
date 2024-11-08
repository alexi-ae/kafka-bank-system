package com.alexiae.kafka.account.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        if (token != null && jwtService.validateToken(token)) {
            String username = jwtService.getUsernameFromToken(token);
            List<GrantedAuthority> authorities = jwtService.getAuthoritiesFromToken(token);
            String customerId = jwtService.getCustomerIdFromToken(token);
            request.setAttribute("customerId", customerId);
            // Configura la autenticación en el contexto de Spring Security
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    null, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

//        if (Boolean.TRUE.equals(jwtService.validateToken(token))) {
//            // Extraer la identidad y los roles del token
//            String username = jwtService.getUsernameFromToken(token);
//            List<GrantedAuthority> authorities = jwtService.getAuthoritiesFromToken(token);
//            String customerId = jwtService.getCustomerIdFromToken(token);
//            request.setAttribute("customerId", customerId);
//
//            if (username != null) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        username, null, authorities);
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
