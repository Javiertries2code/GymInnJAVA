package objects;

import java.util.Objects;

public class Set {

	private String name;
	private String description;
	private Integer time;
	private Integer reps;
	public Set(String name, String description, Integer time, Integer reps) {
		super();
		this.name = name;
		this.description = description;
		this.time = time;
		this.reps = reps;
	}
	public Set() {
		super();
		this.name = "name";
		this.description = "description";
		this.time = -1;
		this.reps = -1;
	}

	
}
