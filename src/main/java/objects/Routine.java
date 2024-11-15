package objects;

import java.util.Objects;

public class Routine {

	private String id;
	private String name;
	private String description;
	private Integer time;
	private Integer reps;
	private Integer rest;


	




	public Routine(String id, String name, String description, Integer time, Integer reps, Integer rest) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.time = time;
		this.reps = reps;
		this.rest = rest;
	}



	public Routine() {
		super();
		this.id = "";
		this.name = "name";
		this.description = "description";
		this.time = -1;
		this.reps = -1;
		this.rest = -1;
		
	}



	public Integer getRest() {
		return rest;
	}



	public void setRest(Integer rest) {
		this.rest = rest;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Integer getTime() {
		return time;
	}



	public void setTime(Integer time) {
		this.time = time;
	}



	public Integer getReps() {
		return reps;
	}



	public void setReps(Integer reps) {
		this.reps = reps;
	}



	@Override
	public String toString() {
		return "Routine [id=" + id + ", name=" + name + ", description=" + description + ", time=" + time + ", reps="
				+ reps + ", rest=" + rest + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, reps, rest, time);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Routine other = (Routine) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(reps, other.reps)
				&& Objects.equals(rest, other.rest) && Objects.equals(time, other.time);
	}

	
}
