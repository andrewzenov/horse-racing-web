package by.training.java.grodno.az.data.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import by.training.java.grodno.az.data.dao.RateDao;
import by.training.java.grodno.az.data.dao.mapper.RateViewMapper;
import by.training.java.grodno.az.data.entities.RateView;
import by.training.java.grodno.az.data.model.Rate;

@Repository
public class RateDaoImpl extends GenericDao<Rate> implements RateDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<RateView> getRateViewList(int horseRacingId) {
		String sql = String
				.format("select r.id as rate_id, r.value,r.coefficient_value as coefficient_value,r.coefficient_id as coefficient_id,r.user_id as user_id,c.rate_line_id as rate_line_id,rl.result as result"
						+ " from rate as r join coefficient as c on r.coefficient_id=c.id"
						+ " join racing_line as rl on c.racing_line_id=rl.id where rl.horse_racing_id=%s;",

						horseRacingId);
		System.out.println(sql);

		List<RateView> result = jdbcTemplate.query(sql, new RateViewMapper());

		return result;
	}
}
