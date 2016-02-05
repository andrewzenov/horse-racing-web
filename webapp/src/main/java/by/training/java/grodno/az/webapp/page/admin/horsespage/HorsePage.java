package by.training.java.grodno.az.webapp.page.admin.horsespage;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import by.training.java.grodno.az.data.model.Horse;
import by.training.java.grodno.az.service.HorseService;
import by.training.java.grodno.az.service.ParticipantService;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;

@AuthorizeInstantiation(value = { "admin" })
public class HorsePage extends AbstractPage {

	private static final long serialVersionUID = 1L;

	@Inject
	private HorseService hourseService;

	@Inject
	private ParticipantService participantService;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		List<Horse> allUsers = hourseService.getAll();

		add(new ListView<Horse>("hourses-list", allUsers) {
			@Override
			protected void populateItem(ListItem<Horse> item) {

				final Horse hourse = item.getModelObject();
				item.add(new Label("id", hourse.getId()));
				item.add(new Label("name", hourse.getName()));

				item.add(new Link("hourse-edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new HorseEditPage(hourse));
					}
				});

				item.add(new Link("hourse-delete-link") {

					@Override
					public void onClick() {
						if (participantService.getHourse(hourse.getId()) == null) {
							hourseService.delete(hourse);
							setResponsePage(HorsePage.class);
						} else {
							HorsePage editPage = new HorsePage();
							editPage.error(getString("all.delete.inposible"));
							setResponsePage(editPage);
						}
					}
				});

			}
		});

		add(new Link("hourse-create-link") {
			@Override
			public void onClick() {
				setResponsePage(new HorseEditPage());
			}
		});
	}

}
