package by.training.java.grodno.az.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.training.java.grodno.az.data.dao.CoefficientDao;
import by.training.java.grodno.az.data.dao.RateDao;
import by.training.java.grodno.az.data.dao.RateTypeDao;
import by.training.java.grodno.az.data.entities.RateView;
import by.training.java.grodno.az.data.model.Coefficient;
import by.training.java.grodno.az.data.model.Rate;
import by.training.java.grodno.az.data.model.RateType;
import by.training.java.grodno.az.data.model.User;
import by.training.java.grodno.az.service.RateService;
import by.training.java.grodno.az.service.UserService;

@Service
public class RateServiceImpl implements RateService {

	@Autowired
	private RateDao rateDao;

	@Autowired
	private RateTypeDao rateTypeDao;

	@Autowired
	private CoefficientDao coefficientDao;

	@Autowired
	private UserService userService;

	@Override
	public Rate getById(int id) {
		return rateDao.get(id);
	}

	@Override
	public int insert(Rate entity) {
		return rateDao.insert(entity);
	}

	@Override
	public void update(Rate entity) {
		rateDao.update(entity);
	}

	@Override
	public void insertOrUpdate(Rate entity) {
		if (rateDao.get(entity.getId()) != null) {
			rateDao.update(entity);
		} else {
			rateDao.insert(entity);
		}
	}

	@Override
	public void delete(int id) {
		rateDao.delete(id);
	}

	@Override
	public void delete(Rate entity) {
		rateDao.delete(entity);
	}

	@Override
	public RateType getRateType(int rateType_id) {
		return rateTypeDao.get(rateType_id);
	}

	@Override
	public Coefficient getCoefficient(int coefficient_id) {
		return coefficientDao.get(coefficient_id);
	}

	@Override
	public List<Rate> getAll() {
		return rateDao.getAll();
	}

	@Override
	public List<Rate> getAll(String orderBy, boolean orderType) {
		List<Rate> result = rateDao.getAll(orderBy, orderType);
		return result;
	}

	@Override
	public List<Rate> getAll(Map<String, Object> atributesFinding, String orderBy, boolean type) {
		return rateDao.getAll(atributesFinding, orderBy, type);
	}

	@Override
	public List<Rate> getAll(int first, int count) {
		List<Rate> result = rateDao.getAll(first, count);
		return result;
	}

	@Override
	public List<Rate> getAll(int first, int count, String orderBy, boolean orderType) {
		List<Rate> result = rateDao.getAll(first, count, orderBy, orderType);
		return result;
	}

	@Override
	public List<Rate> getAll(Map<String, Object> atributesFinding, int first, int count, String orderBy,
			boolean orderType) {
		List<Rate> result = rateDao.getAll(atributesFinding, first, count, orderBy, orderType);
		return result;
	}

	@Override
	public int getCount() {
		return rateDao.getCount();
	}

	@Override
	public int getCount(Map<String, Object> atributesFinding) {
		return rateDao.getCount(atributesFinding);
	}

	@Transactional
	@Override
	public void doRate(Rate rate) {

		Double rateValue = rate.getValue();
		User user = userService.getById(rate.getUserId());
		Double newUserBalans = user.getBalance() - rateValue;
		user.setBalance(newUserBalans);
		rateDao.insert(rate);
		userService.update(user);

	}

	@Override
	public List<RateView> getRateViewList(int hourseRacingId) {
		return rateDao.getRateViewList(hourseRacingId);
	}
}
