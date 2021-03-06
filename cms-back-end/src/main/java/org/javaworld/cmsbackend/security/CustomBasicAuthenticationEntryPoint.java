package org.javaworld.cmsbackend.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javaworld.cmsbackend.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(CustomBasicAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
			throws IOException, ServletException {

		logger.info("statrting CustomBasicAuthenticationEntryPoint.commence()");
		logger.info("An error during authentication happened");
		logger.info("request url = " + request.getRequestURL());
		// print request headers
		logger.info("HEADERS: ");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			if (headerName.toLowerCase().equals("authorization")) {
				String[] splittedStr = headerValue.split(" ");
				headerValue = (splittedStr.length > 0) ? splittedStr[0] : "";
			}
			logger.info("	" + headerName + " = " + headerValue);
		}
		
		Response customResponse = new Response();
		customResponse.setStatus(false);
		customResponse.setMessage("invalid username or password");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(customResponse);

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");

		PrintWriter writer = response.getWriter();
		writer.println(jsonString);
	}

	public void afterPropertiesSet() throws Exception {
		setRealmName("dev");
	}

}