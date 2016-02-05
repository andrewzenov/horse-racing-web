package by.training.java.grodno.az.webapp.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.training.java.grodno.az.data.entities.ParticipantView;

public class ParticipantViewChoiceRenderer  extends ChoiceRenderer<ParticipantView> {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getDisplayValue(ParticipantView object) {
		return String.format("%s %s and %s", object.getJockeyFirstName(),object.getJockeyLastName(), object.getHorseName());
	}

	@Override
	public String getIdValue(ParticipantView object, int index) {
		return String.valueOf(object.getParticipantId());
	}

}
