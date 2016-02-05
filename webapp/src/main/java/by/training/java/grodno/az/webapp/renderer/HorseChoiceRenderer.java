package by.training.java.grodno.az.webapp.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.training.java.grodno.az.data.model.Horse;

public class HorseChoiceRenderer extends ChoiceRenderer<Horse>{

	private static final long serialVersionUID = 1L;

	@Override
	public Object getDisplayValue(Horse object) {
		return object.getName();
	}

	@Override
	public String getIdValue(Horse object, int index) {
		return String.valueOf(object.getId());
	}
	
}
