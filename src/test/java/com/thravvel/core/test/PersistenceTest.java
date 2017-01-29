package com.thravvel.core.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.PlatformTransactionManager;

import com.thravvel.core.persistence.PersistenceConfig;

/**
 * 
 */

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class }, loader = AnnotationConfigContextLoader.class)

public class PersistenceTest {

	@Autowired
	PlatformTransactionManager transactionManager;
	// IPersonDao dao;
	// IGenericService<Person> personService;

	@Test
	public void txManagerShouldNotBeNull() {

		// assertNotNull(personService);
		assertNotNull(transactionManager);

	}

	// @Test
	// public void whenPersonEntityIsCreated_thenNoExceptions() {
	// Person person = new Person("mtd90i0ii", "Danielle", 240);
	// personService.createEntity(person);
	// // dao.save(person);
	//
	// }
	//
	// @Test
	// public void whenPersonEntityIsUpdated_thenNoExceptions() {
	// Person person = personService.getEntityById(Long.valueOf(65536));
	// // Person person = dao.getOne(32768L);
	// // System.out.println(person.toString());
	// person.setName("Majoie");
	// // dao.save(person);
	// personService.updateEntity(person, 65536L);
	//
	// }

}
