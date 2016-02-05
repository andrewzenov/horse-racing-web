package by.training.java.grodno.az.webapp.page.admin.horsespage;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.StringValidator;

import by.training.java.grodno.az.data.model.Horse;
import by.training.java.grodno.az.service.HorseService;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;

@AuthorizeInstantiation(value = { "admin" })
public class HorseEditPage extends AbstractPage {
	private static final long serialVersionUID = 1L;

	@Inject
	private HorseService hourseService;

	private Horse hourse;

	public HorseEditPage() {
		super();
		this.hourse = new Horse();
	}

	public HorseEditPage(Horse hourse) {
		super();
		this.hourse = hourse;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Horse> form = new Form<>("hourse-edit-form", new CompoundPropertyModel<>(hourse));
		add(form);

		TextField<String> nameTextField = new TextField<String>("name");
		nameTextField.setRequired(true);
		nameTextField.add(StringValidator.maximumLength(18));
		form.add(nameTextField);

		form.add(new SubmitLink("hourse-submit-button") {
			@Override
			public void onSubmit() {

				hourseService.insertOrUpdate(hourse);
				HorseEditPage editPage = new HorseEditPage();
				editPage.info("hourse saved");
				setResponsePage(editPage);

			};
		});
		add(new Link<Void>("hourses-page-link") {
			@Override
			public void onClick() {
				setResponsePage(HorsePage.class);
			}
		});
	}

}
