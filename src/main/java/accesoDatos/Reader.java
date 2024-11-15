package accesoDatos;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import manager.Login;
import objects.HistoricalRecord;
import objects.Routine;
import objects.Usuario;
import objects.Workout;

public class Reader implements FirebaseReaderInterface {

	private static Firestore db = null;
	private static final String FIREBASE_JASON = "gymconection.json";

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
	 * 
	 * @throws Exception
	 */

	// TO-DO Make it genereric as to retrieve and return any tipe of entity provided
	// as a param
	@Override
	public List<QueryDocumentSnapshot> findUsuariosFirebase() throws Exception {

		Firestore db = Connection.getDatabase();

		ApiFuture<QuerySnapshot> query = db.collection("usuarios").get();

		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> usuarios = querySnapshot.getDocuments();
		//db.close();
		return usuarios;
	}

	/**
	 * this method is gonna RETURN THE WK0 WORKOUT, it would fail with a different
	 * name
	 * 
	 * @param wkName
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public DocumentReference getWK0Reference() throws InterruptedException, ExecutionException, IOException {
		Firestore db = Connection.getDatabase();

		DocumentReference wkRef = db.collection("workouts").document("wk0");
		ApiFuture<DocumentSnapshot> query = wkRef.get();
		DocumentSnapshot document = query.get();

		if (document.exists()) {
			return wkRef;
		} else {
			System.out.println("Documento no existe");
			return null;
		}
	}

	/**
	 * realod the info of the users workouts, and current level, neccesary to make
	 * sure that we are using current data Not working by now
	 * 
	 * @throws IOException
	 *
	 */
	@Override
	public void reloadWorkout() throws InterruptedException, ExecutionException, IOException {
		Firestore db = Connection.getDatabase();

		ApiFuture<DocumentSnapshot> query = db.collection("usuarios").document(Login.currentUser.getId()).get();
		DocumentSnapshot document = query.get();

		System.out.println("reloadWorkout BEFORE" + Login.currentUser.getLevel());

		Login.currentUser.setLevel(((DocumentSnapshot) document.getData()).getDouble("level").intValue());

		DocumentReference wkRef = (DocumentReference) document.getData().get("ref_workouts");
		Login.currentUser.setWorkout(new Reader().getOneWorkout(wkRef));
		System.out.println("reloadWorkout AFTER" + Login.currentUser.getLevel());
	}

	/**
	 * retrieves all workouts available in db, and returns the
	 * List<QueryDocumentSnapshot>
	 * 
	 * @throws Exception
	 */
	@Override
	public List<QueryDocumentSnapshot> getAllWorkoutsFirebase() throws Exception {

		Firestore db = Connection.getDatabase();

		ApiFuture<QuerySnapshot> query = db.collection("workouts").get();

		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> workouts = querySnapshot.getDocuments();
		//db.close();

		return workouts;
	}

	public DocumentReference getOneLevelHigherWkRef(Integer levelSearch) throws Exception {

		DocumentReference nextWkRef = null;

		Firestore db = Connection.getDatabase();

		ApiFuture<QuerySnapshot> query = null;
		if (levelSearch == -1) {
			query = db.collection("workouts").whereEqualTo("level", (Login.currentUser.getLevel() + 1))
					.orderBy("level", Query.Direction.ASCENDING).get();

		} else {
			query = db.collection("workouts").whereEqualTo("level", (levelSearch + 1))
					.orderBy("level", Query.Direction.ASCENDING).get();
		}
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> workouts = querySnapshot.getDocuments();
		// we know there will be only one is db, as for current design
		for (QueryDocumentSnapshot workout : workouts) {
			nextWkRef = workout.getReference();
		}
		db.close();
		return nextWkRef;

	}

