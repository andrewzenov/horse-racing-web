package by.training.java.grodno.az.webapp.component.headerholder;

import java.util.Set;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import by.training.java.grodno.az.webapp.app.UserSession;
import by.training.java.grodno.az.webapp.component.languageselection.LanguageSelectionComponent;
import by.training.java.grodno.az.webapp.component.logoutinputpanel.InputPanel;
import by.training.java.grodno.az.webapp.component.logoutinputpanel.LogoutPanel;
import by.training.java.grodno.az.webapp.component.menupanel.adminmenupanel.AdminMainMenuPanel;
import by.training.java.grodno.az.webapp.component.menupanel.playermenupanel.PlayerMainMenuPanel;

public class HeaderHolderPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public enum Menu {
		PLAYER, ADMIN
	}

	private String titleForPlayer = getString("component.headerHolderPanel.title.player");
	private String titleForAdmin = getString("component.headerHolderPanel.title.admin");

	public HeaderHolderPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new LanguageSelectionComponent("language-panel"));
		
		String userName =getString("component.headerHolderPanel.user.notaut");
		Label label;

		if (UserSession.get().isSignedIn()) {
			userName = UserSession.get().getMetaData(UserSession.USER_METADATA_KEY).getFullName();
			label = new Label("user-name-label", userName);
			label.add(AttributeModifier.append("style", "color:white"));
		} else {
			label = new Label("user-name-label", userName);
		}
		
		Set<String> role = UserSession.get().getRoles();
		if (role == null) {
			add(new InputPanel("logout-input-panel"));
			add(new PlayerMainMenuPanel("main-menu-panel"));
			add(new Label("title", titleForPlayer));
		} else {
			add(new LogoutPanel("logout-input-panel"));

			switch (role.toString()) {
			case "player":
				add(new PlayerMainMenuPanel("main-menu-panel"));
				add(new Label("title", titleForPlayer));

				break;
			case "admin":
				add(new AdminMainMenuPanel("main-menu-panel"));
				add(new Label("title", titleForAdmin));
				break;

			}
		}

		add(label);

	}

	@Override
	protected void onConfigure() {
		super.onConfigure();

		
	}
}
