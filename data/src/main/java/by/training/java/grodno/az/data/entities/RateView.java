package by.training.java.grodno.az.data.entities;

import java.io.Serializable;

public class RateView implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int rateId;
	private Double value;
	private Double coefficientValue;
	private int coefficientId;
	private int userId;
	private int rateLineId;
	private int result;

	public int getRateId() {
		return rateId;
	}

	public void setRateId(int rateId) {
		this.rateId = rateId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getCoefficientValue() {
		return coefficientValue;
	}

	public void setCoefficientValue(Double coefficientValue) {
		this.coefficientValue = coefficientValue;
	}

	public int getCoefficientId() {
		return coefficientId;
	}

	public void setCoefficientId(int coefficientId) {
		this.coefficientId = coefficientId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRateLineId() {
		return rateLineId;
	}

	public void setRateLineId(int rateLineId) {
		this.rateLineId = rateLineId;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "RateView [rateId=" + rateId + ", value=" + value + ", coefficientValue=" + coefficientValue
				+ ", coefficientId=" + coefficientId + ", userId=" + userId + ", rateLineId=" + rateLineId + ", result="
				+ result + "]";
	}

	
	
}
