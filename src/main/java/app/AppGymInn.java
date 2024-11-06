package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.QueryDocumentSnapshot;

import accesoDatos.*;
import display.Frame;


import accesoDatos.Backups;
import accesoDatos.Reader;
import objects.Workout;

public class AppGymInn {

	public static void main(String[] args) {
		try {
			System.out.println("Running...");

            // Crear instancia de Backups
            Backups backups = new Backups();

            // Obtener los workouts desde Firebase
            backups.obtenerWorkouts();

            // Obtener la lista de QueryDocumentSnapshot desde Firebase
            Reader reader = new Reader();
            List<QueryDocumentSnapshot> snapshotList = reader.getAllWorkoutsFirebase(); // Devuelve una lista de QueryDocumentSnapshot

            // Convertir los QueryDocumentSnapshot a objetos Workout
            List<Workout> workouts = convertToWorkoutList(snapshotList);

            // Guardar los workouts en un archivo
            backups.saveWorkouts(workouts);

            System.out.println("Running Frame...");
            new Frame().run();
			new Frame().run();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 // Método para convertir QueryDocumentSnapshot a Workout
    private static List<Workout> convertToWorkoutList(List<QueryDocumentSnapshot> snapshotList) {
        List<Workout> workouts = new ArrayList<>();
        for (QueryDocumentSnapshot snapshot : snapshotList) {
            // Convertir cada QueryDocumentSnapshot a un objeto Workout
            Workout workout = snapshot.toObject(Workout.class);
            workouts.add(workout);
        }
        return workouts;
    }
}
