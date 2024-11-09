package com.alexiae.kafka.customer.infrastructure.config;

import com.alexiae.kafka.customer.domain.exception.ApiRestException;
import com.alexiae.kafka.customer.domain.exception.ErrorReason;
import com.alexiae.kafka.customer.domain.exception.ErrorSource;
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
        if (token != null) {
            if (Boolean.FALSE.equals(jwtService.validateToken(token))) {
                handleCustomException(response, ApiRestException.builder()
                        .reason(ErrorReason.UNAUTHORIZED)
                        .source(ErrorSource.BUSINESS_SERVICE)
                        .build());
                return;
            }

            List<GrantedAuthority> authorities = jwtService.getAuthoritiesFromToken(token);
            String customerId = jwtService.getCustomerIdFromToken(token);
            request.setAttribute("customerId", customerId);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    null, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private void handleCustomException(HttpServletResponse response, ApiRestException ex) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"code\": \"" + ex.getReason().getErrorCode() + "\", \"reason\": \"" + ex.getReason().name() + "\" ,\"source\": \"" + ex.getSource().name() + "\"}");
        response.getWriter().flush();
    }
}
