package by.training.java.grodno.az.data.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DaoUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DaoUtil.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public <T> T getEntity(String sql, Class<T> entityClass, Object[] parameters) {
		T result = null;
		try {
			result = (T) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper<T>(entityClass));
		} catch (Exception e) {
			LOGGER.debug("Exception: no such {} with parameters {}. Return null.", entityClass.getSimpleName(), parameters);
			return null;
		}
		return result;
	}

}
