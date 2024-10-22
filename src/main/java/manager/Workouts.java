package manager;

import java.awt.Frame;
import java.util.ArrayList;

import display.*;

import accesoDatos.Reader;
import objects.Workout;

public class Workouts {

	public  ArrayList<Workout> showSameLowerLevelWorkouts(String level) throws Exception {
		
		int levelSearch;
		if (level.isEmpty())
			levelSearch = -1;
		else 
			levelSearch = Integer.parseInt(level);
		ArrayList<Workout> workouts =  new Reader().getSameLowerLevelWorkouts(levelSearch);
		
		
		
		return workouts;		
	}
	
}
