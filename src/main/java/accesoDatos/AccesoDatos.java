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
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class AccesoDatos  implements DatosInterface{

	 private static Firestore db = null;
	 private static final String FIREBASE_JASON = "C:\\Users\\Javier\\eclipse-workspace\\GymInn\\gymconection.json";
	 
	 /**
	  * Singleton to stablish, if not yet running, a connection to the Firebase db
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
	     * Stablish a connection to the Firebase db, using the above declared static route to the json connection fiel
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
		    .setProjectId("gyminn-389ca")
		    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
		    .build();

		 db = firestoreOptions.getService();	
		if(db == null)
			System.out.println("got null in getConnection");
		
		}
/**
 * Retrieves a List<QueryDocumentSnapshot> with the users
 */

	//TO-DO  Make it genereric as to retrieve and return any tipe of entity provided as a param
	@Override
	public List<QueryDocumentSnapshot> encontrarUsuariosFirebase() throws InvalidClassException, StreamCorruptedException,
			ClassNotFoundException, FileNotFoundException, IOException, InterruptedException, ExecutionException {
		
		db = getDatabase();
		ApiFuture<QuerySnapshot> query = db.collection("usuarios").get();

		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> departamentos = querySnapshot.getDocuments();
		
		
		return departamentos;
	}

	
	
}
