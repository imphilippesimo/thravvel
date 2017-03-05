/**
 * 
 */
package com.thravvel.core.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thravvel.core.SpringGlobalConfig;
import com.thravvel.core.dao.contract.IAgencyDao;
import com.thravvel.core.dao.contract.IUserDao;
import com.thravvel.core.data.entities.Agency;
import com.thravvel.core.data.entities.Station;
import com.thravvel.core.data.entities.User;
import com.thravvel.core.utils.ThravvelCoreConstants;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringGlobalConfig.class }, loader = AnnotationConfigWebContextLoader.class)

public class DaoTest {

	Logger logger = Logger.getLogger(DaoTest.class);

	@Autowired
	IAgencyDao agencyDao;

	@Autowired
	IUserDao userDao;

	// @Test
	public void agencyDaoShouldNotBeNull() {

		assertNotNull(agencyDao);
	}

	// @Test
	public void createAgencyWithoutError() {
		Agency agency = new Agency("Touristique", new ArrayList<Station>());
		agencyDao.save(agency);
		assertNotNull(agencyDao.findOne(agency.getId()));

	}

	@Test
	public void createUsersWithoutError() {

		User user;
		// for (int j = 693900; j < 694000; j++) {
		// user = new User(String.valueOf(j), String.valueOf(j) + "pass",
		// ThravvelCoreConstants.FEMALE);
		// userDao.save(user);
		//
		// }

		// List<User> users = userDao.findAll();
		user = userDao.save(new User("99999", "99999pass", ThravvelCoreConstants.FEMALE));
		logger.debug(user.getConfirmationCode());

		// assertTrue(!users.isEmpty());
		assertNotNull(user);

	}
}
