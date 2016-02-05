package by.training.java.grodno.az.data.model;

import by.training.java.grodno.az.data.entities.AbstractEntity;

public class Coefficient extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private int id;
	private double value;
	private int rateLineId;
	private int racingLineId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getRateLineId() {
		return rateLineId;
	}

	public void setRateLineId(int rateLineId) {
		this.rateLineId = rateLineId;
	}

	public int getRacingLineId() {
		return racingLineId;
	}

	public void setRacingLineId(int racingLineId) {
		this.racingLineId = racingLineId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coefficient [id=" + id + ", value=" + value + ", rateLineId=" + rateLineId + ", racingLineId="
				+ racingLineId + "]";
	}

}
