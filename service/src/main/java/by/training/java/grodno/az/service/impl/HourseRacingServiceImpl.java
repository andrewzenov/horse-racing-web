package by.training.java.grodno.az.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.training.java.grodno.az.data.dao.HourseRacingDao;
import by.training.java.grodno.az.data.model.HorseRacing;
import by.training.java.grodno.az.service.HorseRacingService;

@Service
public class HourseRacingServiceImpl implements HorseRacingService {

	@Autowired
	private HourseRacingDao hourseRacingDao;

	@Override
	public HorseRacing getById(int id) {
		return hourseRacingDao.get(id);
	}

	@Override
	public int insert(HorseRacing entity) {
		return hourseRacingDao.insert(entity);
	}

	@Override
	public void update(HorseRacing entity) {
		hourseRacingDao.update(entity);
	}

	@Override
	public void insertOrUpdate(HorseRacing entity) {
		if (hourseRacingDao.get(entity.getId()) != null) {
			hourseRacingDao.update(entity);
		} else {
			hourseRacingDao.insert(entity);
		}
	}

	@Override
	public void delete(int id) {
		hourseRacingDao.delete(id);
	}

	@Override
	public void delete(HorseRacing entity) {
		hourseRacingDao.delete(entity);
	}

	@Override
	public List<HorseRacing> getAll() {
		return hourseRacingDao.getAll();
	}

	@Override
	public List<HorseRacing> getAll(String orderBy, boolean orderType) {
		List<HorseRacing> result = hourseRacingDao.getAll(orderBy, orderType);
		return result;
	}

	@Override
	public List<HorseRacing> getAll(Map<String, Object> atributesFinding, String orderBy, boolean type) {
		return hourseRacingDao.getAll(atributesFinding, orderBy, type);
	}

	@Override
	public List<HorseRacing> getAll(int first, int count) {
		List<HorseRacing> result = hourseRacingDao.getAll(first, count);
		return result;
	}

	@Override
	public List<HorseRacing> getAll(int first, int count, String orderBy, boolean orderType) {
		List<HorseRacing> result = hourseRacingDao.getAll(first, count, orderBy, orderType);
		return result;
	}

	@Override
	public List<HorseRacing> getAll(Map<String, Object> atributesFinding, int first, int count, String orderBy,
			boolean orderType) {
		List<HorseRacing> result = hourseRacingDao.getAll(atributesFinding, first, count, orderBy, orderType);
		return result;
	}

	@Override
	public int getCount() {
		return hourseRacingDao.getCount();
	}

	@Override
	public int getCount(Map<String, Object> atributesFinding) {
		return hourseRacingDao.getCount(atributesFinding);
	}
}
