package by.training.java.grodno.az.webapp.page.admin.coefficientpage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.training.java.grodno.az.data.model.Coefficient;
import by.training.java.grodno.az.data.model.HorseRacing;
import by.training.java.grodno.az.data.model.RacingLine;
import by.training.java.grodno.az.data.model.Rate;
import by.training.java.grodno.az.data.model.RateLine;
import by.training.java.grodno.az.service.CoefficientService;
import by.training.java.grodno.az.service.ParticipantService;
import by.training.java.grodno.az.service.RacingLineService;
import by.training.java.grodno.az.service.RateLineService;
import by.training.java.grodno.az.service.RateService;
import by.training.java.grodno.az.service.UserService;
import by.training.java.grodno.az.webapp.app.UserSession;
import by.training.java.grodno.az.webapp.links.LinkForRole;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;
import by.training.java.grodno.az.webapp.page.admin.horseracingpage.HorseRacingPage;
import by.training.java.grodno.az.webapp.page.loginpage.LoginPage;
import by.training.java.grodno.az.webapp.page.userpage.AddBalance;

public class SelectCoefficient extends AbstractPage {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(SelectCoefficient.class);
	public static final int MAXQUANTITY = 7;

	@Inject
	private ParticipantService participantService;

	@Inject
	private RacingLineService racingLineService;

	@Inject
	private RateLineService rateLineService;

	@Inject
	private RateService rateService;

	@Inject
	private UserService userService;

	@Inject
	private CoefficientService coefficientService;

	private HorseRacing horseRacing;

	private int index;

	public SelectCoefficient(HorseRacing hourseRacing) {
		super();
		this.horseRacing = hourseRacing;
	}

