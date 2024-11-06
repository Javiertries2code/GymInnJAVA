package accesoDatos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.google.cloud.firestore.QueryDocumentSnapshot;

import objects.Workout;

public class Backups {
    
    // Método para obtener los workouts desde Firebase
    public void obtenerWorkouts() {
        try {
            // Llamamos al método getAllWorkoutsFirebase() de la clase Reader
            Reader reader = new Reader();
            List<QueryDocumentSnapshot> workouts = reader.getAllWorkoutsFirebase();

            // Aquí puedes procesar la lista de workouts
            for (QueryDocumentSnapshot workout : workouts) {
            	 // Imprimir el ID del documento
                System.out.println("ID del Workout: " + workout.getId());

                // Imprimir todos los campos del documento
                System.out.println("Datos del Workout: ");
                System.out.println("Name: " + workout.getString("name"));
                System.out.println("Level: " + workout.getLong("level"));
                System.out.println("NumSets: " + workout.getLong("numSets"));
                System.out.println("URL: " + workout.getString("url"));
                
                // Si refSets es un campo que puede ser una lista, también podrías imprimirlo de esta manera:
                System.out.println("RefSets: " + workout.get("refSets"));

                System.out.println("=====================================");
            }

        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
    }
    
    // Método para guardar la lista de workouts en un archivo de respaldo
    public void saveWorkouts(List<Workout> workouts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("backup.dat"))) {
            oos.writeObject(workouts);  // Serializa la lista de workouts
            System.out.println("Workouts guardados exitosamente en backup.dat");
        } catch (IOException e) {
            System.err.println("Error guardando workouts: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
