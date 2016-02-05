package by.training.java.grodno.az.service;

import java.util.List;

import by.training.java.grodno.az.data.model.Coefficient;
import by.training.java.grodno.az.data.model.RacingLine;
import by.training.java.grodno.az.data.model.RateLine;

public interface CoefficientService extends IService<Coefficient> {

	RateLine getRateLine(int rateLine_id);

	RacingLine getRacingLine(int racingLine_id);
	
	void insert(List<Coefficient> list);

}
