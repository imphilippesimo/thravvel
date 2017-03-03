/**
 * 
 */
package com.thravvel.core.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

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

	@Autowired
	IAgencyDao agencyDao;

	@Autowired
	IUserDao userDao;

	@Test
	public void agencyDaoShouldNotBeNull() {

		assertNotNull(agencyDao);
	}

	@Test
	public void createAgencyWithoutError() {
		Agency agency = new Agency("Touristique", new ArrayList<Station>());
		agencyDao.save(agency);
		assertNotNull(agencyDao.findOne(agency.getId()));

	}

	// @Test
	public void createUserWithoutError() {
		User user = new User("6990494", "makala", ThravvelCoreConstants.FEMALE);
		userDao.save(user);
		assertNotNull(userDao.findOne(user.getId()));
		System.out.println(user.getConfirmationCode());

	}
}
