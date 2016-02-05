package by.training.java.grodno.az.service;

import java.util.List;

import by.training.java.grodno.az.data.entities.RateView;
import by.training.java.grodno.az.data.model.Coefficient;
import by.training.java.grodno.az.data.model.Rate;
import by.training.java.grodno.az.data.model.RateType;

public interface RateService extends IService<Rate> {

	RateType getRateType(int rateType_id);

	Coefficient getCoefficient(int coefficient_id);

	void doRate(Rate rate);

	List<RateView> getRateViewList(int hourseRacingId);

}
