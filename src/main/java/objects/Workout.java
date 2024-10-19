package objects;

import java.util.Arrays;
import java.util.Objects;

public class Workout {

	private String name;
	private Integer level;
	private Integer numSets;
	private int[] refSets;
	private String url;
	public Workout(String name, Integer level, Integer numSets, int[] refSets, String url) {
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
	
	
	
	
}
