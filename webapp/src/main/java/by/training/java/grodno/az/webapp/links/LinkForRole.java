package by.training.java.grodno.az.webapp.links;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.link.Link;

@AuthorizeAction(roles = { "admin", "player", "bukmeker" }, action = Action.RENDER)
public class LinkForRole extends Link<Void> {
	private static final long serialVersionUID = 1L;

	public LinkForRole(String id) {
		super(id);
	}

	@Override
	public void onClick() {
	}
}
