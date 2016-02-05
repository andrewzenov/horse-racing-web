package by.training.java.grodno.az.webapp.component.menupanel.adminmenupanel;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import by.training.java.grodno.az.webapp.page.aboutuspage.AboutUsPage;
import by.training.java.grodno.az.webapp.page.admin.adminpage.AdminPage;
import by.training.java.grodno.az.webapp.page.admin.editpage.EditPage;
import by.training.java.grodno.az.webapp.page.admin.horseracingpage.HorseRacingPage;
import by.training.java.grodno.az.webapp.page.rulespage.RulesPage;

public class AdminMainMenuPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public AdminMainMenuPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new BookmarkablePageLink<Void>("admin-page-link", AdminPage.class));
		add(new BookmarkablePageLink<Void>("edit-page-link", EditPage.class));
		add(new BookmarkablePageLink<Void>("rules-page-link", RulesPage.class));
		add(new BookmarkablePageLink<Void>("about-us-page-link", AboutUsPage.class));
		add(new BookmarkablePageLink<Void>("rates-page-link", HorseRacingPage.class));

	}

}
