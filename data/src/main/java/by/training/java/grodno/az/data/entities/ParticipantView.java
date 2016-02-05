package by.training.java.grodno.az.data.entities;

import java.io.Serializable;

public class ParticipantView implements Serializable {

	private static final long serialVersionUID = 1L;

	private int participantId;
	private String jockeyFirstName;
	private String jockeyLastName;
	private String horseName;

	public String getJockeyLastName() {
		return jockeyLastName;
	}

	public void setJockeyLastName(String jockeyLastName) {
		this.jockeyLastName = jockeyLastName;
	}

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public String getJockeyFirstName() {
		return jockeyFirstName;
	}

	public void setJockeyFirstName(String jockeyFirstName) {
		this.jockeyFirstName = jockeyFirstName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public String toStringShort() {
		return "[Id=" + participantId + ", Jockey=" + jockeyFirstName.charAt(0) +". "+ jockeyLastName + ", Horse="
				+ horseName + "]";
	}

	@Override
	public String toString() {
		return "ParticipantView [Participant Id=" + participantId + ", Jockey first name=" + jockeyFirstName
				+ ", Jockey last name=" + jockeyLastName + ", Horse name=" + horseName + "]";
	}

}
