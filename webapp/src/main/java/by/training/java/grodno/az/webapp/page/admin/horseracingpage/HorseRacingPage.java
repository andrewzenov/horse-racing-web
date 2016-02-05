package by.training.java.grodno.az.webapp.page.admin.horseracingpage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;

import by.training.java.grodno.az.data.model.HorseRacing;
import by.training.java.grodno.az.data.model.RacingLine;
import by.training.java.grodno.az.service.HorseRacingService;
import by.training.java.grodno.az.service.RacingLineService;
import by.training.java.grodno.az.webapp.links.AdminLinkRender;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;
import by.training.java.grodno.az.webapp.page.admin.coefficientpage.CoefficientEditPage;
import by.training.java.grodno.az.webapp.page.admin.coefficientpage.SelectCoefficient;
import by.training.java.grodno.az.webapp.page.admin.racinglinepage.RacingLineEditParticipantPage;
import by.training.java.grodno.az.webapp.page.admin.racinglinepage.RacingLineResultEditPage;
import by.training.java.grodno.az.webapp.page.resultpage.ResultPage;

public class HorseRacingPage extends AbstractPage {

	private static final long serialVersionUID = 1L;

	@Inject
	private HorseRacingService hourseRacingService;

	@Inject
	private RacingLineService racingLineService;

	@Override
	protected void onInitialize() {
		super.onInitialize();

		List<HorseRacing> allHourseRacings = hourseRacingService.getAll();

		add(new ListView<HorseRacing>("hourse-racing-list", allHourseRacings) {
			@Override
			protected void populateItem(ListItem<HorseRacing> item) {

				final HorseRacing horseRacing = item.getModelObject();

				boolean isAfter = horseRacing.getDate().after(new Date());

				item.add(new Label("id", horseRacing.getId()));
				item.add(new Label("title", horseRacing.getTitle()));
				item.add(new Label("date", horseRacing.getDate()));

				item.add(new AdminLinkRender("hourse-racing-edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new HorseRacingEditPage(horseRacing));
					}
				});

				item.add(new AdminLinkRender("hourse-racing-delete-link") {

					@Override
					public void onClick() {
						hourseRacingService.delete(horseRacing);
						setResponsePage(HorseRacingPage.class);
					}
				});

				item.add(new AdminLinkRender("racing-line-edit-link") {

					@Override
					public void onClick() {
						setResponsePage(new RacingLineEditParticipantPage(horseRacing));
					}
				});

				item.add(new AdminLinkRender("racing-result-edit-link") {

					@Override
					public void onClick() {
						setResponsePage(new RacingLineResultEditPage(horseRacing));
					}
				});

				item.add(new AdminLinkRender("coefficients-edit-link") {

					@Override
					public void onClick() {
						setResponsePage(new CoefficientEditPage(horseRacing));
					}
				});

				Model<String> model = new Model<>(getString("all.rate"));
				Label label = new Label("select-label", model);

				Map<String, Object> findingAtributes = new HashMap<>();
				findingAtributes.put("horseRacingId", horseRacing.getId());

				List<RacingLine> rList = racingLineService.getAll(findingAtributes, "id", true);

				boolean isResult = getIsResult(rList);
				if (!isAfter) {
					model.setObject(getString("all.result"));
				} else {
					if (isResult) {
						model.setObject(getString("all.result"));
					} else {
						model.setObject(getString("all.rate"));
					}
				}

				Link link = new Link("select-coefficients-link") {

					@Override
					public void onClick() {

						if (!isAfter) {
							setResponsePage(new ResultPage(horseRacing));
						} else {
							if (isResult) {
								setResponsePage(new ResultPage(horseRacing));
							} else {
								setResponsePage(new SelectCoefficient(horseRacing));
							}
						}
					}
				};

				item.add(link.add(label));

			}

			private boolean getIsResult(List<RacingLine> rList) {
				boolean result = false;
				for (RacingLine r : rList) {
					if (r.getResult() != null) {
						result = true;

						break;
					}
				}
				return result;
			}
		});

		add(new AdminLinkRender("hourse-racing-create-link") {
			@Override
			public void onClick() {
				setResponsePage(new HorseRacingEditPage());
			}
		});
	}

}
