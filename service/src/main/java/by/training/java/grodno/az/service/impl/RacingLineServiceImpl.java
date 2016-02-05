package by.training.java.grodno.az.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.training.java.grodno.az.data.dao.RacingLineDao;
import by.training.java.grodno.az.data.entities.RacingLineView;
import by.training.java.grodno.az.data.model.HorseRacing;
import by.training.java.grodno.az.data.model.Participant;
import by.training.java.grodno.az.data.model.RacingLine;
import by.training.java.grodno.az.service.HorseRacingService;
import by.training.java.grodno.az.service.ParticipantService;
import by.training.java.grodno.az.service.RacingLineService;

@Service
public class RacingLineServiceImpl implements RacingLineService {

	@Autowired
	private RacingLineDao racingLineDao;

	@Autowired
	private HorseRacingService hourseRacingService;

	@Autowired
	private ParticipantService participantService;

	@Override
	public RacingLine getById(int id) {
		return racingLineDao.get(id);
	}

	@Override
	public int insert(RacingLine entity) {
		return racingLineDao.insert(entity);
	}

	@Override
	public void update(RacingLine entity) {
		racingLineDao.update(entity);
	}

	@Override
	public void insertOrUpdate(RacingLine entity) {
		if (racingLineDao.get(entity.getId()) != null) {
			racingLineDao.update(entity);
		} else {
			racingLineDao.insert(entity);
		}
	}

	@Override
	public void delete(int id) {
		racingLineDao.delete(id);
	}

	@Override
	public void delete(RacingLine entity) {
		racingLineDao.delete(entity);
	}

	@Override
	public HorseRacing getHourseRacing(int hourseRacing_id) {
		return hourseRacingService.getById(hourseRacing_id);
	}

	@Override
	public Participant getParticipant(int participant_id) {
		return participantService.getById(participant_id);
	}

	@Override
	public List<RacingLine> getAll() {
		return racingLineDao.getAll();
	}

	@Override
	public List<RacingLine> getAll(String orderBy, boolean orderType) {
		List<RacingLine> result = racingLineDao.getAll(orderBy, orderType);
		return result;
	}

	@Override
	public List<RacingLine> getAll(Map<String, Object> atributesFinding, String orderBy, boolean type) {
		return racingLineDao.getAll(atributesFinding, orderBy, type);
	}

	@Override
	public List<RacingLine> getAll(int first, int count) {
		List<RacingLine> result = racingLineDao.getAll(first, count);
		return result;
	}

	@Override
	public List<RacingLine> getAll(int first, int count, String orderBy, boolean orderType) {
		List<RacingLine> result = racingLineDao.getAll(first, count, orderBy, orderType);
		return result;
	}

	@Override
	public List<RacingLine> getAll(Map<String, Object> atributesFinding, int first, int count, String orderBy,
			boolean orderType) {
		List<RacingLine> result = racingLineDao.getAll(atributesFinding, first, count, orderBy, orderType);
		return result;
	}

	@Override
	public int getCount() {
		return racingLineDao.getCount();
	}

	@Override
	public RacingLineView getView(RacingLine racingLine) {
		// transaction
		RacingLineView result = new RacingLineView();
		result.setRacingLineId(racingLine.getId());
		result.setHorseRacing(hourseRacingService.getById(racingLine.getHorseRacingId()));
		result.setParticipantView(participantService.getViewById(racingLine.getParticipantId()));

		result.setRusult(racingLine.getResult());

		return result;
	}

	@Override
	public int getCount(Map<String, Object> atributesFinding) {
		return racingLineDao.getCount(atributesFinding);
	}

	@Override
	@Transactional()
	public void insert(List<RacingLine> list) {
		for (RacingLine r : list) {
			insertOrUpdate(r);
		}
	}
}
