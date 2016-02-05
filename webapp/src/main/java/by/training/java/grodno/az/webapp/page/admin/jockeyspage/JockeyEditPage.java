package by.training.java.grodno.az.webapp.page.admin.jockeyspage;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.StringValidator;

import by.training.java.grodno.az.data.model.Jockey;
import by.training.java.grodno.az.service.JockeyService;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;

@AuthorizeInstantiation(value = { "admin" })
public class JockeyEditPage extends AbstractPage {
	private static final long serialVersionUID = 1L;

	@Inject
	private JockeyService jockeyService;

	private Jockey jockey;
	public JockeyEditPage() {
		super();
		this.jockey = new Jockey();
	}

	public JockeyEditPage(Jockey jockey) {
		super();
		this.jockey = jockey;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Jockey> form = new Form<>("jockey-edit-form", new CompoundPropertyModel<>(jockey));
		add(form);

		TextField<String> fNameTextField = new TextField<String>("firstName");
		fNameTextField.setRequired(true);
		fNameTextField.add(StringValidator.maximumLength(45));
		form.add(fNameTextField);

		TextField<String> surnameTextField = new TextField<String>("lastName");
		surnameTextField.setRequired(true);
		surnameTextField.add(StringValidator.maximumLength(45));
		form.add(surnameTextField);

		form.add(new SubmitLink("jockey-submit-button") {
			@Override
			public void onSubmit() {

				jockeyService.insertOrUpdate(jockey);
				JockeyEditPage editPage = new JockeyEditPage();
				editPage.info(getString("all.data.saved"));
				setResponsePage(editPage);

			};
		});
		add(new Link<Void>("jockeys-page-link") {
			@Override
			public void onClick() {
				setResponsePage(JockeysPage.class);
			}
		});
	}

}
