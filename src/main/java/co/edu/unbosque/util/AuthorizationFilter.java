package co.edu.unbosque.util;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.ServletContextRequestLoggingFilter;

import co.edu.unbosque.proyecto.controller.UsuarioController;
import co.edu.unbosque.proyecto.model.Usuario;
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

	@Autowired
	private UsuarioController uc;
	private static final Logger LOG = LoggerFactory.getLogger(AuthorizationFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		System.out.println("truco");

		String requestURI = httpRequest.getRequestURI();
	

//		if (isLoginRequest) {
		// El usuario ha iniciado sesión o es una solicitud de inicio de sesión, permite
		// el acceso
		chain.doFilter(request, response);
//		} else {
//			LOG.info("Acceso no autorizado");
//			// El usuario no ha iniciado sesión y no es una solicitud de inicio de sesión,
//			// redirige a otra página
//			httpResponse.sendRedirect("/login.html"); // Cambia la ruta según tu estructura de archivos
//		}

	}

}
