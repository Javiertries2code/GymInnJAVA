package manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import accesoDatos.AccesoDatos;
import objects.Usuario;
import objects.Workout;
import manager.Login;

public class UserInfo {
	public void getUserInfor() throws InvalidClassException, StreamCorruptedException, ClassNotFoundException,
			FileNotFoundException, IOException, InterruptedException, ExecutionException {

		Usuario miUsuario = new Usuario();
		String email = Login.currentUser.getEmail();

		List<QueryDocumentSnapshot> usuarios = new AccesoDatos().findUsuariosFirebase();
		for (QueryDocumentSnapshot usuario : usuarios) {

			if (usuario.getString("email").equalsIgnoreCase(email)) {

				miUsuario.setName(usuario.getString("name"));
				miUsuario.setSurname(usuario.getString("surname"));
				miUsuario.setPassword(usuario.getString("password"));
				miUsuario.setRegistered(usuario.getBoolean("registered"));
				DocumentReference wkRef = (DocumentReference) usuario.getData().get("ref_workouts");

				if (wkRef != null) {
					// Imprimir la referencia
					System.out.println("FOUND REFERENCE: " + wkRef.getPath());

					try {
						// Obtener los datos del documento referenciado de manera síncrona
						DocumentSnapshot documentSnapshot = wkRef.get().get();

						// Verificar si el documento existe y luego imprimir los datos
						if (documentSnapshot.exists()) {
							String id = ""+documentSnapshot.getId();
							String name =  documentSnapshot.getString("name");
							
							System.out.println("Workout name: " + name);
							System.out.println("Workout id: " + id );
							// wk0, cause in the database, trainers will have workout wk0 assigned, so only
							// will retrieve data of others
							if (!id.equalsIgnoreCase("wk0"))
							{System.out.println(id  + "calling getwork");
								getWorkoutInfo("" + documentSnapshot.getId(), name);
							}
							// System.out.println("Workout name: " + documentSnapshot.getData());

						} else {
							System.out.println("getUserInfor()  -No workout data found for the reference.");
						}
					} catch (Exception e) {
						System.out.println("getUserInfor()  -Failed to retrieve workout data: " + e.getMessage());
					}
				}

			}
		}

	}

	private void getWorkoutInfo(String idWorkout, String name) throws InvalidClassException, StreamCorruptedException, ClassNotFoundException,
	FileNotFoundException, IOException, InterruptedException, ExecutionException {
System.out.println(idWorkout +"  " + name);
Workout miWorkout = new Workout();



List<QueryDocumentSnapshot> workouts = new AccesoDatos().findWorkoutsFirebase();
for (QueryDocumentSnapshot workout : workouts) {

	if (workout.getString("name").equalsIgnoreCase(name)) {

		miWorkout.setName(workout.getString("name"));
		miWorkout.setLevel(workout.getDouble("level").intValue());
		miWorkout.setNumSets(workout.getDouble("num_sets").intValue());
		System.out.println(miWorkout.toString());
		DocumentReference SetRef = null;
		 try {
			SetRef = (DocumentReference) workout.getData().get("ref_sets");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (SetRef != null) {
			System.out.println("FOUND REFERENCE: " + SetRef.getPath());

//			try {
//				// Obtener los datos del documento referenciado de manera síncrona
//				DocumentSnapshot documentSnapshot = SetRef.get().get();
//
//				// Verificar si el documento existe y luego imprimir los datos
//				if (documentSnapshot.exists()) {
//					System.out.println("Set name: " + documentSnapshot.getString("name"));
//					System.out.println("Set id: " + documentSnapshot.getId());
//					// wk0, cause in the database, trainers will have workout wk0 assigned, so 
//					// willonly retrieve data of other users
//				
//
//				} else {
//					System.out.println("getWorkoutInfo -No workout data found for the reference.");
//				}
//			} catch (Exception e) {
//				System.out.println("getWorkoutInfo - Failed to retrieve workout data: " + e.getMessage());
//			}
		}

	}
}

}
}