	@SuppressWarnings("serial")
	@Override
	protected void onInitialize() {
		super.onInitialize();
		Double fieldRateValue = 100.0;
		Model<Double> rateValueModel = new Model<Double>(fieldRateValue);

		List<Rate> rates = new ArrayList<>();
		List<Coefficient> coefficientsList = new ArrayList<>();

		Map<String, Object> atributes = new HashMap<>();
		atributes.put("horseRacingId", horseRacing.getId());

		// initialization racingLine list of racingLine where
		// hourseRacingId=current horse racing id
		List<RacingLine> racingLineList = new ArrayList<>(racingLineService.getAll(atributes, "id", true));
		int racingLineListSize = racingLineList.size();

		// initialization rateLine list of all of rateLine
		List<RateLine> rateLineList = new ArrayList<>(rateLineService.getAll(null, "id", true));
		int rateLineListSize = rateLineList.size();

		List<CoefficientView> coefficientViewList = new ArrayList<>();

		for (int i = 0; i < racingLineListSize; i++) {

			RacingLine racingline = racingLineList.get(i);
			CoefficientView coefficientView = new CoefficientView();
			int racingLineId = racingline.getId();
			coefficientView.participantName = participantService.getViewById(racingline.getParticipantId())
					.toStringShort();

			for (int r = 0; r < rateLineListSize; r++) {

				int rateLineId = rateLineList.get(r).getId();
				Map<String, Object> findingAtributes = new HashMap<>();
				findingAtributes.put("rateLineId", rateLineId);
				findingAtributes.put("racingLineId", racingLineId);
				List<Coefficient> coefficients = coefficientService.getAll(findingAtributes, null, true);
				Coefficient coefficient;
				if (coefficients.size() != 0) {
					coefficient = coefficients.get(0);
				} else {
					coefficient = new Coefficient();
				}
				PropertyModel<Double> propertyModel = new PropertyModel<>(coefficient, "value");
				coefficientView.coefficients.add(coefficient.getId());
				coefficientView.coefficientsModels.put(coefficient.getId(), propertyModel);
			}
			coefficientViewList.add(coefficientView);
		}

		add(new Label("sel-hourse-racing-title", horseRacing.toString()));

		for (int i = 0; i < CoefficientEditPage.MAXQUANTITY; i++) {
			String title = "Empty";
			if (i < rateLineListSize) {
				title = rateLineList.get(i).getTitle();
			}
			add(new Label(String.format("title-%s", i), title));
		}

		Form<Void> form = new Form<>("rate-form");
		add(form);
		form.setOutputMarkupId(true);

		// Model<Double> rateValueModel = new Model<>(rateValueModel);
		TextField<Double> rateValueTextField = new TextField<Double>("rate-value", rateValueModel, Double.class);
		form.add(rateValueTextField.setRequired(true));

		final Model<Double> model = new Model<Double>(0.0);
		final Label label = new Label("possible-winning-label", model);
		// label.setOutputMarkupId(true);
		form.add(label);

		// Model<Double> posWinModel=new Model(posWin);
		// Label posWinLabel = new Label("possible-winning-label", posWinModel);
		// posWinLabel.setOutputMarkupId(true);
		// form.add(posWinLabel);

		ListView<Rate> listView = new ListView<Rate>("rate-list", rates) {

			@Override
			protected void populateItem(ListItem<Rate> item) {
				final Rate rate = item.getModelObject();
				item.add(new Label("rate-coefficient-label", rate.getCoefficientValue()));
			}
		};
		form.add(listView);

		LinkForRole balanceLink = new LinkForRole("add-balance-link") {

			@Override
			public void onClick() {
				setResponsePage(new AddBalance(userService
						.getById(Session.get().getMetaData(UserSession.USER_METADATA_KEY).getUser().getId())));
			}
		};
		add(balanceLink);

		form.add(new SubmitLink("submit-button") {
			@Override
			public void onSubmit() {
				if (UserSession.get().isSignedIn()) {
					int userId = Session.get().getMetaData(UserSession.USER_METADATA_KEY).getUser().getId();

					double checkBalance = 0.0;
					for (Rate rate : rates) {
						checkBalance += rateValueModel.getObject();
					}

					if (rates.size() == 0) {
						SelectCoefficient responsePage = new SelectCoefficient(horseRacing);
						responsePage.warn(getString("page.selectCoefficient.no.rates"));
						setResponsePage(responsePage);
					} else {

						if (userService.getById(userId).getBalance() < checkBalance) {
							SelectCoefficient responsePage = new SelectCoefficient(horseRacing);
							responsePage.warn(getString("page.selectCoefficient.insufficient.funds"));
							setResponsePage(responsePage);

						} else {

							for (Rate rate : rates) {
								rate.setUserId(userId);
								rate.setValue(rateValueModel.getObject());
								LOGGER.info("Submit link. Inser or update {}", rate);
								rateService.doRate(rate);
								SelectCoefficient responsePage = new SelectCoefficient(horseRacing);
								responsePage.info(getString("all.data.saved"));
								setResponsePage(responsePage);
							}
						}
					}

				} else {
					setResponsePage(LoginPage.class);
				}
			}
		});

		add(new ListView<CoefficientView>("sel-coefficient-list", coefficientViewList) {
			@Override
			protected void populateItem(ListItem<CoefficientView> item) {

				final CoefficientView coefficientView = item.getModelObject();
				item.add(new Label("participant", coefficientView.participantName));
				int modelsSize = coefficientView.coefficientsModels.size();

				for (int q = 0; q < CoefficientEditPage.MAXQUANTITY; q++) {
					String id = String.valueOf(q);
					if (q < modelsSize) {

						int key = coefficientView.coefficients.get(q);

						PropertyModel<Double> coefModel = coefficientView.coefficientsModels.get(key);
						Label coefValue = new Label("coef-value-" + id, coefModel);

						AjaxLink<Void> ajaxLink = new AjaxLink<Void>(id) {

							@Override
							public void onClick(AjaxRequestTarget target) {

								boolean isContains = false;
								for (int i = 0; i < rates.size(); i++) {
									if (rates.get(i).getCoefficientId() == key) {
										rates.remove(i);
										isContains = true;
									}
								}

								if (!isContains) {
									Rate rate = new Rate();
									rate.setCoefficientValue(coefModel.getObject());
									rate.setCoefficientId(key);
									rates.add(rate);

									add(AttributeModifier.replace("class", "button-red"));

								} else {
									add(AttributeModifier.replace("class", "button"));
								}
								model.setObject(getPosWin(rates, rateValueModel));
								// target.add(label);
								rateValueModel.setObject(Double.valueOf(rateValueTextField.getValue()));
								target.add(form);
								target.add(this);
							}
						};

						item.add(ajaxLink.add(coefValue));
						if (coefModel.getObject() == 0) {
							ajaxLink.setVisible(false);
						}

					} else {

						Label coefValue = new Label("coef-value-" + q, new Model<Double>());

						item.add(((new AjaxLink<Void>(String.valueOf(q)) {

							@Override
							public void onClick(AjaxRequestTarget target) {
								System.out.println("rates.size()=" + rates.size());
								for (int i = 0; i < rates.size(); i++) {
									if (rates.get(i).getCoefficientId() == 0) {
										rates.remove(i);
									} else {
										Rate rate = new Rate();
										PropertyModel<Double> valueModel = coefficientView.coefficientsModels
												.get(index);
										rate.setCoefficientValue(valueModel.getObject());
										rate.setCoefficientId(coefficientsList.get(i).getId());
										rates.add(rate);
									}

								}
								getPosWin(rates, rateValueModel);
								target.add(form);
							}
						}).add(coefValue)).setVisible(false));

					}
				}

			}

		});

		add(new BookmarkablePageLink<Void>("sel-hourse-racing-page-link", HorseRacingPage.class));

	}

	private double getPosWin(List<Rate> rates, Model<Double> rateValueModel) {
		Double posWin = 0.0;
		posWin = 0.0;
		for (Rate rate : rates) {
			posWin += rateValueModel.getObject() * rate.getCoefficientValue();
		}
		System.out.println("posWin=" + posWin);
		return posWin;
	}

	private class CoefficientView implements Serializable {
		private static final long serialVersionUID = 1L;

		public CoefficientView() {
			super();
			this.coefficientsModels = new HashMap<>();
			coefficients = new ArrayList<>();
		}

		// private Coefficient coefficient;
		private String participantName;
		private List<Integer> coefficients;
		private Map<Integer, PropertyModel<Double>> coefficientsModels;

	}

	@AuthorizeAction(roles = { "admin", "bukmeker", "player" }, action = Action.ENABLE)
	private class UsersSubmitLink extends SubmitLink {
		private static final long serialVersionUID = 1L;

		public UsersSubmitLink(String id) {
			super(id);
		}

	}

}
