package by.training.java.grodno.az.webapp.links;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.link.Link;

@AuthorizeAction(roles = { "admin" }, action = Action.RENDER)
public class AdminLinkRender extends Link<Void> {
	private static final long serialVersionUID = 1L;

	public AdminLinkRender(String id) {
		super(id);
	}

	@Override
	public void onClick() {
	}
}
