package by.training.java.grodno.az.webapp.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.training.java.grodno.az.data.entities.RacingLineView;

public class RacingLineViewChoiceRenderer extends ChoiceRenderer<RacingLineView> {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getDisplayValue(RacingLineView object) {
		return String.format("%s and %s", object.getParticipantView().getJockeyFirstName(),
				object.getParticipantView().getHorseName());
	}

	@Override
	public String getIdValue(RacingLineView object, int index) {
		return String.valueOf(object.getRacingLineId());
	}

}
