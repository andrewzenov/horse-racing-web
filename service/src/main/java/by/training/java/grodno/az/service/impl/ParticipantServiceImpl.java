package by.training.java.grodno.az.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.training.java.grodno.az.data.dao.HourseDao;
import by.training.java.grodno.az.data.dao.JockeyDao;
import by.training.java.grodno.az.data.dao.ParticipantDao;
import by.training.java.grodno.az.data.entities.ParticipantView;
import by.training.java.grodno.az.data.model.Horse;
import by.training.java.grodno.az.data.model.Jockey;
import by.training.java.grodno.az.data.model.Participant;
import by.training.java.grodno.az.service.ParticipantService;

@Service
public class ParticipantServiceImpl implements ParticipantService {

	@Autowired
	private ParticipantDao participantDao;

	@Autowired
	private HourseDao hourseDao;

	@Autowired
	private JockeyDao jockeyDao;

	@Override
	public Participant getById(int id) {
		return participantDao.get(id);
	}

	@Override
	public int insert(Participant entity) {
		return participantDao.insert(entity);
	}

	@Override
	public void update(Participant entity) {
		participantDao.update(entity);
	}

	@Override
	public void insertOrUpdate(Participant entity) {
		if (participantDao.get(entity.getId()) != null) {
			participantDao.update(entity);
		} else {
			participantDao.insert(entity);
		}
	}

	@Override
	public void delete(int id) {
		participantDao.delete(id);
	}

	@Override
	public void delete(Participant entity) {
		participantDao.delete(entity);
	}

	@Override
	public Horse getHourse(int hourse_id) {
		return hourseDao.get(hourse_id);
	}

	@Override
	public Jockey getJockey(int jockey_id) {
		return jockeyDao.get(jockey_id);
	}

	@Override
	public List<Participant> getAll() {
		return participantDao.getAll();
	}

	@Override
	public List<Participant> getAll(String orderBy, boolean orderType) {
		List<Participant> result = participantDao.getAll(orderBy, orderType);
		return result;
	}

	@Override
	public List<Participant> getAll(Map<String, Object> atributesFinding, String orderBy, boolean type) {
		return participantDao.getAll(atributesFinding, orderBy, type);
	}

	@Override
	public List<Participant> getAll(int first, int count) {
		List<Participant> result = participantDao.getAll(first, count);
		return result;
	}

	@Override
	public List<Participant> getAll(int first, int count, String orderBy, boolean orderType) {
		List<Participant> result = participantDao.getAll(first, count, orderBy, orderType);
		return result;
	}

	@Override
	public List<Participant> getAll(Map<String, Object> atributesFinding, int first, int count, String orderBy,
			boolean orderType) {
		List<Participant> result = participantDao.getAll(atributesFinding, first, count, orderBy, orderType);
		return result;
	}

	@Override
	public int getCount() {
		return participantDao.getCount();
	}

	@Override
	public List<ParticipantView> getView() {
		return participantDao.getView();
	}

	@Override
	public ParticipantView getViewById(int participant_id) {
		return participantDao.getViewById(participant_id);
	}

	@Override
	public int getCount(Map<String, Object> atributesFinding) {
		return participantDao.getCount(atributesFinding);
	}

	@Override
	@Transactional()
	public void insert(List<Participant> list) {
		for(Participant p:list){
			insertOrUpdate(p);
		}
		
	}

}
