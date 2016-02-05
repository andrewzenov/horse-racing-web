package by.training.java.grodno.az.data.dao;

import java.util.List;
import java.util.Map;

import by.training.java.grodno.az.data.entities.AbstractEntity;

public interface Dao<T extends AbstractEntity> {

	T get(int id);

	int insert(T entity);

	int update(T entity);

	int delete(int id);

	void delete(T entity);

	List<T> getAll();
	
	List<T> getAll(int first, int count);
	
	List<T> getAll(String orderBy, boolean orderType);
	
	List<T> getAll(int first, int count, String orderBy, boolean orderType);

	List<T> getAll(Map<String, Object> atributesFinding, String orderBy,boolean type);
	
	List<T> getAll(Map<String, Object> atributesFinding, int first, int count, String orderBy, boolean orderType);
	
	int getCount();

	int getCount(Map<String, Object> atributesFinding);

	

	

	

	


}
