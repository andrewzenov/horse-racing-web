package by.training.java.grodno.az.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.training.java.grodno.az.data.dao.UserDao;
import by.training.java.grodno.az.data.model.User;
import by.training.java.grodno.az.data.util.DaoUtil;

@Repository
public class UserDaoImpl extends GenericDao<User>implements UserDao {

	@Autowired
	private DaoUtil daoUtil;
	
	@Override
	public User getByLogPas(String login, String password) {
		String sql = String.format("select * from user where login=? and password=?");
		Object[] parameters=new Object[]{login,password};
		return daoUtil.getEntity(sql, User.class, parameters);
		
	}

	

}
