package accesoDatos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import manager.Login;
import objects.Usuario;
import objects.Workout;
import objects.HistoricalRecord;
import objects.Routine;


public class Writer {
	
	public DocumentReference addHistoricalRecord(HistoricalRecord record) throws Exception {
		Firestore db = Connection.getDatabase();
		 record.setDate( new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date()));
		DocumentReference recordRef = db.collection("historicos").document();
		 ApiFuture<WriteResult> future = recordRef.set(record);
		
		 try {
		        
		        WriteResult result = future.get();
		        JOptionPane.showMessageDialog(null, "The record was added");

		        System.out.println("Documento agregado exitosamente en: " + result.getUpdateTime());
		    } catch (Exception e) {
		        System.out.println("Error al agregar el documento: " + e.getMessage());
		        throw e; 
		    }
		return recordRef;
		}
		
	
	public void updateUserRecords(ArrayList<DocumentReference> newRecords, DocumentReference newWorkout, Integer miLevel ) throws Exception {
		
		Firestore db = Connection.getDatabase();
		// Update an existing document. notice its written document "1", we are using the email
		DocumentReference docRef = db.collection("usuarios").document(Login.currentUser.getEmail());

		// (async) Update one field
		ApiFuture<WriteResult> future = docRef.update("wk_history", newRecords);
		ApiFuture<WriteResult> futureB = docRef.update("ref_workouts", newWorkout);
		ApiFuture<WriteResult> futureC = docRef.update("level", miLevel + 1);

		// ...
		WriteResult result = future.get();
		WriteResult resultB = futureB.get();
		WriteResult resultC = futureB.get();
		
		System.out.println("Write result: " + result);
		db.close();
	}
	

	/*public void addUser(Usuario usuario) throws Exception {

		Firestore db = Connection.getDatabase();

		

		DocumentReference recordRef = db.collection("usuarios").document(usuario.getEmail());
		);
		 ApiFuture<WriteResult> future = recordRef.set(usuario);
		
		 try {
		        
		        WriteResult result = future.get();
		        JOptionPane.showMessageDialog(null, "The user was added");
		        System.out.println("Documento agregado exitosamente en: " + result.getUpdateTime());
		    } catch (Exception e) {
		        System.out.println("Error al agregar el documento: " + e.getMessage());
		        throw e; 
		    }

		db.close();
	}*/

	public void addUser(Usuario usuario) throws Exception {
		
		Firestore db = Connection.getDatabase();
		
		CollectionReference usuarios = db.collection("usuarios");
		
		Map<String, Object> user = new HashMap<>();
		
		user.put("birthday", usuario.getBirthday());
		user.put("email", usuario.getEmail());
		
		user.put("level", 0);
		user.put("name", usuario.getName());
		user.put("password", usuario.getPassword());
		user.put("progress", 0);
		user.put("registered", true);
		user.put("ref_workouts", null);
		user.put("surname", usuario.getSurname());
		user.put("wk_history", null);
		user.put("isTrainer", usuario.isTrainer());
		user.put("ref_workouts", new Reader().getWK0Reference());
		
		DocumentReference userNew = usuarios.document(usuario.getEmail());
		
		userNew.set(user);
		
		//db.close();
	}
	
	
	public void addSets( Routine usuario) throws Exception {
	
		 
		Firestore db = Connection.getDatabase();

		CollectionReference usuarios = db.collection("sets");

		Map<String, Object> user = new HashMap<>();
		user.put("reps", usuario.getReps());
		user.put("description", usuario.getDescription());
		user.put("time", usuario.getTime());
		user.put("name", usuario.getName());
		if(usuario.getId().isBlank() == false)
			user.put("id", usuario.getId());

		

		DocumentReference userNew = usuarios.document();

		userNew.set(user);

		//db.close();
	}
	
}
