package by.training.java.grodno.az.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.training.java.grodno.az.data.model.HourseRacing;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-spring-context.xml")
public class HourseRacingServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(HourseRacingServiceTest.class);

	@Autowired
	private HourseRacingService service;

	@Autowired
	private TestUtil util;

	@Test
	public void insertTest() {

		HourseRacing hourseRacing = initHourseRacing();

		int id = service.insert(hourseRacing);
		hourseRacing.setId(id);
		LOGGER.info("Insert {}", hourseRacing);

		HourseRacing checkHourseRacing = service.getById(id);

		Assert.assertEquals(hourseRacing.getTitle(), checkHourseRacing.getTitle());
		Assert.assertEquals(hourseRacing.getDate(), checkHourseRacing.getDate());

	}

	@Test
	public void deleteTest() {

		HourseRacing hourseRacing = initHourseRacing();

		int id = service.insert(hourseRacing);
		hourseRacing.setId(id);
		LOGGER.info("Insert {}", hourseRacing);

		service.delete(id);
		LOGGER.info("Delete {}", hourseRacing);

		Assert.assertNull(service.getById(id));

	}

	@Test
	public void updateTest() {

		HourseRacing hourseRacing = initHourseRacing();

		int id = service.insert(hourseRacing);
		hourseRacing.setId(id);
		LOGGER.info("Insert {}", hourseRacing);

		final String title = util.getRandomString(20);
		final Date date = new Date();

		hourseRacing.setTitle(title);
		hourseRacing.setDate(date);

		service.update(hourseRacing);

		HourseRacing checkHourseRacing = service.getById(id);

		Assert.assertEquals(hourseRacing.getTitle(), checkHourseRacing.getTitle());
		Assert.assertEquals(hourseRacing.getDate(), checkHourseRacing.getDate());

	}

	private HourseRacing initHourseRacing() {
		HourseRacing hourseRacing = new HourseRacing();
		final String title = util.getRandomString(15);
		final Date date = new Date();
		hourseRacing.setTitle(title);
		hourseRacing.setDate(date);
		return hourseRacing;
	}

}
