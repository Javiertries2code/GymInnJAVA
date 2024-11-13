package pruebasAkira;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.api.core.ApiFuture;
import objects.Routine;
import objects.Usuario;
import objects.Workout;
import accesoDatos.Backups;
import accesoDatos.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class GuardarBackups {
	private Firestore db;

    public GuardarBackups() {
        try {
            // Conectar a Firestore
            db = Connection.getDatabase();
        } catch (IOException e) {
            e.printStackTrace();  // Manejo de excepción de IO
        }
    }

    // Método para obtener los datos de Firestore y guardarlos en el backup
    public void obtenerDatosYGuardarBackup() throws ExecutionException, InterruptedException, IOException {
        // Obtener los datos desde Firestore
        List<Workout> workouts = obtenerWorkoutsDeFirestore();
        List<Routine> routines = obtenerRoutinesDeFirestore();
        List<Usuario> usuarios = obtenerUsuariosDeFirestore();

        // Instanciar la clase Backups y guardar los datos
        Backups backups = new Backups();
        backups.saveAllData(workouts, routines, usuarios);  // Guardar en el archivo backup.dat
    }

    // Método para obtener los entrenamientos desde Firestore
    private List<Workout> obtenerWorkoutsDeFirestore() throws ExecutionException, InterruptedException {
        List<Workout> workouts = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> query = db.collection("workouts").get();
            QuerySnapshot querySnapshot = query.get();

            for (QueryDocumentSnapshot document : querySnapshot) {
                Workout workout = document.toObject(Workout.class);
                workouts.add(workout);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw e;  // Propagar la excepción
        }
        return workouts;
    }

    // Método para obtener las rutinas desde Firestore
    private List<Routine> obtenerRoutinesDeFirestore() throws ExecutionException, InterruptedException {
        List<Routine> routines = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> query = db.collection("routines").get();
            QuerySnapshot querySnapshot = query.get();

            for (QueryDocumentSnapshot document : querySnapshot) {
                Routine routine = document.toObject(Routine.class);
                routines.add(routine);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw e;  // Propagar la excepción
        }
        return routines;
    }

    // Método para obtener los usuarios desde Firestore
    private List<Usuario> obtenerUsuariosDeFirestore() throws ExecutionException, InterruptedException {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> query = db.collection("users").get();
            QuerySnapshot querySnapshot = query.get();

            for (QueryDocumentSnapshot document : querySnapshot) {
                Usuario usuario = document.toObject(Usuario.class);
                usuarios.add(usuario);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw e;  // Propagar la excepción
        }
        return usuarios;
    }
}
