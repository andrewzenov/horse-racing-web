package by.training.java.grodno.az.data.dao;

import java.util.List;

import by.training.java.grodno.az.data.entities.RateView;
import by.training.java.grodno.az.data.model.Rate;

public interface RateDao extends Dao<Rate>{

	List<RateView> getRateViewList(int hourseRacingId);

}
