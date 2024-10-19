package objects;

import java.util.Objects;

public class Set {

	private String id;
	private String name;
	private String description;
	private Integer time;
	private Integer reps;


	
	public Set(String id, String name, String description, Integer time, Integer reps) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.time = time;
		this.reps = reps;
	}



	public Set() {
		super();
		this.id = "";
		this.name = "name";
		this.description = "description";
		this.time = -1;
		this.reps = -1;
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
		return "Set [id=" + id + ", name=" + name + ", description=" + description + ", time=" + time + ", reps=" + reps
				+ "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, reps, time);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Set other = (Set) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(reps, other.reps)
				&& Objects.equals(time, other.time);
	}

	
}
