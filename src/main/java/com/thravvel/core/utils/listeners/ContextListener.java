/**
 * 
 */
package com.thravvel.core.utils.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 * 
 */
public class ContextListener implements ServletContextListener {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	// @Autowired
	// private ChatLauncher chatLauncher;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext())
				.getAutowireCapableBeanFactory().autowireBean(this);

		try {

			logger.info("Starting socket.IO Server ... ");
			// chatLauncher.launch();
			logger.info("Socket.IO Server started ");

		} catch (Exception e) {
			logger.fatal("Thravvel core context initialization failed ", e);
			throw new RuntimeException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd'  ' HH'H:' mm'min:' ss's' ");
		Date shutDownDate = new Date();

		logger.info("Shutdown of Thravvel core context  at " + formater.format(shutDownDate));

	}

}
