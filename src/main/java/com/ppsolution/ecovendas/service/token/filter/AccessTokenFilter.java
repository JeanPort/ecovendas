package com.ppsolution.ecovendas.service.token.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppsolution.ecovendas.dto.response.ErrorResponse;
import com.ppsolution.ecovendas.service.token.TokenService;
import com.ppsolution.ecovendas.service.token.TokenServiceException;
import com.ppsolution.ecovendas.service.token.provider.jjwt.JwtBearerDefault;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AccessTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            tryDoFilterInternal(request, response, filterChain);
        }catch (TokenServiceException e){
            setResponseError(response, e);
        }

    }

    private void tryDoFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        var token = "";
        var email = "";
        var authorization = request.getHeader("Authorization");

        if (isTokenPresent(authorization)){
            token = authorization.substring(JwtBearerDefault.TOKEN_BEARER_DEFAULT.length());
            email = tokenService.getSubjectDoAccessToken(token);
        }

        if (isEmailNotInContext(email)){
            setAuthentication(request, email);
        }

        filterChain.doFilter(request, response);
    }

    private static boolean isTokenPresent(String authorization) {
        return authorization != null && authorization.startsWith(JwtBearerDefault.TOKEN_BEARER_DEFAULT);
    }

    private static boolean isEmailNotInContext(String email) {
        return email != null && !email.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private void setAuthentication(HttpServletRequest request, String email) {
        var userDetail = userDetailsService.loadUserByUsername(email);
        var authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void setResponseError(HttpServletResponse response, TokenServiceException e) throws IOException {
        var httpStatus = HttpStatus.UNAUTHORIZED;
        var body = new ErrorResponse(httpStatus.getReasonPhrase(), e.getLocalizedMessage(), httpStatus.value(), e.getClass().getSimpleName(), LocalDateTime.now());
        var json = objectMapper.writeValueAsString(body);
        response.setStatus(httpStatus.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
