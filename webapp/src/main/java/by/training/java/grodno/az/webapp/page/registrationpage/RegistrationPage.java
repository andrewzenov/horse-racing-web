package by.training.java.grodno.az.webapp.page.registrationpage;

import by.training.java.grodno.az.data.model.User;
import by.training.java.grodno.az.webapp.component.registrationform.RegistrationUserForm;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;

public class RegistrationPage extends AbstractPage {
	private static final long serialVersionUID = 1L;

	private User user;

	public RegistrationPage() {

	}

	public RegistrationPage(User user) {
		this.user = user;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new RegistrationUserForm("registration-user-form", user));
	}
}
