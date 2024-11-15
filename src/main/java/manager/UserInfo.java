package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.protobuf.Int32Value;

import accesoDatos.Reader;
import objects.HistoricalRecord;
import objects.Usuario;
import objects.Workout;
import manager.Login;


/**
 * given an email, used as userd identificaion, retrieves info from db and creates an objects usuario
 */
public class UserInfo {
	
	public Usuario getUserInfor(String email) throws Exception {

		Usuario miUsuario = new Usuario();
		//String email = Login.currentUser.getEmail();
		Long numSets = -2L;
		ArrayList<DocumentReference> refSets = new ArrayList<DocumentReference>();

		List<QueryDocumentSnapshot> usuarios = new Reader().findUsuariosFirebase();
		for (QueryDocumentSnapshot usuario : usuarios) {

			if (usuario.getString("email").equalsIgnoreCase(email)) {

				miUsuario.setId(usuario.getString("id"));
				miUsuario.setName(usuario.getString("name"));
				miUsuario.setLevel(usuario.getDouble("level").intValue());
				miUsuario.setProgress(usuario.getDouble("progress").intValue());
				miUsuario.setSurname(usuario.getString("surname"));
				miUsuario.setEmail(usuario.getString("email"));
				miUsuario.setPassword(usuario.getString("password"));
				miUsuario.setRegistered(usuario.getBoolean("registered"));
				DocumentReference wkRef = (DocumentReference) usuario.getData().get("ref_workouts");

				if (wkRef != null) {
				
					miUsuario.setWorkout(new Reader().getOneWorkout(wkRef));
				
				}

			}
		}
		//not created yet, so current user point to null, better load after succesfull login somewhere else
		//miUsuario.setWk_history(new Record().getAllRecords());

		return miUsuario;

	}

/**
 * relaods all info, gotta be used after changes are done, to use fresh info
 * loads historical record, as is not possible untill successfull login is completed
 * 
 * @throws Exception
 */
	public void reloadUserInfo() throws Exception {
		Login.currentUser = getUserInfor(Login.currentUser.getEmail()) ;
		loadRecordsQuick();

		
	}
	
	/**
	 * it loads historical records, faster option than loading the whle user
	 * @throws Exception
	 */
	public void loadRecordsQuick() throws Exception {

		Login.currentUser.setWk_history(new Record().getAllRecords());

		
	}
	
	
	
	/**
	 * gets info on a particular workout given the name
	 * @param idWorkout
	 * @param name
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void getWorkoutInfo(String idWorkout, String name) throws Exception {
		Workout miWorkout = new Workout();

		List<QueryDocumentSnapshot> workouts = new Reader().getAllWorkoutsFirebase();
		for (QueryDocumentSnapshot workout : workouts) {

			if (workout.getString("name").equalsIgnoreCase(name)) {

				miWorkout.setName(workout.getString("name"));
				miWorkout.setLevel(workout.getDouble("level").intValue());
				miWorkout.setNumSets(workout.getDouble("num_sets").intValue());
				
				DocumentReference SetRef = null;
				try {
					SetRef = (DocumentReference) workout.getData().get("ref_sets");
				} catch (Exception e) {
					
					e.printStackTrace();
				}

				if (SetRef != null) {
					System.out.println("FOUND REFERENCE: " + SetRef.getPath() + "in getWorkoutInfo. userinfo");

				}

			}
		}

	}
}
