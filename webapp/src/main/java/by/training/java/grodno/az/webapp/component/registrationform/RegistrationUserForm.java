package by.training.java.grodno.az.webapp.component.registrationform;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import by.training.java.grodno.az.data.model.User;
import by.training.java.grodno.az.service.UserService;
import by.training.java.grodno.az.webapp.app.UserSession;
import by.training.java.grodno.az.webapp.enums.Role;
import by.training.java.grodno.az.webapp.page.homepage.HomePage;
import by.training.java.grodno.az.webapp.page.registrationpage.RegistrationPage;
import by.training.java.grodno.az.webapp.page.userpage.UsersPage;

public class RegistrationUserForm extends Panel {
	private static final long serialVersionUID = 1L;

	private double startBalance = 1000.0;

	private User user;
	private boolean isNew;

	public RegistrationUserForm(String id) {
		super(id);
		this.user = new User();
		isNew = true;
		this.user.setCreateDate(new Date());
		this.user.setBalance(startBalance);
		this.user.setRole("player");
	}

	public RegistrationUserForm(String id, User user) {
		super(id);
		if (user == null) {
			this.user = new User();
			this.user.setCreateDate(new Date());
			this.user.setBalance(startBalance);
			this.user.setRole("player");
			isNew = true;
		} else {
			this.user = user;
			isNew = false;
		}
	}

	@Inject
	private UserService service;

	@SuppressWarnings("serial")
	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<User> form = new Form<>("registration-form", new CompoundPropertyModel<>(user));
		add(form);

		Label loginLabel = new Label("user-login-label", ". " + getString("all.login") + ": " + user.getLogin());

		form.add(loginLabel);
		if (isNew) {
			loginLabel.setVisible(false);
		}

		TextField<String> fNameTextField = new TextField<String>("firstName");
		fNameTextField.setRequired(true);
		fNameTextField.add(StringValidator.maximumLength(45));
		form.add(fNameTextField);

		TextField<String> surnameTextField = new TextField<String>("lastName");
		surnameTextField.setRequired(true);
		surnameTextField.add(StringValidator.maximumLength(45));
		form.add(surnameTextField);

		EmailTextField emailTextField = new EmailTextField("email");
		emailTextField.setRequired(true);
		emailTextField.add(EmailAddressValidator.getInstance());
		form.add(emailTextField);

		TextField<String> loginTextField = new TextField<String>("login");
		loginTextField.setRequired(true);
		loginTextField.add(StringValidator.maximumLength(20));
		form.add(loginTextField);

		Label passwordLabel = new Label("password-label", getString("all.password") + ":");
		PasswordTextField passwordTextField = new PasswordTextField("password");

		passwordTextField.setRequired(true);
		passwordTextField.add(StringValidator.maximumLength(40));

		form.add(passwordLabel);
		form.add(passwordTextField);

		if (!isNew) {
			loginTextField.setVisible(false);
			passwordTextField.setVisible(false);
		}

		PropertyModel<String> roleModel = new PropertyModel<String>(user, "role");
		System.out.println("roleModel=" + roleModel.getObject());
		List<String> choices = Arrays.asList(Role.admin.name().toLowerCase(), Role.player.name().toLowerCase(),
				Role.bukmeker.name().toLowerCase());
		DropDownChoice<String> dropDownChoice = new DropDownChoice<>("drop-role", roleModel, choices);
		dropDownChoice.setRequired(true);
		form.add(dropDownChoice);
		dropDownChoice.setVisible(false);

		Set<String> role = UserSession.get().getRoles();
		if (role != null) {
			if (role.toString().equalsIgnoreCase(Role.admin.name())) {
				dropDownChoice.setVisible(true);
			}
		}

		form.add(new SubmitLink("submit-button") {
			@Override
			public void onSubmit() {
				if (isNew) {
					String pass = service.encryption(user.getPassword());
					user.setPassword(pass);
				}
				user.setRole(roleModel.getObject());
				System.out.println("user=" + user);
				if (!UserSession.get().isSignedIn()) {

					if (service.insert(user) < 0) {
						RegistrationPage registrPage = new RegistrationPage(user);
						registrPage.error("login is already taken");
						user.setLogin("");
						setResponsePage(registrPage);
					} else {
						HomePage homePage = new HomePage();
						homePage.info("new user created !!!");
						setResponsePage(homePage);
					}
				} else {
					service.insertOrUpdate(user);

					RegistrationPage pageToResponse = new RegistrationPage(user);
					if (isNew) {
						pageToResponse.info("new user created !!!");
					} else {
						pageToResponse.info("user updated!!!");
					}
					setResponsePage(pageToResponse);
				}

			};
		});

		add(new UsersPageLink("users-page-link", UsersPage.class));

	}

	@AuthorizeAction(roles = { "admin" }, action = Action.RENDER)
	private class UsersPageLink extends BookmarkablePageLink<Object> {
		private static final long serialVersionUID = 1L;

		public UsersPageLink(String id, Class pageClass) {
			super(id, pageClass);
		}

	}
}
