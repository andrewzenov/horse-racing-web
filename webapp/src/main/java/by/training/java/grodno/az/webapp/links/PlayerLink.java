package by.training.java.grodno.az.webapp.links;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.link.Link;

@AuthorizeAction(roles = { "player" }, action = Action.RENDER)
public class PlayerLink extends Link<Void> {
	private static final long serialVersionUID = 1L;

	public PlayerLink(String id) {
		super(id);
	}

	@Override
	public void onClick() {
	}
}
