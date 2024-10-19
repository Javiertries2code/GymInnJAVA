package accesoDatos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import objects.Set;
import objects.Workout;

public class AccesoDatos implements FirebaseDatosInterface {

	private static Firestore db = null;
	private static final String FIREBASE_JASON = "C:\\Users\\Javier\\eclipse-workspace\\GymInn\\gymconection.json";

	/**
	 * Singleton to stablish, if not yet running, a connection to the Firebase db
	 * 
	 * @return
	 * @throws IOException
	 */
	public static Firestore getDatabase() throws IOException {
		if (db == null) {
			getConnection();
		}
		return db;
	}

	/**
	 * Stablish a connection to the Firebase db, using the above declared static
	 * route to the json connection fiel
	 * 
	 * @throws IOException
	 */
	private static void getConnection() throws IOException {
		FileInputStream serviceAccount = null;

		try {
			serviceAccount = new FileInputStream(FIREBASE_JASON);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
				.setProjectId("gyminn-389ca").setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

		db = firestoreOptions.getService();
		if (db == null)
			System.out.println("got null in getConnection");

	}

	/**
	 * Retrieves a List<QueryDocumentSnapshot> with the users
	 */

	// TO-DO Make it genereric as to retrieve and return any tipe of entity provided
	// as a param
	@Override
	public List<QueryDocumentSnapshot> findUsuariosFirebase() throws InvalidClassException, StreamCorruptedException,
			ClassNotFoundException, FileNotFoundException, IOException, InterruptedException, ExecutionException {

		db = getDatabase();
		ApiFuture<QuerySnapshot> query = db.collection("usuarios").get();

		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> usuarios = querySnapshot.getDocuments();

		return usuarios;
	}

	@Override
	public List<QueryDocumentSnapshot> findWorkoutsFirebase() throws InvalidClassException, StreamCorruptedException,
			ClassNotFoundException, FileNotFoundException, IOException, InterruptedException, ExecutionException {

		db = getDatabase();
		ApiFuture<QuerySnapshot> query = db.collection("workouts").get();

		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> workouts = querySnapshot.getDocuments();

		return workouts;
	}

	@Override
	public Workout FindOneWorkout(DocumentReference docRef) throws InterruptedException, ExecutionException {

		Workout workout = new Workout();

		ApiFuture<DocumentSnapshot> future = docRef.get();
		// block on response
		DocumentSnapshot document = future.get();
		ArrayList<Set> refSets = new ArrayList<Set>();
		ArrayList<DocumentReference> refSetsDoc = new ArrayList<DocumentReference>();

		if (document.exists()) {
			workout.setId(document.getId());
			workout.setName(document.getString("name"));
			workout.setLevel(document.getDouble("level").intValue());
			workout.setNumSets(document.getDouble("num_sets").intValue());
			workout.setUrl(document.getString("url"));
			workout.setRefSets(refSets);

			Map<String, Object> workoutUsuario = document.getData();
			for (Map.Entry<String, Object> entry : workoutUsuario.entrySet()) {
				// System.out.println(entry.getKey() + " => " + entry.getValue() +" " +
				// entry.getValue().getClass());

				if (entry.getKey().equalsIgnoreCase("ref_sets")) {
					refSetsDoc = (ArrayList<DocumentReference>) entry.getValue();
				}
			}

		} else {
			System.out.println("No such workout found!");
		}

		for (DocumentReference doc : refSetsDoc) {
			refSets.add(getSets(doc));

		}
		// just testing
//		 for(Set set: workout.getRefSets())
//			 System.out.println("new set added find one workout" + set.getName());

		return workout;
	}

	public Set getSets(DocumentReference docRef) throws InterruptedException, ExecutionException {

		ApiFuture<DocumentSnapshot> future = docRef.get();
		// block on response
		DocumentSnapshot document = future.get();
		Set set = null;
		if (document.exists()) {
			// convert document to POJO
			set = document.toObject(Set.class);
			System.out.println(set.toString());
		} else {
			System.out.println("No such set found!");
		}

		return set;
	}
}
