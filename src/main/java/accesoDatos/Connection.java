package accesoDatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

public class Connection {

	private static Firestore db = null;
	private static final String FIREBASE_JASON = "..\\GymInnJAVA\\gymconection.json";

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
	
	
}
