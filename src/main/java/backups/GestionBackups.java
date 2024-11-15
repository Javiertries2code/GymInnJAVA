package backups;

import accesoDatos.Reader;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import objects.HistoricalRecord;
import objects.Routine;
import objects.Usuario;
import objects.Workout;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionBackups {

	 private static final String BACKUP_FILE = "backups.dat";
	    /**
	     * Creates a backup by fetching data from Firestore and saving it to a file.
	     */
	    public void createBackup() throws Exception {
	        Reader reader = new Reader();

	        List<QueryDocumentSnapshot> usuariosSnapshot = reader.findUsuariosFirebase();
	        List<QueryDocumentSnapshot> workoutsSnapshot = reader.getAllWorkoutsFirebase();
	        List<Routine> routines = fetchAllRoutines(reader, workoutsSnapshot);

	        List<Usuario> usuarios = convertSnapshotsToUsuarios(usuariosSnapshot);
	        List<Workout> workouts = convertSnapshotsToWorkouts(workoutsSnapshot);

	        saveToFile(usuarios, workouts, routines);
	    }

	    /**
	     * Converts Firestore documents to a list of Usuario objects.
	     */
	    public List<Usuario> convertSnapshotsToUsuarios(List<QueryDocumentSnapshot> snapshots) throws Exception {
	        List<Usuario> usuarios = new ArrayList<>();

	        for (QueryDocumentSnapshot snapshot : snapshots) {
	            Usuario usuario = snapshot.toObject(Usuario.class);

	            List<DocumentReference> wkHistoryRefs = (List<DocumentReference>) snapshot.get("wk_history");
	            if (wkHistoryRefs != null) {
	                ArrayList<HistoricalRecord> wkHistory = new ArrayList<>();
	                for (DocumentReference ref : wkHistoryRefs) {
	                    try {
	                        HistoricalRecord record = ref.get().get().toObject(HistoricalRecord.class);
	                        wkHistory.add(record);
	                    } catch (Exception e) {
	                        System.err.println("Error fetching wk_history document: " + e.getMessage());
	                    }
	                }
	                usuario.setWk_history(wkHistory);
	            } else {
	                usuario.setWk_history(new ArrayList<>());
	            }

	            usuarios.add(usuario);
	        }
	        return usuarios;
	    }
	    /**
	     * Converts Firestore documents to a list of Workout objects.
	     */
	    private List<Workout> convertSnapshotsToWorkouts(List<QueryDocumentSnapshot> snapshots) {
	        List<Workout> workouts = new ArrayList<>();
	        for (QueryDocumentSnapshot snapshot : snapshots) {
	            Workout workout = snapshot.toObject(Workout.class);
	            workouts.add(workout);
	        }
	        return workouts;
	    }

	    /**
	     * Fetches all routines associated with the workouts.
	     */
	    private List<Routine> fetchAllRoutines(Reader reader, List<QueryDocumentSnapshot> workoutsSnapshot) throws Exception {
	        List<Routine> routines = new ArrayList<>();
	        for (QueryDocumentSnapshot workoutSnapshot : workoutsSnapshot) {
	            List<DocumentReference> refSets = (List<DocumentReference>) workoutSnapshot.get("ref_sets");
	            if (refSets != null) {
	                for (DocumentReference refSet : refSets) {
	                    Routine routine = reader.getSets(refSet);
	                    routines.add(routine);
	                }
	            }
	        }
	        return routines;
	    }

	    /**
	     * Serializes and saves the data to a file.
	     */
	    private void saveToFile(List<Usuario> usuarios, List<Workout> workouts, List<Routine> routines) {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BACKUP_FILE))) {
	            oos.writeObject(usuarios);
	            oos.writeObject(workouts);
	            oos.writeObject(routines);
	        } catch (IOException e) {
	            System.err.println("Error saving to backup file: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	
}
