package objects;

import java.util.Arrays;
import java.util.Objects;

public class Workout {

	private String name;
	private Integer level;
	private Integer numSets;
	private String[] refSets;
	private String url;
	
	public Workout(String name, Integer level, Integer numSets, String[] refSets, String url) {
		super();
		this.name = name;
		this.level = level;
		this.numSets = numSets;
		this.refSets = refSets;
		this.url = url;
	}
	public Workout() {
		super();
		this.name = "";
		this.level = -1;
		this.numSets = -1;
		this.refSets = null;
		this.url = "https://www.youtube.com/watch?v=NfVUrG2wNG0";
	}
	public String getName() {
		return name;
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
	public String[] getRefSets() {
		return refSets;
	}
	public void setRefSets(String[] refSets) {
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
		return "Workout [name=" + name + ", level=" + level + ", numSets=" + numSets + ", refSets="
				+ Arrays.toString(refSets) + ", url=" + url + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(refSets);
		result = prime * result + Objects.hash(level, name, numSets, url);
		return result;
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
		return Objects.equals(level, other.level) && Objects.equals(name, other.name)
				&& Objects.equals(numSets, other.numSets) && Arrays.equals(refSets, other.refSets)
				&& Objects.equals(url, other.url);
	}
	
	
	
	
	
}
