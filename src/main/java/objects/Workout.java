package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Workout {

	private String id;
	private String name;
	private Integer level;
	private Integer numSets;
	private ArrayList<Routine> refSets;
	private String url;
	

	public Workout(String id, String name, Integer level, Integer numSets, ArrayList<Routine> refSets, String url) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.numSets = numSets;
		this.refSets = refSets;
		this.url = url;
	}

	public Workout() {
		super();
		this.id = "";
		this.name = "";
		this.level = -1;
		this.numSets = -1;
		this.refSets = null;
		this.url = "https://www.youtube.com/watch?v=NfVUrG2wNG0";
	}
	
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getNumSets() {
		return numSets;
	}
	public void setNumSets(Integer numSets) {
		this.numSets = numSets;
	}


	public ArrayList<Routine> getRefSets() {
		return refSets;
	}
	public void setRefSets(ArrayList<Routine> refSets) {
		this.refSets = refSets;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Workout [id=" + id + ", name=" + name + ", level=" + level + ", numSets=" + numSets + ", refSets="
				+ refSets + ", url=" + url + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, level, name, numSets, refSets, url);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Workout other = (Workout) obj;
		return Objects.equals(id, other.id) && Objects.equals(level, other.level) && Objects.equals(name, other.name)
				&& Objects.equals(numSets, other.numSets) && Objects.equals(refSets, other.refSets)
				&& Objects.equals(url, other.url);
	}
	
	
	
	
	
}
