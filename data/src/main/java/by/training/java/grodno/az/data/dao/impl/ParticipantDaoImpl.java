package by.training.java.grodno.az.data.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import by.training.java.grodno.az.data.dao.ParticipantDao;
import by.training.java.grodno.az.data.dao.mapper.ParticipantViewMapper;
import by.training.java.grodno.az.data.entities.ParticipantView;
import by.training.java.grodno.az.data.model.Participant;

@Repository
public class ParticipantDaoImpl extends GenericDao<Participant> implements ParticipantDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ParticipantView> getView() {
		String sql = "select p.id,j.first_name as jockey_firstname,j.last_name as jockey_lastname,h.name as horse_name"
				+ " from participant as p join jockey as j on p.jockey_id=j.id"
				+ " join horse as h on p.horse_id=h.id order by p.id;";

		List<ParticipantView> result = jdbcTemplate.query(sql, new ParticipantViewMapper());

		return result;
	}

	@Override
	public ParticipantView getViewById(int participant_id) {

		String sql = String
				.format("select p.id,j.first_name as jockey_firstname,j.last_name as jockey_lastname,h.name as horse_name"
						+ " from participant as p join jockey as j on p.jockey_id=j.id"
						+ " join horse as h on p.horse_id=h.id where p.id= %s order by p.id;", participant_id);

		ParticipantView result = jdbcTemplate.queryForObject(sql, new ParticipantViewMapper());

		return result;
	}
}
