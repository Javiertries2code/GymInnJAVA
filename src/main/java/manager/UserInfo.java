package manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.protobuf.Int32Value;

import accesoDatos.Reader;
import objects.Usuario;
import objects.Workout;
import manager.Login;

public class UserInfo {
	
	public Usuario getUserInfor(String email) throws InvalidClassException, StreamCorruptedException, ClassNotFoundException,
			FileNotFoundException, IOException, InterruptedException, ExecutionException {

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
				miUsuario.setPassword(usuario.getString("password"));
				miUsuario.setRegistered(usuario.getBoolean("registered"));
				DocumentReference wkRef = (DocumentReference) usuario.getData().get("ref_workouts");

				if (wkRef != null) {
					// Imprimir la referencia
//					System.out.println("FOUND REFERENCE: " + wkRef);
//
//					System.out.println("FOUND REFERENCE: " + wkRef.getPath());
					miUsuario.setWorkout(new Reader().getOneWorkout(wkRef));
				
				}

			}
		}
		return miUsuario;

	}

	private void getWorkoutInfo(String idWorkout, String name) throws Exception {
		//System.out.println(idWorkout + "  " + name);
		Workout miWorkout = new Workout();

		List<QueryDocumentSnapshot> workouts = new Reader().getAllWorkoutsFirebase();
		for (QueryDocumentSnapshot workout : workouts) {

			if (workout.getString("name").equalsIgnoreCase(name)) {

				miWorkout.setName(workout.getString("name"));
				miWorkout.setLevel(workout.getDouble("level").intValue());
				miWorkout.setNumSets(workout.getDouble("num_sets").intValue());
				//System.out.println(miWorkout.toString());
				DocumentReference SetRef = null;
				try {
					SetRef = (DocumentReference) workout.getData().get("ref_sets");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (SetRef != null) {
					System.out.println("FOUND REFERENCE: " + SetRef.getPath());

				}

			}
		}

	}
}
