package objects;

import java.util.Date;

public class HistoricalRecord {

	private String name;
	private int level;
	private int totalTime;
	private int estimatedTime;
	private String date;
	private Double accomplishment;
	
	
	public HistoricalRecord(String name, int level, int totalTime, int estimatedTime, String date, Double accomplishment) {
		super();
		this.name = name;
		this.level = level;
		this.totalTime = totalTime;
		this.estimatedTime = estimatedTime;
		this.date = date;
		this.accomplishment = accomplishment;
	}


	public HistoricalRecord() {
		super();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getTotalTime() {
		return totalTime;
	}


	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}


	public int getestimatedTime() {
		return estimatedTime;
	}


	public void setestimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public Double getPercentage() {
		return accomplishment;
	}


	public void setPercentage(Double percentage) {
		this.accomplishment = percentage;
	}
	
	
	
	
}
