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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-spring-context.xml")
public class HourseServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourseServiceTest.class);

	@Autowired
	private HourseService service;

	@Autowired
	private TestUtil util;

	@Test
	public void insertTest() {

		Hourse hourse = initHourse();
		int id = service.insert(hourse);
		hourse.setId(id);
		LOGGER.info("Insert {}", hourse);

		Hourse checkHourse = service.getById(id);

		Assert.assertEquals(hourse.getName(), checkHourse.getName());
	}

	@Test
	public void updateTest() {

		Hourse hourse = initHourse();

		int id = service.insert(hourse);
		hourse.setId(id);
		LOGGER.info("Insert {}", hourse);

		final String name = util.getRandomString(18);

		hourse.setName(name);

		service.update(hourse);

		Hourse checkHourse = service.getById(id);

		Assert.assertEquals(hourse.getName(), checkHourse.getName());

	}

	@Test
	public void deleteTest() {

		Hourse hourse = initHourse();

		int id = service.insert(hourse);
		hourse.setId(id);
		LOGGER.info("Insert {}", hourse);

		service.delete(id);
		LOGGER.info("Delete {}", hourse);

		Assert.assertNull(service.getById(id));

	}

	private Hourse initHourse() {
		Hourse hourse = new Hourse();
		String name = util.getRandomString(20);
		hourse.setName(name);
		return hourse;
	}

}
