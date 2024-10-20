package accesoDatos;

import java.util.HashMap;
import java.util.Map;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;

import objects.Usuario;

public class Writer {

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
		
		DocumentReference userNew = usuarios.document();

		userNew.set(user);
		
	

		db.close();

		
		
	}

}
