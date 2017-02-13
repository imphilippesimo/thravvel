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
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.thravvel.core.dao.contract.IAgencyDao;
import com.thravvel.core.entities.Agency;
import com.thravvel.core.entities.Station;
import com.thravvel.core.persistence.PersistenceConfig;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class }, loader = AnnotationConfigContextLoader.class)

public class DaoTest {

	@Autowired
	IAgencyDao agencyDao;

	@Test
	public void agencyDaoShouldNotBeNull() {

		assertNotNull(agencyDao);
	}

	@Test
	public void createAgencyWithoutError() {
		Agency agency = new Agency("Danay", new ArrayList<Station>());
		agencyDao.save(agency);
		assertNotNull(agencyDao.findOne(agency.getId()));

	}
}
