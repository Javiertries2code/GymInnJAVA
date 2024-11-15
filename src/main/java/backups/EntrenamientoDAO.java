package backups;

import com.google.cloud.firestore.Firestore;

import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;

import objects.Workout;
import objects.Routine;
import objects.Usuario;

import accesoDatos.Connection;

public class EntrenamientoDAO {

    private static Firestore db;

    static {
        try {
            db = Connection.getDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene todos los entrenamientos desde Firestore junto con sus rutinas y los guarda en un archivo.
     * 
     * @return lista de objetos Workout con sus rutinas asociadas
     */
    public static List<Workout> getWorkoutsWithRoutines() {
        List<Workout> workouts = new ArrayList<>();

        try {
            CollectionReference workoutsCollection = db.collection("workouts");
            ApiFuture<QuerySnapshot> query = workoutsCollection.get();
            QuerySnapshot querySnapshot = query.get();

            for (QueryDocumentSnapshot document : querySnapshot) {

                Workout workout = document.toObject(Workout.class);
                
                List<Routine> routines = getRoutinesForWorkout(workout.getId());
                workout.setRefSets(new ArrayList<>(routines));
                workouts.add(workout);
            }

            saveToFile(workouts);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return workouts;
    }

    /**
     * Obtiene las rutinas asociadas a un entrenamiento por el ID de ese entrenamiento.
     * 
     * @param workoutId el ID del entrenamiento
     * @return lista de rutinas asociadas al entrenamiento
     */
    private static List<Routine> getRoutinesForWorkout(String workoutId) {
        List<Routine> routines = new ArrayList<>();

        try {
          
            CollectionReference routinesCollection = db.collection("workouts")
                    .document(workoutId)
                    .collection("routines");
            ApiFuture<QuerySnapshot> query = routinesCollection.get();
            QuerySnapshot querySnapshot = query.get();

            for (QueryDocumentSnapshot document : querySnapshot) {
                Routine routine = document.toObject(Routine.class);
                routines.add(routine);
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return routines;
    }

    /**
     * Guarda la lista de entrenamientos en un archivo backup2.dat.
     * 
     * @param workouts la lista de entrenamientos a guardar
     */
    private static void saveToFile(List<Workout> workouts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("backup.dat"))) {
            oos.writeObject(workouts);
            System.out.println("Datos guardados exitosamente en backup.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene todos los usuarios desde Firestore y los guarda en un archivo.
     * 
     * @return lista de usuarios
     */
    public static List<Usuario> getAllUsers() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
 
            CollectionReference usersCollection = db.collection("usuarios");
            ApiFuture<QuerySnapshot> query = usersCollection.get();
            QuerySnapshot querySnapshot = query.get();

            for (QueryDocumentSnapshot document : querySnapshot) {
                Usuario usuario = document.toObject(Usuario.class);
                usuarios.add(usuario);
            }

            saveUsersToFile(usuarios);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    /**
     * Guarda la lista de usuarios en un archivo backup2.dat.
     * 
     * @param usuarios la lista de usuarios a guardar
     */
    private static void saveUsersToFile(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("backup.dat", true))) {
            oos.writeObject(usuarios);
            System.out.println("Usuarios guardados exitosamente en backups.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
