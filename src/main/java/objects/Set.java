package objects;

import java.util.Objects;

public class Set {

	private String name;
	private String description;
	private Integer reps;
	public Set(String name, String description, Integer reps) {
		super();
		this.name = name;
		this.description = description;
		this.reps = reps;
	}
	public Set() {
		super();
		this.name = "name";
		this.description = "description";
		this.reps = -1;
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
	public Integer getReps() {
		return reps;
	}
	public void setReps(Integer reps) {
		this.reps = reps;
	}
	@Override
	public String toString() {
		return "Set [name=" + name + ", description=" + description + ", reps=" + reps + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, name, reps);
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
		return Objects.equals(description, other.description) && Objects.equals(name, other.name)
				&& Objects.equals(reps, other.reps);
	}
	
	
	
	
}
