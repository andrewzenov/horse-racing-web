package by.training.java.grodno.az.webapp.page.admin.coefficientpage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.wicket.kendo.ui.markup.html.link.BookmarkablePageLink;

import by.training.java.grodno.az.data.model.Coefficient;
import by.training.java.grodno.az.data.model.HorseRacing;
import by.training.java.grodno.az.data.model.RacingLine;
import by.training.java.grodno.az.data.model.RateLine;
import by.training.java.grodno.az.service.CoefficientService;
import by.training.java.grodno.az.service.ParticipantService;
import by.training.java.grodno.az.service.RacingLineService;
import by.training.java.grodno.az.service.RateLineService;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;
import by.training.java.grodno.az.webapp.page.admin.horseracingpage.HorseRacingPage;

@AuthorizeInstantiation(value = { "admin" })
public class CoefficientEditPage extends AbstractPage {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(CoefficientEditPage.class);
	public static final int MAXQUANTITY = 7;

	@Inject
	private ParticipantService participantService;

	@Inject
	private RacingLineService racingLineService;

	@Inject
	private RateLineService rateLineService;

	@Inject
	private CoefficientService coefficientService;

	private HorseRacing horseRacing;

	public CoefficientEditPage(HorseRacing hourseRacing) {
		super();
		this.horseRacing = hourseRacing;
	}

	@SuppressWarnings("serial")
	@Override
	protected void onInitialize() {
		super.onInitialize();

		List<Coefficient> coefficientsList = new ArrayList<>();

		Map<String, Object> atributes = new HashMap<>();
		atributes.put("horseRacingId", horseRacing.getId());
		List<RacingLine> racingLineList = new ArrayList<>(racingLineService.getAll(atributes, "id", true));

		List<RateLine> rateLineList = new ArrayList<>(rateLineService.getAll(null, "id", true));
		int rateLineSize = rateLineList.size();

		List<CoefficientView> coefficientViewList = new ArrayList<>();

		for (int i = 0; i < racingLineList.size(); i++) {
			RacingLine racingline = racingLineList.get(i);
			CoefficientView coefficientView = new CoefficientView();
			int racingLineId = racingline.getId();
			coefficientView.participantName = participantService.getViewById(racingline.getParticipantId())
					.toStringShort();
			for (RateLine rateLine : rateLineList) {

				int rateLineId = rateLine.getId();
				Map<String, Object> findingAtributes = new HashMap<>();
				findingAtributes.put("rateLineId", rateLineId);
				findingAtributes.put("racingLineId", racingLineId);
				List<Coefficient> coefficients = coefficientService.getAll(findingAtributes, null, true);
				Coefficient coefficient;
				if (coefficients.size() != 0) {
					coefficient = coefficients.get(0);
				} else {
					coefficient = new Coefficient();
					coefficient.setRateLineId(rateLineId);
					coefficient.setRacingLineId(racingLineId);
				}
				coefficientsList.add(coefficient);
				coefficientView.models.put(rateLineId, new PropertyModel<>(coefficient, "value"));
			}
			coefficientViewList.add(coefficientView);
		}

		Form<Void> form = new Form<>("coefficient-edit-form");
		add(form);
		form.add(new FeedbackPanel("feedback-panel"));
		form.add(new Label("hourse-racing-title", horseRacing.toString()));

		for (int i = 0; i < CoefficientEditPage.MAXQUANTITY; i++) {
			String title = "Empty";
			if (i < rateLineSize) {
				title = rateLineList.get(i).getTitle();
			}
			form.add(new Label(String.format("title-%s", i), title));
		}

		form.add(new ListView<CoefficientView>("coefficient-list", coefficientViewList) {
			@Override
			protected void populateItem(ListItem<CoefficientView> item) {

				final CoefficientView coefficientView = item.getModelObject();
				item.add(new Label("participant", coefficientView.participantName));
				int modelsSize = coefficientView.models.size();
				for (int q = 0; q < CoefficientEditPage.MAXQUANTITY; q++) {

					if (q < modelsSize) {

						String id = String.valueOf(q);
						int key = rateLineList.get(q).getId();
						TextField<Double> field = new TextField<>(id, coefficientView.models.get(key));
						field.setRequired(true);
						field.add(new RangeValidator<Double>(1.0, 99.0));
						item.add(field);
					} else {
						TextField<Double> sleepTextField = new TextField<>(String.valueOf(q), new Model<Double>(),
								Double.class);
						sleepTextField.setVisible(false);
						item.add(sleepTextField);
					}
				}
			}
		});

		form.add(new SubmitLink("coefficient-submit-button") {
			@Override
			public void onSubmit() {
				coefficientService.insert(coefficientsList);
				LOGGER.info("Submit link. Insert list of coefficient to DB");
				CoefficientEditPage editPage = new CoefficientEditPage(horseRacing);
				editPage.info(getString("all.data.saved"));
				setResponsePage(editPage);
			};
		});

		add(new BookmarkablePageLink<Void>("hourse-racing-page-link", HorseRacingPage.class));

	}

	private class CoefficientView implements Serializable {
		private static final long serialVersionUID = 1L;

		public CoefficientView() {
			super();
			this.models = new HashMap<>();
		}

		private String participantName;
		private Map<Integer, PropertyModel<Double>> models;

	}

}
