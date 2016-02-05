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

import by.training.java.grodno.az.data.model.Coefficient;
import by.training.java.grodno.az.data.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-spring-context.xml")
public class CoefficientServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(CoefficientServiceTest.class);

	@Autowired
	private CoefficientService service;

	@Autowired
	private TestUtil util;

	@SuppressWarnings("deprecation")
	@Test
	public void insertTest() {

		Coefficient coefficient = initCoefficient();

		int id = service.insert(coefficient);
		coefficient.setId(id);
		LOGGER.info("Insert {}", coefficient);

		Coefficient checkCoefficient = service.getById(id);
		Assert.assertEquals(coefficient.getValue(), checkCoefficient.getValue());
		Assert.assertEquals(coefficient.getRacingLineId(), checkCoefficient.getRacingLineId());
		Assert.assertEquals(coefficient.getRateLineId(), checkCoefficient.getRateLineId());

	}

	private Coefficient initCoefficient() {
		Coefficient coefficient = new Coefficient();

		final double value = util.getRandomDouble();
		final int rateLineId = 1;
		final int racingLineId = 1;

		coefficient.setValue(value);
		coefficient.setRacingLineId(racingLineId);
		coefficient.setRateLineId(rateLineId);

		return coefficient;
	}

}
