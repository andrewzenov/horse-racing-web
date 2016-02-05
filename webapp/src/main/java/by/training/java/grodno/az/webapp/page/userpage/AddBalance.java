package by.training.java.grodno.az.webapp.page.userpage;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;

import by.training.java.grodno.az.data.model.User;
import by.training.java.grodno.az.service.UserService;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;

public class AddBalance extends AbstractPage {
	private static final long serialVersionUID = 1L;

	private User user;

	@Inject
	private UserService userService;

	public AddBalance(User user) {
		this.user = user;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<User> form = new Form<>("balance-form");
		add(form);

		Label loginLabel = new Label("user-login-label", getString("all.login") + ": " + user.getLogin());

		form.add(loginLabel);

		Model<Double> balance = new Model<>();

		TextField<Double> textFieldBalance = new TextField<Double>("balance", balance, Double.class);

		textFieldBalance.setRequired(true);
		textFieldBalance.add(new RangeValidator<Double>(0.0, 100000.0));
		form.add(textFieldBalance);

		form.add(new SubmitLink("submit-button") {
			@Override
			public void onSubmit() {
				user.setBalance(user.getBalance() + balance.getObject());
				userService.update(user);
				AddBalance editPage = new AddBalance(user);
				editPage.info(getString("all.data.saved"));
				setResponsePage(editPage);
			}
		});
	}

}
