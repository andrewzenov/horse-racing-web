package by.training.java.grodno.az.webapp.component.logoutinputpanel;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import by.training.java.grodno.az.webapp.app.UserSession;
import by.training.java.grodno.az.webapp.page.homepage.HomePage;
import by.training.java.grodno.az.webapp.page.userpage.personalarea.PersonalArea;

public class LogoutPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogoutPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new Link<Void>("logout") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				UserSession.get().signOut();
				setResponsePage(new HomePage());
			}
		});
		
	add(new Link<Void>("personal-area-link") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(PersonalArea.class);
			}
		});

	}

}
