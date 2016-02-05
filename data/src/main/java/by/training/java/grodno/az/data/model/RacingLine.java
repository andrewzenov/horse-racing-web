package by.training.java.grodno.az.data.model;

import by.training.java.grodno.az.data.entities.AbstractEntity;

public class RacingLine extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int horseRacingId;
	private int participantId;
	private Integer result;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHorseRacingId() {
		return horseRacingId;
	}

	public void setHorseRacingId(int horseRacingId) {
		this.horseRacingId = horseRacingId;
	}

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "RacingLine [id=" + id + ", horseRacingId=" + horseRacingId + ", participantId=" + participantId
				+ ", result=" + result + "]";
	}

}
