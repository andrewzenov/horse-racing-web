package by.training.java.grodno.az.webapp.page.admin.participantspage;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import by.training.java.grodno.az.data.entities.ParticipantView;
import by.training.java.grodno.az.service.ParticipantService;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;

@AuthorizeInstantiation(value = { "admin" })
public class ParticipantsPage extends AbstractPage {

	private static final long serialVersionUID = 1L;

	@Inject
	private ParticipantService participantService;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		List<ParticipantView> allUsers = participantService.getView();

		add(new ListView<ParticipantView>("participants-list", allUsers) {
			@Override
			protected void populateItem(ListItem<ParticipantView> item) {

				final ParticipantView participantView = item.getModelObject();

				int participantId = participantView.getParticipantId();
				item.add(new Label("id", participantId));
				item.add(new Label("jockey",
						participantView.getJockeyFirstName() + " " + participantView.getJockeyLastName()));
				item.add(new Label("hourse", participantView.getHorseName()));

				item.add(new Link("participant-edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new ParticipantEditPage(participantService.getById(participantId)));
					}
				});

				item.add(new Link("participant-delete-link") {

					@Override
					public void onClick() {
						participantService.delete(participantId);
						setResponsePage(ParticipantsPage.class);
					}
				});

			}
		});

		add(new Link("participant-create-link") {
			@Override
			public void onClick() {
				setResponsePage(new ParticipantEditPage());
			}
		});
	}

}
