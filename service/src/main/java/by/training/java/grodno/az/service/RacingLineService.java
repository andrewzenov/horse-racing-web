package by.training.java.grodno.az.service;

import java.util.List;

import by.training.java.grodno.az.data.entities.RacingLineView;
import by.training.java.grodno.az.data.model.HorseRacing;
import by.training.java.grodno.az.data.model.Participant;
import by.training.java.grodno.az.data.model.RacingLine;

public interface RacingLineService extends IService<RacingLine> {

	HorseRacing getHourseRacing(int hourseRacing_id);

	Participant getParticipant(int participant_id);
	
	RacingLineView getView(RacingLine racingLine);
	
	void insert(List<RacingLine> list);
}
