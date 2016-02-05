package by.training.java.grodno.az.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.training.java.grodno.az.data.model.RateType;
import by.training.java.grodno.az.data.model.User;
import by.training.java.grodno.az.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-spring-context.xml")
public class RateTypeServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(RateTypeServiceTest.class);

	@Autowired
	private RateTypeService service;

	@Autowired
	private TestUtil util;

	@Test
	public void insertTest() {

		RateType rateType = initRateType();

		int id = service.insert(rateType);
		rateType.setId(id);
		LOGGER.info("Insert {}", rateType);

		RateType checkRateType = service.getById(id);

		Assert.assertEquals(rateType.getName(), checkRateType.getName());
		Assert.assertEquals(rateType.getDescription(), checkRateType.getDescription());
		
	}

	@Test
	public void updateTest() {

		RateType rateType = initRateType();

		int id = service.insert(rateType);
		rateType.setId(id);
		LOGGER.info("Insert {}", rateType);

		String name = util.getRandomString(15);
		String description = util.getRandomString(200);

		rateType.setName(name);
		rateType.setDescription(description);

		service.update(rateType);

		RateType checkRateType = service.getById(id);

		Assert.assertEquals(checkRateType.getName(), name);
		Assert.assertEquals(checkRateType.getDescription(), description);

	}

	@Test
	public void deleteTest() {

		RateType rateType = initRateType();

		int id = service.insert(rateType);
		rateType.setId(id);
		LOGGER.info("Insert {}", rateType);

		service.delete(id);
		LOGGER.info("Delete {}", rateType);

		Assert.assertNull(service.getById(id));

	}

	private RateType initRateType() {
		RateType rateType = new RateType();
		final String name = util.getRandomString(15);
		final String description = util.getRandomString(100);

		rateType.setName(name);
		rateType.setDescription(description);

		return rateType;
	}

}
