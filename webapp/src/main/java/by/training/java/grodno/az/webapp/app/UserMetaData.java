package by.training.java.grodno.az.webapp.app;

import java.io.Serializable;

import by.training.java.grodno.az.data.model.User;

public class UserMetaData implements Serializable {
	private static final long serialVersionUID = 1L;

	private User user;

	public String getFullName() {
		return String.format("%s %s", user.getFirstName(), user.getLastName());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
