package by.training.java.grodno.az.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.training.java.grodno.az.data.model.Hourse;
import by.training.java.grodno.az.data.model.Jockey;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-spring-context.xml")
public class JockeyServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(JockeyServiceTest.class);

	@Autowired
	private JockeyService service;

	@Autowired
	private TestUtil util;

	@Test
	public void insertTest() {

		Jockey jockey = initJockey();
		int id = service.insert(jockey);
		jockey.setId(id);
		LOGGER.info("Insert {}", jockey);

		Jockey checkJockey = service.getById(id);

		Assert.assertEquals(jockey.getFirstName(), checkJockey.getFirstName());
		Assert.assertEquals(jockey.getLastName(), checkJockey.getLastName());
	}

	@Test
	public void updateTest() {

		Jockey jockey = initJockey();
		int id = service.insert(jockey);
		jockey.setId(id);
		LOGGER.info("Insert {}", jockey);

		final String firstName = util.getRandomString(20);
		final String lastName = util.getRandomString(20);

		jockey.setFirstName(firstName);
		jockey.setLastName(lastName);

		service.update(jockey);

		Jockey checkJockey = service.getById(id);

		Assert.assertEquals(jockey.getFirstName(), checkJockey.getFirstName());
		Assert.assertEquals(jockey.getLastName(), checkJockey.getLastName());

	}

	@Test
	public void deleteTest() {

		Jockey jockey = initJockey();
		int id = service.insert(jockey);
		jockey.setId(id);
		LOGGER.info("Insert {}", jockey);

		service.delete(id);
		LOGGER.info("Delete {}", jockey);

		Assert.assertNull(service.getById(id));

	}

	private Jockey initJockey() {
		Jockey jockey = new Jockey();
		final String firstName = util.getRandomString(20);
		final String lastName = util.getRandomString(20);
		jockey.setFirstName(firstName);
		jockey.setLastName(lastName);
		return jockey;
	}

}
