package by.training.java.grodno.az.webapp.app;

import javax.inject.Inject;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;

import by.training.java.grodno.az.data.model.User;
import by.training.java.grodno.az.service.UserService;

public class UserSession extends AuthenticatedWebSession {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;

	public static MetaDataKey<UserMetaData> USER_METADATA_KEY = new MetaDataKey<UserMetaData>() {
		private static final long serialVersionUID = 1L;
	};

	private Roles roles;

	public UserSession(Request request) {
		super(request);
		Injector.get().inject(this);
	}

	public static UserSession get() {
		return (UserSession) Session.get();
	}

	@Override
	protected boolean authenticate(String login, String password) {
		if (userService == null) {
			throw new IllegalArgumentException("user service is null");
		}
		User user = userService.getByLogPas(login, password);
		if (user != null) {

			UserMetaData metaData = new UserMetaData();
			metaData.setUser(user);
			Session.get().setMetaData(USER_METADATA_KEY, metaData);
			roles = new Roles();
			roles.add(user.getRole());
			return true;
		}
		return false;

	}

	@Override
	public void signOut() {
		super.signOut();
	}

	@Override
	public Roles getRoles() {
		if (!UserSession.get().isSignedIn()) {
			return null;
		}
		return roles;
	}

}
