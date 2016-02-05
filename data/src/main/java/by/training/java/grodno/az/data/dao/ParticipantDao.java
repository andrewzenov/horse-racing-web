package by.training.java.grodno.az.data.dao;

import java.util.List;

import by.training.java.grodno.az.data.entities.ParticipantView;
import by.training.java.grodno.az.data.model.Participant;

public interface ParticipantDao extends Dao<Participant> {

	List<ParticipantView> getView();

	ParticipantView getViewById(int participant_id);

}
