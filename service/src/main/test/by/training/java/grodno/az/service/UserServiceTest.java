package by.training.java.grodno.az.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.training.java.grodno.az.data.model.User;
import by.training.java.grodno.az.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-spring-context.xml")
public class UserServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

	@Autowired
	private UserService service;

	@Autowired
	private TestUtil util;

	@Test
	public void insertTest() {

		User user = initUser();

		int id = service.insert(user);
		user.setId(id);
		LOGGER.info("Insert {}", user);

		User checkUser = service.getById(id);

		Assert.assertEquals(user.getLogin(), checkUser.getLogin());
		Assert.assertEquals(user.getPassword(), checkUser.getPassword());
		Assert.assertEquals(user.getFirstName(), checkUser.getFirstName());
		Assert.assertEquals(user.getLastName(), checkUser.getLastName());
		Assert.assertEquals(user.getCreateDate(), checkUser.getCreateDate());
//		Assert.assertEquals(user.getEndDate(), checkUser.getEndDate());
		Assert.assertEquals(user.getBalance(), checkUser.getBalance(), 0.000);
		Assert.assertEquals(user.getEmail(), checkUser.getEmail());
		Assert.assertEquals(user.getRole(), checkUser.getRole());
		
		// For getAll
		System.out.println(service.getAll());
		System.out.println(service.getAll("id", true));
		System.out.println(service.getAll(null, "id", false));
	}
	
//	@Test
//	public void deleteTest() {
//
//		User user = initUser();
//
//		int id = service.insert(user);
//		user.setId(id);
//		LOGGER.info("Insert {}", user);
//
//		service.delete(id);
//		LOGGER.info("Delete {}", user);
//
//		Assert.assertNull(service.getById(id));
//
//	}
//
//	@Test
//	public void updateTest() {
//
//		User user = initUser();
//
//		int id = service.insert(user);
//		user.setId(id);
//		LOGGER.info("Insert {}", user);
//
//		final String firstName = util.getRandomString(15);
//		final String lastName = util.getRandomString(15);
//		final String login = util.getRandomString(15);
//		final String password = util.getRandomString(15);
//		final String email = util.getRandomString(15);
//		final String role = util.getRandomString(15);
//		final double balance = util.getRandomDouble();
//		final Date createDate = new Date();
//		final Date endDate = new Date();
//
//		user.setFirstName(firstName);
//		user.setLastName(lastName);
//		user.setLogin(login);
//		user.setPassword(new UserServiceImpl().encryption(password));
//		user.setBalance(balance);
//		user.setEmail(email);
//		user.setRole(role);
//		user.setCreateDate(createDate);
//		user.setEndDate(endDate);
//
//		service.update(user);
//
//		User checkUser = service.getById(id);
//
//		Assert.assertEquals(checkUser.getLogin(), login);
//		Assert.assertEquals(checkUser.getPassword(), new UserServiceImpl().encryption(password));
//		Assert.assertEquals(checkUser.getFirstName(), firstName);
//		Assert.assertEquals(checkUser.getLastName(), lastName);
//		Assert.assertEquals(checkUser.getBalance(), balance, 0.000);
//		Assert.assertEquals(checkUser.getEmail(), email);
//		Assert.assertEquals(checkUser.getRole(), role);
//
//	}
//
//	@Test
//	public void getByLogPasTest() {
//
//		User user = initUser();
//
//		String login = util.getRandomString(15);
//		String password = util.getRandomString(15);
//		user.setLogin(login);
//		user.setPassword(new UserServiceImpl().encryption(password));
//
//		int id = service.insert(user);
//		user.setId(id);
//		LOGGER.info("Insert {}", user);
//
//		User checkUser = service.getByLogPas(login, password);
//
//		Assert.assertEquals(id, checkUser.getId());
//	}

	private User initUser() {
		User user = new User();
//		final String login = util.getRandomString(15);
//		final String password = service.encryption(util.getRandomString(15));
//		final String firstName = util.getRandomString(15);
//		final String lastName = util.getRandomString(15);
//		final String email = util.getRandomString(15);
//		final String role = util.getRandomString(15);
//		final Double balance = util.getRandomDouble();
//		final Date createDate = new Date();
//		final Date endDate = new Date();
		
		final String login = "admin";
		final String password = service.encryption("admin");
		final String firstName = "admin";
		final String lastName = "admin";
		final String email = "admin";
		final String role = "admin";
		final Double balance = 0.0;
		final Date createDate = new Date();
		final Date endDate = null;

		user.setCreateDate(createDate);
//		user.setEndDate(endDate);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setLogin(login);
		user.setPassword(password);
		user.setBalance(balance);
		user.setEmail(email);
		user.setRole(role);
		return user;
	}

}
