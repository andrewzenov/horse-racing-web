package by.training.java.grodno.az.webapp.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.training.java.grodno.az.data.model.Jockey;

public class JockeyChoiceRenderer extends ChoiceRenderer<Jockey>{

	private static final long serialVersionUID = 1L;

	@Override
	public Object getDisplayValue(Jockey object) {
		return String.format("%s %s",object.getFirstName(),object.getLastName());
	}

	@Override
	public String getIdValue(Jockey object, int index) {
		return String.valueOf(object.getId());
	}
	
	
}
