package by.training.java.grodno.az.data.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.training.java.grodno.az.data.entities.RateView;

public class RateViewMapper implements RowMapper<RateView> {

	@Override
	public RateView mapRow(ResultSet rs, int rowNum) throws SQLException {

		int rateId = rs.getInt("rate_id");
		Double value = rs.getDouble("value");
		Double coefficientValue = rs.getDouble("coefficient_value");
		int coefficientId = rs.getInt("coefficient_id");
		int userId = rs.getInt("user_id");
		int rateLineId = rs.getInt("rate_line_id");
		int result = rs.getInt("result");

		RateView rateView = new RateView();

		rateView.setRateId(rateId);
		rateView.setValue(value);
		rateView.setCoefficientValue(coefficientValue);
		rateView.setCoefficientId(coefficientId);
		rateView.setUserId(userId);
		rateView.setRateLineId(rateLineId);
		rateView.setResult(result);

		return rateView;
	}
}
