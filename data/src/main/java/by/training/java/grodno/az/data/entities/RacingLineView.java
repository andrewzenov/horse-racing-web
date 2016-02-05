package by.training.java.grodno.az.data.entities;

import java.io.Serializable;

import by.training.java.grodno.az.data.model.HorseRacing;

public class RacingLineView implements Serializable {

	private static final long serialVersionUID = 1L;

	private int racingLineId;
	private HorseRacing horseRacing;
	private ParticipantView participantView;
	private Integer rusult;

	public int getRacingLineId() {
		return racingLineId;
	}

	public void setRacingLineId(int racingLineId) {
		this.racingLineId = racingLineId;
	}

	public HorseRacing getHorseRacing() {
		return horseRacing;
	}

	public void setHorseRacing(HorseRacing horseRacing) {
		this.horseRacing = horseRacing;
	}

	public ParticipantView getParticipantView() {
		return participantView;
	}

	public void setParticipantView(ParticipantView participantView) {
		this.participantView = participantView;
	}

	public Integer getRusult() {
		return rusult;
	}

	public void setRusult(Integer rusult) {
		this.rusult = rusult;
	}

}
