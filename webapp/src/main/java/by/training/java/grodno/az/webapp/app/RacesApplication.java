package by.training.java.grodno.az.webapp.app;

import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import by.training.java.grodno.az.webapp.converterlocator.RacesDoubleConvertor;
import by.training.java.grodno.az.webapp.page.homepage.HomePage;
import by.training.java.grodno.az.webapp.page.loginpage.LoginPage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.epam.training.webapp.StartJetty#main(String[])
 */
@Component("MyWebApplication")
public class RacesApplication extends AuthenticatedWebApplication {

	@Autowired
	private ApplicationContext context;

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		getComponentInstantiationListeners().add(
				new SpringComponentInjector(this, context));

		getMarkupSettings().setStripWicketTags(true);
		// add your configuration here
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return UserSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}
	
	@Override
	protected IConverterLocator newConverterLocator() {
	    ConverterLocator converterLocator = new ConverterLocator();
	    converterLocator.set(Double.class, new RacesDoubleConvertor());
	    return converterLocator;
	}
}
