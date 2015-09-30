package com.cms.log;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class LoggerInitializer
 */
@WebServlet(value = "/LoggerInitializer", loadOnStartup = 1)
public class LoggerInitializer extends HttpServlet {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(LoggerInitializer.class);

	/**
	 * ServletConfig - servlet config object.
	 *
	 * @param config
	 *            the config
	 * @throws ServletException
	 *             the servlet exception
	 */
	public void init(final ServletConfig config) throws ServletException {
		final String realPath = config.getServletContext().getRealPath("/");
		final String log4jFile = realPath + "/WEB-INF/log4j.properties";

		PropertyConfigurator.configure(log4jFile);
		LOG.info("Application Initialized");

	}
}
