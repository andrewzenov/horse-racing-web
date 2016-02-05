package by.training.java.grodno.az.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.training.java.grodno.az.data.dao.RateLineDao;
import by.training.java.grodno.az.data.model.RateLine;
import by.training.java.grodno.az.service.RateLineService;

@Service
public class RateLineServiceImpl implements RateLineService {

	@Autowired
	private RateLineDao rateLineDao;

	@Override
	public RateLine getById(int id) {
		return rateLineDao.get(id);
	}

	@Override
	public int insert(RateLine entity) {
		return rateLineDao.insert(entity);
	}

	@Override
	public void update(RateLine entity) {
		rateLineDao.update(entity);
	}

	@Override
	public void insertOrUpdate(RateLine entity) {
		if (rateLineDao.get(entity.getId()) != null) {
			rateLineDao.update(entity);
		} else {
			rateLineDao.insert(entity);
		}
	}

	@Override
	public void delete(int id) {
		rateLineDao.delete(id);
	}

	@Override
	public void delete(RateLine entity) {
		rateLineDao.delete(entity);
	}

	@Override
	public List<RateLine> getAll() {
		return rateLineDao.getAll();
	}

	@Override
	public List<RateLine> getAll(String orderBy, boolean orderType) {
		List<RateLine> result = rateLineDao.getAll(orderBy, orderType);
		return result;
	}

	@Override
	public List<RateLine> getAll(Map<String, Object> atributesFinding, String orderBy, boolean type) {
		return rateLineDao.getAll(atributesFinding, orderBy, type);
	}

	@Override
	public List<RateLine> getAll(int first, int count) {
		List<RateLine> result = rateLineDao.getAll(first, count);
		return result;
	}

	@Override
	public List<RateLine> getAll(int first, int count, String orderBy, boolean orderType) {
		List<RateLine> result = rateLineDao.getAll(first, count, orderBy, orderType);
		return result;
	}

	@Override
	public List<RateLine> getAll(Map<String, Object> atributesFinding, int first, int count, String orderBy,
			boolean orderType) {
		List<RateLine> result = rateLineDao.getAll(atributesFinding, first, count, orderBy, orderType);
		return result;
	}

	@Override
	public int getCount() {
		return rateLineDao.getCount();
	}

	@Override
	public int getCount(Map<String, Object> atributesFinding) {
		return rateLineDao.getCount(atributesFinding);
	}

}
