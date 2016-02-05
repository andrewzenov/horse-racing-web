package by.training.java.grodno.az.webapp.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.training.java.grodno.az.data.model.HorseRacing;

public class HorseRacingChoiceRenderer extends ChoiceRenderer<HorseRacing> {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getDisplayValue(HorseRacing object) {
		return String.format("%s - %s", object.getTitle(), object.getDate());
	}

	@Override
	public String getIdValue(HorseRacing object, int index) {
		return String.valueOf(object.getId());
	}

}
