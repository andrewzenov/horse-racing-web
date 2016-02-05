package by.training.java.grodno.az.service;

import java.util.List;

import by.training.java.grodno.az.data.entities.ParticipantView;
import by.training.java.grodno.az.data.model.Horse;
import by.training.java.grodno.az.data.model.Jockey;
import by.training.java.grodno.az.data.model.Participant;

public interface ParticipantService extends IService<Participant> {

	Horse getHourse(int hourse_id);

	Jockey getJockey(int jockey_id);
	
	List<ParticipantView> getView();
	
	ParticipantView getViewById(int participant_id);
	
	void insert(List<Participant> list);
	
}
