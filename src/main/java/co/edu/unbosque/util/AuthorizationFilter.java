package co.edu.unbosque.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.ServletContextRequestLoggingFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
@Order(1)
public class AuthorizationFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(ServletContextRequestLoggingFilter.class);
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Verifica si el usuario ha iniciado sesión
        boolean isLoggedIn = false; // Lógica para verificar si el usuario ha iniciado sesión

        if (isLoggedIn) {
            // El usuario ha iniciado sesión, permite el acceso a la siguiente solicitud
            chain.doFilter(request, response);
        } else {
            // El usuario no ha iniciado sesión, redirige a otra página
            httpResponse.sendRedirect("/login.html"); // Cambia la ruta según tu estructura de archivos
        }
		
	}

}
