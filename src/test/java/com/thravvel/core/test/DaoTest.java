/**
 * 
 */
package com.thravvel.core.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.PlatformTransactionManager;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.thravvel.core.DaoImplement.AgencyDaoImpl;
import com.thravvel.core.DaoInterface.IAgencyDao;
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

	// @Autowired
	// @Resource(name = "agencyDao")
	// TODO: REMOVE THIS STATIC DEPENDENCY INJECTION
	IAgencyDao agencyDao = new AgencyDaoImpl();

	EntityTransaction tx;
	@Autowired
	LocalContainerEntityManagerFactoryBean entityManagerFactory;
	@Autowired
	PlatformTransactionManager transactionManager;

	@SuppressWarnings("rawtypes")
	@Before
	public void setUpBeforeClass() {

		EntityManager em = entityManagerFactory.getObject().createEntityManager();
		try {
			((GenericDao) agencyDao).setManager(em);
			tx = em.getTransaction();

		} catch (Exception e) {
			System.err.println("Erreur mise en place de l'environement de test de la DAO avec AgencyDao");
			e.printStackTrace();
		}

	}

	@Test
	public void agencyDaoShouldNotBeNull() {

		assertNotNull(agencyDao);
	}

	@Test
	public void createAgencyWithoutError() {
		Agency agency = new Agency("Touristique", new ArrayList<Station>());
		try {
			tx.begin();
			agencyDao.create(agency);
			tx.commit();
		} catch (DataAccessException e) {
			System.err.println("Erreur creation nouvelle agence");
			e.printStackTrace();

		}

	}
}
