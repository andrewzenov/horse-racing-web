package by.training.java.grodno.az.data.model;

import by.training.java.grodno.az.data.entities.AbstractEntity;

public class RateLine extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String title;
	private String description;
	private String positions;
	
	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "RateLine [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
	
	
}
