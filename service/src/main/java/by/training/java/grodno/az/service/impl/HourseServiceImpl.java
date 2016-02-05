package by.training.java.grodno.az.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.training.java.grodno.az.data.dao.HourseDao;
import by.training.java.grodno.az.data.model.Horse;
import by.training.java.grodno.az.service.HorseService;

@Service
public class HourseServiceImpl implements HorseService {

	@Autowired
	private HourseDao hourseDao;

	@Override
	public Horse getById(int id) {
		return hourseDao.get(id);
	}

	@Override
	public int insert(Horse entity) {
		return hourseDao.insert(entity);
	}

	@Override
	public void update(Horse entity) {
		hourseDao.update(entity);
	}

	@Override
	public void insertOrUpdate(Horse entity) {
		if (hourseDao.get(entity.getId()) != null) {
			hourseDao.update(entity);
		} else {
			hourseDao.insert(entity);
		}
	}

	@Override
	public void delete(int id) {
		hourseDao.delete(id);
	}

	@Override
	public void delete(Horse entity) {
		hourseDao.delete(entity);
	}

	@Override
	public List<Horse> getAll() {
		return hourseDao.getAll();
	}

	@Override
	public List<Horse> getAll(String orderBy, boolean orderType) {
		List<Horse> result = hourseDao.getAll(orderBy, orderType);
		return result;
	}

	@Override
	public List<Horse> getAll(Map<String, Object> atributesFinding, String orderBy, boolean type) {
		return hourseDao.getAll(atributesFinding, orderBy, type);
	}

	@Override
	public List<Horse> getAll(int first, int count) {
		List<Horse> result = hourseDao.getAll(first, count);
		return result;
	}

	@Override
	public List<Horse> getAll(int first, int count, String orderBy, boolean orderType) {
		List<Horse> result = hourseDao.getAll(first, count, orderBy, orderType);
		return result;
	}

	@Override
	public List<Horse> getAll(Map<String, Object> atributesFinding, int first, int count, String orderBy,
			boolean orderType) {
		List<Horse> result = hourseDao.getAll(atributesFinding, first, count, orderBy, orderType);
		return result;
	}

	@Override
	public int getCount() {
		return hourseDao.getCount();
	}

	@Override
	public int getCount(Map<String, Object> atributesFinding) {
		return hourseDao.getCount(atributesFinding);
	}

}