	/**
	 * Returns an arraylist with all the workouts of the same level and lower that
	 * the currentUsers holds (hence we reload beforehand)
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Workout> getSameLowerLevelWorkouts(int levelSearch) throws Exception {

		// reloadWorkout();

		ArrayList<Workout> historyWorkouts = new ArrayList<Workout>();
		Firestore db = Connection.getDatabase();

		ApiFuture<QuerySnapshot> query = null;
		if (levelSearch == -1) {
			query = db.collection("workouts").whereLessThanOrEqualTo("level", Login.currentUser.getLevel())
					.orderBy("level", Query.Direction.DESCENDING).get();

		} else {
			query = db.collection("workouts").whereEqualTo("level", levelSearch)
					.orderBy("level", Query.Direction.DESCENDING).get();
		}
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> workouts = querySnapshot.getDocuments();

		for (QueryDocumentSnapshot workout : workouts) {
			historyWorkouts.add(getOneWorkout(workout.getReference()));
		}

		// Se tiene que cerrar pero como no la abre, lo dejo asi de momento, luego lo
		// arreglo
		// db.close();
		return historyWorkouts;

	}

	/**
	 * get aworkout containing all the reference sets
	 * 
	 * @throws IOException
	 */
	@Override
	public Workout getOneWorkout(DocumentReference docRef)
			throws InterruptedException, ExecutionException, IOException {
		Firestore db = Connection.getDatabase();
		Workout workout = new Workout();

		ApiFuture<DocumentSnapshot> future = docRef.get();

		DocumentSnapshot document = future.get();
		ArrayList<Routine> refSets = new ArrayList<Routine>();
		ArrayList<DocumentReference> refSetsDoc = new ArrayList<DocumentReference>();

		if (document.exists()) {
			workout.setId(document.getId());
			workout.setName(document.getString("name"));
			workout.setLevel(document.getDouble("level").intValue());
			workout.setNumSets(document.getDouble("num_sets").intValue());
			workout.setUrl(document.getString("url"));
			workout.setRefSets(refSets);
			// refSetsDoc = (ArrayList<DocumentReference>)
			// document.getData().get("ref_sets");

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

		return workout;
	}

	/**
	 * it retrieves all the info of a user, and packs it in a map to be returned
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getCurrentUserDataMap() throws Exception {
		// for testing
		Firestore dB = Connection.getDatabase();
		DocumentReference docRef = dB.collection("usuarios").document(Login.currentUser.getEmail());
//		DocumentReference docRef = dB.collection("usuarios").document(Login.currentUser.getId());
		Usuario usuario = new Usuario();
		Map<String, Object> miUsuarioMap = null;
		ApiFuture<DocumentSnapshot> future = docRef.get();

		DocumentSnapshot document = future.get();
		// ArrayList<HistoricalRecord> records = new ArrayList<HistoricalRecord>();
		// ArrayList<DocumentReference> recordsDoc = new ArrayList<DocumentReference>();

		if (document.exists()) {
			miUsuarioMap = document.getData();

			miUsuarioMap.put("refTOUSer", docRef);
			for (Map.Entry<String, Object> entry : miUsuarioMap.entrySet()) {

				System.out.println(entry.getKey() + " => " + entry.getValue());

			}
		} else {
			System.out.println("No such usuario found!");
		}
	//	dB.close();
		return miUsuarioMap;
	}

	public HistoricalRecord getOneHistoricalRecord(DocumentReference docRef)
			throws InterruptedException, ExecutionException {

		ApiFuture<DocumentSnapshot> future = docRef.get();
		// block on response
		DocumentSnapshot document = future.get();
		HistoricalRecord record = null;
		if (document.exists()) {
			// convert document to POJO
			record = document.toObject(HistoricalRecord.class);
			// System.out.println(record.toString());
		} else {
			System.out.println("No such record found!");
		}

		return record;
	}

	public Routine getSets(DocumentReference docRef) throws InterruptedException, ExecutionException {

		ApiFuture<DocumentSnapshot> future = docRef.get();
		// block on response
		DocumentSnapshot document = future.get();
		Routine routine = null;
		if (document.exists()) {
			// convert document to POJO
			routine = document.toObject(Routine.class);
			// System.out.println(routine.toString());
		} else {
			System.out.println("No such set found!");
		}

		return routine;
	}
}
