package by.training.java.grodno.az.data.model;

import by.training.java.grodno.az.data.entities.AbstractEntity;

public class Participant extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int horseId;
	private int jockeyId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHorseId() {
		return horseId;
	}

	public void setHorseId(int hourseId) {
		this.horseId = hourseId;
	}

	public int getJockeyId() {
		return jockeyId;
	}

	public void setJockeyId(int jockeyId) {
		this.jockeyId = jockeyId;
	}

	@Override
	public String toString() {
		return "Participant [id=" + id + ", horseId=" + horseId + ", jockeyId=" + jockeyId + "]";
	}

}
