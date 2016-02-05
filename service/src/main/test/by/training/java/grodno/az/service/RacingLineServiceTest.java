package by.training.java.grodno.az.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.training.java.grodno.az.data.model.RacingLine;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-spring-context.xml")
public class RacingLineServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RacingLineServiceTest.class);

	@Autowired
	private RacingLineService service;

	@Autowired
	private TestUtil util;

	@Test
	public void insertTest() {

		RacingLine racingLine = initRacingLine();

		int id = service.insert(racingLine);
		racingLine.setId(id);
		LOGGER.info("Insert {}", racingLine);

		RacingLine checkRacingLine = service.getById(id);

		Assert.assertEquals(racingLine.getHourseRacingId(), checkRacingLine.getHourseRacingId());
		Assert.assertEquals(racingLine.getParticipantId(), checkRacingLine.getParticipantId());
		Assert.assertEquals(racingLine.getResult(), checkRacingLine.getResult());

	}

	@Test
	public void updateTest() {

		RacingLine racingLine = initRacingLine();

		int id = service.insert(racingLine);
		racingLine.setId(id);
		LOGGER.info("Insert {}", racingLine);

		final int result = racingLine.getResult() + 1;

		racingLine.setResult(result);

		service.update(racingLine);

		RacingLine checkRacingLine = service.getById(id);

		Assert.assertEquals(racingLine.getResult(), checkRacingLine.getResult());

	}

	@Test
	public void deleteTest() {

		RacingLine racingLine = initRacingLine();

		int id = service.insert(racingLine);
		racingLine.setId(id);
		LOGGER.info("Insert {}", racingLine);

		service.delete(id);
		LOGGER.info("Delete {}", racingLine);

		Assert.assertNull(service.getById(id));

	}

	private RacingLine initRacingLine() {
		RacingLine racingLine = new RacingLine();
		final int hourseRacingId = 1;
		final int participantId = 1;
		final int result = util.getRandomInteger();

		racingLine.setHourseRacingId(hourseRacingId);
		racingLine.setParticipantId(participantId);
		racingLine.setResult(result);

		return racingLine;
	}

}
