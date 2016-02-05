package by.training.java.grodno.az.data.dao;

import by.training.java.grodno.az.data.model.User;

public interface UserDao extends Dao<User> {

	User getByLogPas(String login, String password);
	
}
