package by.training.java.grodno.az.webapp.component.languageselection;

import java.util.Locale;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import by.training.java.grodno.az.webapp.page.homepage.HomePage;

public class LanguageSelectionComponent extends Panel {
	private static final long serialVersionUID = 1L;

	public LanguageSelectionComponent(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new LangSelectionLink("ru"));
		add(new LangSelectionLink("en"));
	}

	private final class LangSelectionLink extends Link<Void> {
		private static final long serialVersionUID = 1L;

		private LangSelectionLink(String id) {
			super(id);
		}

		@Override
		protected void onConfigure() {
			super.onConfigure();
			Locale locale = Session.get().getLocale();
			String lang = locale == null ? null : locale.getLanguage();
			if (getId().equals(lang)) {
				add(AttributeModifier.append("style", "border:2px solid"));
			} else {
				add(AttributeModifier.remove("style"));
			}
		}

		@Override
		public void onClick() {
			Session.get().setLocale(new Locale(getId()));
			setResponsePage(HomePage.class);

		}
	}
}
