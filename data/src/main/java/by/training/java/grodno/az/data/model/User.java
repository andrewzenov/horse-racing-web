package by.training.java.grodno.az.data.model;

import java.sql.Timestamp;
import java.util.Date;

import by.training.java.grodno.az.data.entities.AbstractEntity;

public class User extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private int id;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private double balance;
	private Date createDate;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreateDate() {
		Timestamp timestamp = new Timestamp(createDate.getTime());
		timestamp.setNanos(0);
		return timestamp;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", role=" + role + ", balance=" + balance
				+ ", createDate=" + createDate + "]";
	}

}
