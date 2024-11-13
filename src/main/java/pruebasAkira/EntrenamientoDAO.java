package pruebasAkira;

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
            db = Connection.getDatabase();  // Aquí se puede lanzar IOException
        } catch (IOException e) {
            e.printStackTrace();
            // Maneja el error de la manera que prefieras
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
            // Referencia a la colección de entrenamientos en Firestore
            CollectionReference workoutsCollection = db.collection("workouts");
            ApiFuture<QuerySnapshot> query = workoutsCollection.get();
            QuerySnapshot querySnapshot = query.get();

            // Iteramos sobre los entrenamientos obtenidos
            for (QueryDocumentSnapshot document : querySnapshot) {
                // Convertimos cada documento a un objeto Workout
                Workout workout = document.toObject(Workout.class);
                
                // Ahora obtenemos las rutinas de ese entrenamiento (si las tiene)
                List<Routine> routines = getRoutinesForWorkout(workout.getId());
                workout.setRefSets(new ArrayList<>(routines));  // Asociamos las rutinas al entrenamiento

                workouts.add(workout);
            }

            // Guardamos la información en el archivo backup2.dat
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
            // Referencia a la subcolección de rutinas de un entrenamiento específico
            CollectionReference routinesCollection = db.collection("workouts")
                    .document(workoutId)  // Documento específico de un workout
                    .collection("routines");  // Subcolección de rutinas
            ApiFuture<QuerySnapshot> query = routinesCollection.get();
            QuerySnapshot querySnapshot = query.get();

            // Iteramos sobre las rutinas de ese entrenamiento
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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("backup2.dat"))) {
            // Serializamos la lista de entrenamientos en el archivo
            oos.writeObject(workouts);
            System.out.println("Datos guardados exitosamente en backup2.dat");
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
            // Referencia a la colección de usuarios en Firestore
            CollectionReference usersCollection = db.collection("users");
            ApiFuture<QuerySnapshot> query = usersCollection.get();
            QuerySnapshot querySnapshot = query.get();

            // Iteramos sobre los usuarios obtenidos
            for (QueryDocumentSnapshot document : querySnapshot) {
                Usuario usuario = document.toObject(Usuario.class);
                usuarios.add(usuario);
            }

            // Guardamos los usuarios en un archivo
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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("backup2.dat", true))) {
            // Serializamos la lista de usuarios en el archivo
            oos.writeObject(usuarios);
            System.out.println("Usuarios guardados exitosamente en backup2.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
