package by.training.java.grodno.az.webapp.page.admin.editpage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;
import by.training.java.grodno.az.webapp.page.admin.horseracingpage.HorseRacingPage;
import by.training.java.grodno.az.webapp.page.admin.horsespage.HorsePage;
import by.training.java.grodno.az.webapp.page.admin.jockeyspage.JockeysPage;
import by.training.java.grodno.az.webapp.page.admin.participantspage.ParticipantsPage;
import by.training.java.grodno.az.webapp.page.ratelinepage.RateLinePage;
import by.training.java.grodno.az.webapp.page.userpage.UsersPage;

@AuthorizeInstantiation(value = { "admin" })
public class EditPage extends AbstractPage {

	private static final long serialVersionUID = 1L;

	@Override
	protected void onInitialize() {
		super.onInitialize();

		List<ActionLink> links = new ArrayList<>();

		links.add(new ActionLink(getString("all.users"), UsersPage.class));
		links.add(new ActionLink(getString("all.jockeys"), JockeysPage.class));
		links.add(new ActionLink(getString("all.hourses"), HorsePage.class));
		links.add(new ActionLink(getString("all.participants"), ParticipantsPage.class));
		links.add(new ActionLink(getString("all.hourse.racings"), HorseRacingPage.class));
		links.add(new ActionLink(getString("all.rateLine"), RateLinePage.class));

		add(new ListView<ActionLink>("action-link", links) {

			@Override
			protected void populateItem(ListItem<ActionLink> item) {
				final ActionLink actionLink = item.getModelObject();
				item.add(new Label("category", actionLink.title));
				item.add(new BookmarkablePageLink<Void>("page-link", actionLink.pageClass));

			}
		});

	};

	private class ActionLink implements Serializable {
		private static final long serialVersionUID = 1L;

		private String title;
		private Class pageClass;

		private ActionLink(String title, Class<? extends Page> pageClass) {
			this.title = title;
			this.pageClass = pageClass;
		}
	}

}
