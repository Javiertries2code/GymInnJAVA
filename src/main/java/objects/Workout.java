package objects;

import java.util.Arrays;
import java.util.Objects;

public class Workout {

	private String name;
	private Integer level;
	private int[] refSets;
	private String url;
	private String ref_set1;
	private String ref_set2;
	private String ref_set3;
	private String ref_set4;
	

	public Workout(String name, Integer level, int[] refSets, String url, String ref_set1, String ref_set2,
			String ref_set3, String ref_set4) {
		super();
		this.name = name;
		this.level = level;
		this.refSets = refSets;
		this.url = url;
		this.ref_set1 = ref_set1;
		this.ref_set2 = ref_set2;
		this.ref_set3 = ref_set3;
		this.ref_set4 = ref_set4;
	}
	public Workout() {
		super();
		this.name = "name";
		this.level = -1;
		this.refSets = null;
		this.url = "url";
		this.ref_set1 = null;
		this.ref_set2 = null;
		this.ref_set3 = null;
		this.ref_set4 = null;
		
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
	public int[] getRefSets() {
		return refSets;
	}
	public void setRefSets(int[] refSets) {
		this.refSets = refSets;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRef_set1() {
		return ref_set1;
	}
	public void setRef_set1(String ref_set1) {
		this.ref_set1 = ref_set1;
	}
	public String getRef_set2() {
		return ref_set2;
	}
	public void setRef_set2(String ref_set2) {
		this.ref_set2 = ref_set2;
	}
	public String getRef_set3() {
		return ref_set3;
	}
	public void setRef_set3(String ref_set3) {
		this.ref_set3 = ref_set3;
	}
	public String getRef_set4() {
		return ref_set4;
	}
	public void setRef_set4(String ref_set4) {
		this.ref_set4 = ref_set4;
	}
	@Override
	public String toString() {
		return "Workout [name=" + name + ", level=" + level + ", refSets=" + Arrays.toString(refSets) + ", url=" + url
				+ ", ref_set1=" + ref_set1 + ", ref_set2=" + ref_set2 + ", ref_set3=" + ref_set3 + ", ref_set4="
				+ ref_set4 + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(refSets);
		result = prime * result + Objects.hash(level, name, ref_set1, ref_set2, ref_set3, ref_set4, url);
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
				&& Arrays.equals(refSets, other.refSets) && Objects.equals(ref_set1, other.ref_set1)
				&& Objects.equals(ref_set2, other.ref_set2) && Objects.equals(ref_set3, other.ref_set3)
				&& Objects.equals(ref_set4, other.ref_set4) && Objects.equals(url, other.url);
	}
	
}
