/**
 * 
 */
package com.thravvel.core.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thravvel.core.SpringGlobalConfig;
import com.thravvel.core.data.entities.Agency;
import com.thravvel.core.data.entities.Station;
import com.thravvel.core.data.entities.User;
import com.thravvel.core.service.contract.IAgencyService;
import com.thravvel.core.service.contract.IUserService;
import com.thravvel.core.utils.Exceptions.ThravvelCoreException;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringGlobalConfig.class }, loader = AnnotationConfigWebContextLoader.class)
public class ServiceTest {

	private static Logger logger = Logger.getLogger(ServiceTest.class);
	Agency agency;
	User user;

	@Autowired
	IAgencyService agencyService;

	@Autowired
	IUserService userService;

	@Before
	public void doBeforeTests() throws ThravvelCoreException {
		// agencyServiceShouldNotBeNull();
		// createAgencyWithoutErrors();
		// findAgencyByKeywordWithoutError();
		connectUser();

	}

	// @Test
	public void agencyServiceShouldNotBeNull() {

		assertNotNull(agencyService);

	}

	@Test
	public void connectUser() {
		try {
			user = userService.connect("693909", "693909pass");
		} catch (ThravvelCoreException tce) {
			logger.error(tce.getMessage().substring(tce.getMessage().lastIndexOf('-') + 1));
		} catch (Exception e) {
			logger.error("Error occured", e);
		}
	}

	@Ignore
	public void createAgencyWithoutErrors() throws ThravvelCoreException {
		try {
			Agency agency = new Agency("toulouse express", new ArrayList<Station>());
			agencyService.createOrUpdateEntity(agency);
		} catch (ThravvelCoreException tce) {
			throw tce;
		}
	}

	@Ignore
	public void findAgencyByKeywordWithoutError() throws ThravvelCoreException {
		try {
			agency = agencyService.findEntities("tou", 0, 1).getContent().get(0);
			logger.info("Retrieved agency: \n " + agency);
		} catch (ThravvelCoreException tce) {
			throw tce;
		}
	}

	// @Test
	@Ignore
	public void updateAgencyWithoutErrors() throws ThravvelCoreException {
		agency.setName("TCCE");
		try {
			agencyService.createOrUpdateEntity(agency);
		} catch (ThravvelCoreException tce) {
			throw tce;

		}
	}

	@Test
	public void updateUserWithoutErrors() throws ThravvelCoreException {

		try {
			logger.debug("is user yet confirmed ?: " + userService.isConfirmed(user.getPhoneNumber()));
			userService.setUserConfirmed(user.getPhoneNumber(), true);
			logger.debug("is user now confirmed ?: " + userService.isConfirmed(user.getPhoneNumber()));
		} catch (ThravvelCoreException tce) {
			throw tce;

		}
	}

	// @Test
	public void saveUserWithoutErrors() throws ThravvelCoreException {
		try {
			user = new User("+237690943773", "thravvel", 'M');
			userService.createOrUpdateEntity(user);

		} catch (ThravvelCoreException tce) {
			throw tce;
		}
	}

}
