package by.training.java.grodno.az.webapp.page.userpage;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.Model;

import by.training.java.grodno.az.data.model.User;
import by.training.java.grodno.az.service.UserService;
import by.training.java.grodno.az.webapp.app.UserSession;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;
import by.training.java.grodno.az.webapp.page.loginpage.LoginPage;

public class PasswordChange extends AbstractPage {
	private static final long serialVersionUID = 1L;

	private User user;

	@Inject
	private UserService userService;

	public PasswordChange(User user) {
		this.user = user;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<User> form = new Form<>("password-form");
		add(form);

		Label loginLabel = new Label("user-login-label", getString("all.login") + ": " + user.getLogin());

		form.add(loginLabel);

		Model<String> oldPasModel = new Model<>();
		Model<String> newPasModel1 = new Model<>();
		Model<String> newPasModel2 = new Model<>();

		form.add(new PasswordTextField("old-password", oldPasModel).setRequired(true));
		form.add(new PasswordTextField("new-password-1", newPasModel1).setRequired(true));
		form.add(new PasswordTextField("new-password-2", newPasModel2).setRequired(true));

		form.add(new SubmitLink("submit-button") {
			@Override
			public void onSubmit() {
				String oldPas = userService.encryption(oldPasModel.getObject());
				String newPass1 = newPasModel1.getObject();
				String newPass2 = newPasModel2.getObject();
				if (oldPas.equals(user.getPassword()) && (newPass1.equals(newPass2))) {
					user.setPassword(userService.encryption(newPass1));
					userService.update(user);

					LoginPage editPage = new LoginPage();
					editPage.info(getString("all.data.saved"));
					UserSession.get().signOut();
					setResponsePage(editPage);
				} else {
					PasswordChange editPage = new PasswordChange(user);
					editPage.warn(getString("page.loginpage.data.incorrect"));
					setResponsePage(editPage);
				}
			}
		});
	}

}
