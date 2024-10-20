package manager;

import java.awt.Frame;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import display.*;

import accesoDatos.AccesoDatos;
import objects.Workout;

public class Workouts {

	public  ArrayList<Workout> showSameLowerLevelWorkouts() throws InvalidClassException, StreamCorruptedException, ClassNotFoundException, FileNotFoundException, IOException, InterruptedException, ExecutionException {
		
		ArrayList<Workout> workouts =  new AccesoDatos().getSameLowerLevelWorkouts();
		
		
		
		return workouts;		
	}
	
}
