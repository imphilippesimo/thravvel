/**
 * 
 */
package com.thravvel.core.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 * 
 */
public class SharedResourcesProvider {

	public static Logger logger = Logger.getLogger(SharedResourcesProvider.class);
	private static boolean singletonLoaded = Boolean.FALSE.booleanValue();
	private static Properties frontMessageCtx;

	private static SharedResourcesProvider instance;

	private static SecureRandom secureRandom;

	public static SharedResourcesProvider getInstance() {
		if (!singletonLoaded)
			instance = new SharedResourcesProvider();
		return instance;
	}

	private SharedResourcesProvider() {
		loadResources();

	}

	/**
	 * 
	 */
	private synchronized void loadResources() {
		singletonLoaded = Boolean.TRUE;
		String errormsg = null;

		try {
			secureRandom = new SecureRandom();
			frontMessageCtx = new Properties();
			frontMessageCtx.load(new FileInputStream(
					new ClassPathResource(ThravvelCoreConstants.CONTROLLER_MESSAGES_PROPERTIES_FILE).getFile()));

		} catch (FileNotFoundException fnfe) {
			errormsg = "frontMessages.properties: Properties file not found";
			logger.error(errormsg, fnfe);

		} catch (IOException ioe) {
			errormsg = "frontMessages.properties: Properties file I/O error";
			logger.error(errormsg, ioe);
		} catch (Exception e) {
			errormsg = "Error: unable to load thravvel shared resources provider";

		}

	}

	public SecureRandom getSecureRandom() {
		return secureRandom;
	}

	public Properties getFrontMessageCtx() {
		return frontMessageCtx;
	}

}
