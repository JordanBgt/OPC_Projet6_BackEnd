package com.openclassrooms.escalade.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handle authentication error
 *
 * @see AuthenticationEntryPoint
 */
@Component
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    /**
     * Method that will be triggered anytime an AuthenticationException is thrown : when a unauthenticated user requests
     * a secured resource. The response will return the 401 Status code.
     *
     * @param httpServletRequest Http request
     * @param httpServletResponse Http response
     * @param e AuthenticationException
     * @throws IOException exception thrown by sendError method
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {
        log.error("Défaut d'autorisation : {}", e.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erreur : non autorisé");
    }
}
