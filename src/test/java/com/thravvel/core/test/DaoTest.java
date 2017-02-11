/**
 * 
 */
package com.thravvel.core.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.douwe.generic.dao.DataAccessException;
import com.thravvel.core.DaoInterface.IAgencyDao;
import com.thravvel.core.entities.Agency;
import com.thravvel.core.entities.Station;
import com.thravvel.core.persistence.PersistenceConfig;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class }, loader = AnnotationConfigContextLoader.class)
@Commit
public class DaoTest {

	@Autowired
	@Resource(name = "agencyDao")
	IAgencyDao agencyDao;

	@Test
	public void agencyDaoShouldNotBeNull() {

		assertNotNull(agencyDao);
	}

	@Test
	public void createAgencyWithoutError() {
		Agency agency = new Agency("Danay", new ArrayList<Station>());
		try {

			agencyDao.create(agency);
			assertNotNull(agencyDao.findById(agency.getId()));

		} catch (DataAccessException e) {
			System.err.println("Erreur creation nouvelle agence");
			e.printStackTrace();

		}

	}
}
