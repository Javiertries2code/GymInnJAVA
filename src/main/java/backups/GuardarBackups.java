package backups;

import accesoDatos.Connection;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import objects.HistoricalRecord;
import objects.Routine;
import objects.Usuario;
import objects.Workout;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GuardarBackups {

    public List<Usuario> cargarUsuarios() throws ExecutionException, InterruptedException, IOException {
        Firestore db = Connection.getDatabase();
        QuerySnapshot querySnapshot = db.collection("usuarios").get().get();
        List<Usuario> usuarios = new ArrayList<>();

        for (QueryDocumentSnapshot document : querySnapshot) {
            Usuario usuario = document.toObject(Usuario.class);
            List<DocumentReference> referenciasHistoricas = (List<DocumentReference>) document.get("wk_history");

            if (referenciasHistoricas != null) {
                ArrayList<HistoricalRecord> historial = new ArrayList<>();
                for (DocumentReference ref : referenciasHistoricas) {
                    DocumentSnapshot historicalSnapshot = ref.get().get();
                    HistoricalRecord historicalRecord = historicalSnapshot.toObject(HistoricalRecord.class);
                    historial.add(historicalRecord);
                }
                usuario.setWk_history(historial);
            }

            usuarios.add(usuario);
        }

        return usuarios;
    }
    
    public void crearBackup() throws ExecutionException, InterruptedException, IOException {
        //List<Usuario> usuarios = cargarUsuarios();
        List<Workout> workouts = cargarWorkouts();
        List<Routine> routines = cargarRoutines();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("backup.dat"))) {
           // out.writeObject(usuarios);
            out.writeObject(workouts);
            out.writeObject(routines);
        }
    }

    public List<Workout> cargarWorkouts() throws ExecutionException, InterruptedException, IOException {
        Firestore db = Connection.getDatabase();
        QuerySnapshot querySnapshot = db.collection("workouts").get().get();
        List<Workout> workouts = new ArrayList<>();
        for (QueryDocumentSnapshot document : querySnapshot) {
            Workout workout = document.toObject(Workout.class);
            workouts.add(workout);
        }

        return workouts;
    }

    public List<Routine> cargarRoutines() throws ExecutionException, InterruptedException, IOException {
        Firestore db = Connection.getDatabase();
        QuerySnapshot querySnapshot = db.collection("routines").get().get();
        List<Routine> routines = new ArrayList<>();
        for (QueryDocumentSnapshot document : querySnapshot) {
            Routine routine = document.toObject(Routine.class);
            routines.add(routine);
        }

        return routines;
    }

}