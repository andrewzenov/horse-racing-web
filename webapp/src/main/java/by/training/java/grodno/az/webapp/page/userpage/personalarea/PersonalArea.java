package by.training.java.grodno.az.webapp.page.userpage.personalarea;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

import by.training.java.grodno.az.data.model.User;
import by.training.java.grodno.az.service.UserService;
import by.training.java.grodno.az.webapp.app.UserSession;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;
import by.training.java.grodno.az.webapp.page.registrationpage.RegistrationPage;
import by.training.java.grodno.az.webapp.page.userpage.AddBalance;
import by.training.java.grodno.az.webapp.page.userpage.PasswordChange;

@AuthorizeInstantiation(value = { "admin", "bukmeker", "player" })
public class PersonalArea extends AbstractPage {
	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;

	public PersonalArea() {
		super();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		User user = UserSession.get().getMetaData(UserSession.USER_METADATA_KEY).getUser();

		Label loginLabel = new Label("user-login-label", getString("all.login") + ": " + user.getLogin());
		add(loginLabel);

		Label balanceLabel = new Label("balance-label",
				getString("all.balance") + " = " + userService.getById(user.getId()).getBalance());
		add(balanceLabel);
		add(new Link("user-page-link") {

			@Override
			public void onClick() {
				setResponsePage(new RegistrationPage(user));
			}
		});

		add(new Link("password-change-link") {

			@Override
			public void onClick() {
				setResponsePage(new PasswordChange(user));
			}
		});

		add(new Link("add-balance-link") {

			@Override
			public void onClick() {
				setResponsePage(new AddBalance(user));
			}
		});

	}

}
