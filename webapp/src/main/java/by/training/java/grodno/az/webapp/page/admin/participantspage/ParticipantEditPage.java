package by.training.java.grodno.az.webapp.page.admin.participantspage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import by.training.java.grodno.az.data.model.Horse;
import by.training.java.grodno.az.data.model.Jockey;
import by.training.java.grodno.az.data.model.Participant;
import by.training.java.grodno.az.service.HorseService;
import by.training.java.grodno.az.service.JockeyService;
import by.training.java.grodno.az.service.ParticipantService;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;
import by.training.java.grodno.az.webapp.renderer.HorseChoiceRenderer;
import by.training.java.grodno.az.webapp.renderer.JockeyChoiceRenderer;

@AuthorizeInstantiation(value = { "admin" })
public class ParticipantEditPage extends AbstractPage {
	private static final long serialVersionUID = 1L;

	@Inject
	private JockeyService jockeyService;

	@Inject
	private HorseService horseService;

	@Inject
	private ParticipantService participantService;

	private Participant participant;

	public ParticipantEditPage() {
		super();
		this.participant = new Participant();
	}

	public ParticipantEditPage(Participant participant) {
		super();
		this.participant = participant;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Jockey> form = new Form<>("participant-edit-form");
		add(form);

		Model<Jockey> jockeyModel = new Model<>();
		List<Jockey> jockeyChoices = jockeyService.getAll();
		DropDownChoice<Jockey> dropDownJockeyChoice = new DropDownChoice<>("drop-jockey", jockeyModel, jockeyChoices,
				new JockeyChoiceRenderer());
		dropDownJockeyChoice.setRequired(true);
		form.add(dropDownJockeyChoice);

		Model<Horse> horseModel = new Model<>();
		List<Horse> horseChoices = horseService.getAll();
		DropDownChoice<Horse> dropDownHourseChoice = new DropDownChoice<>("drop-hourse", horseModel, horseChoices,
				new HorseChoiceRenderer());
		dropDownHourseChoice.setRequired(true);
		form.add(dropDownHourseChoice);

		form.add(new SubmitLink("participant-submit-button") {
			@Override
			public void onSubmit() {

				int jockeyId = jockeyModel.getObject().getId();
				int horseId = horseModel.getObject().getId();

				Map<String, Object> findParamerers = new HashMap<>();
				findParamerers.put("jockeyId", jockeyId);
				findParamerers.put("horseId", horseId);

				if (participantService.getAll(findParamerers, null, true).size() == 0) {

					participant.setJockeyId(jockeyId);
					participant.setHorseId(horseId);
					participantService.insertOrUpdate(participant);
					ParticipantEditPage editPage = new ParticipantEditPage();
					editPage.info(getString("all.data.saved"));
					setResponsePage(editPage);
				} else {
					ParticipantEditPage editPage = new ParticipantEditPage();
					editPage.warn(getString("page.participantPage.participantCheck"));
					setResponsePage(editPage);
				}
			};
		});
		add(new Link<Void>("participants-page-link") {
			@Override
			public void onClick() {
				setResponsePage(ParticipantsPage.class);
			}
		});
	}

}
