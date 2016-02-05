package by.training.java.grodno.az.webapp.page.admin.jockeyspage;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import by.training.java.grodno.az.data.model.Jockey;
import by.training.java.grodno.az.service.JockeyService;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;

@AuthorizeInstantiation(value = { "admin" })
public class JockeysPage extends AbstractPage {

	private static final long serialVersionUID = 1L;

	@Inject
	private JockeyService jokeyService;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		List<Jockey> allUsers = jokeyService.getAll();

		add(new ListView<Jockey>("jockeys-list", allUsers) {
			@Override
			protected void populateItem(ListItem<Jockey> item) {

				final Jockey jokey = item.getModelObject();
				item.add(new Label("id", jokey.getId()));
				item.add(new Label("firstName", jokey.getFirstName()));
				item.add(new Label("lastName", jokey.getLastName()));

				item.add(new Link("jokey-edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new JockeyEditPage(jokey));
					}
				});

				item.add(new Link("jokey-delete-link") {

					@Override
					public void onClick() {
						jokeyService.delete(jokey);
						setResponsePage(JockeysPage.class);
					}
				});

			}
		});

		add(new Link("jokey-create-link") {
			@Override
			public void onClick() {
				setResponsePage(new JockeyEditPage());
			}
		});
	}

}
