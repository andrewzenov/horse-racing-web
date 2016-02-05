package by.training.java.grodno.az.webapp.page.ratelinepage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;

import by.training.java.grodno.az.data.model.RateLine;
import by.training.java.grodno.az.service.RateLineService;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;
import by.training.java.grodno.az.webapp.page.admin.coefficientpage.CoefficientEditPage;

@AuthorizeInstantiation(value = { "admin", "bookmaker" })
public class RateLineEditPage extends AbstractPage {
	private static final long serialVersionUID = 1L;

	@Inject
	private RateLineService rateLineService;

	private RateLine rateLine;

	public RateLineEditPage() {
		super();
		this.rateLine = new RateLine();
	}

	public RateLineEditPage(RateLine rateLine) {
		super();
		this.rateLine = rateLine;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<RateLine> form = new Form<>("rate-line-edit-form", new CompoundPropertyModel<>(rateLine));
		add(form);

		TextField<String> titleTextField = new TextField<String>("title");
		titleTextField.setRequired(true);
		titleTextField.add(StringValidator.maximumLength(45));
		form.add(titleTextField);

		TextArea<String> description = new TextArea<String>("description");
		description.setRequired(true);
		description.add(StringValidator.maximumLength(1000));
		form.add(description);

		List<Position> positions = new ArrayList<>();
		int maxPosition = 7;
		for (int i = 1; i <= maxPosition; i++) {
			Position position = new Position();
			position.value = i;
			positions.add(position);
		}

		form.add(new ListView<Position>("positions-list", positions) {

			@Override
			protected void populateItem(ListItem<Position> item) {
				final Position position = item.getModelObject();
				item.add(new Label("position-number", "finishes" + String.valueOf(position.value)));
				item.add(new CheckBox("check-box-position", position.model));
			}
		});

		form.add(new SubmitLink("rate-line-submit-button") {
			@Override
			public void onSubmit() {
				StringBuilder stringBuilder = new StringBuilder();
				for (Position p : positions) {
					if (p.model.getObject()) {
						stringBuilder.append(p.value + " ");
					}
				}
				String posStr = stringBuilder.toString();
				if (posStr.length() == 0) {
					RateLineEditPage responsePage = new RateLineEditPage(rateLine);
					responsePage.warn(getString("page.loginpage.data.incorrect"));
					setResponsePage(responsePage);
				} else {
					if (rateLineService.getCount() < CoefficientEditPage.MAXQUANTITY) {
						rateLine.setPositions(posStr);
						rateLineService.insertOrUpdate(rateLine);
						AbstractPage editPage = new RateLinePage();
						editPage.info(getString("all.data.saved"));
						setResponsePage(editPage);
					} else {
						AbstractPage responsePage = new RateLineEditPage(rateLine);
						warn(getString("all.tableRecords.limit"));
						setResponsePage(responsePage);
					}
				}
			}
		});
		add(new Link<Void>("rate-line-page-link") {
			@Override
			public void onClick() {
				setResponsePage(RateLinePage.class);
			}
		});
	}

	private class Position implements Serializable {

		Position() {
			model = new Model<>(false);

		}

		private Model<Boolean> model;
		private int value;

	}

}
