package manager;

import java.util.List;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import accesoDatos.Reader;
import accesoDatos.Writer;
import objects.Usuario;

public class Register {

	public void addNewUser(Usuario usuario) throws Exception {

		if(findDuplicate(usuario) == true)
		{
			new Writer().addUser(usuario);
		}
		
	}

	private boolean findDuplicate(Usuario usuario) throws Exception {
		for(QueryDocumentSnapshot user: new Reader().findUsuariosFirebase()) {
			if (user.getString("email").equalsIgnoreCase(usuario.getEmail()))
			{
				
				return false;
			}
			
		}
		return true;
		
	}


}
