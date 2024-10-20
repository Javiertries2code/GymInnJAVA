package manager;

import java.awt.Frame;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import display.*;

import accesoDatos.Reader;
import objects.Workout;

public class Workouts {

	public  ArrayList<Workout> showSameLowerLevelWorkouts(String level) throws InvalidClassException, StreamCorruptedException, ClassNotFoundException, FileNotFoundException, IOException, InterruptedException, ExecutionException {
		
		int levelSearch;
		if (level.isEmpty())
			levelSearch = -1;
		else 
			levelSearch = Integer.parseInt(level);
		ArrayList<Workout> workouts =  new Reader().getSameLowerLevelWorkouts(levelSearch);
		
		
		
		return workouts;		
	}
	
}
