package manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

	private boolean findDuplicate(Usuario usuario) throws InvalidClassException, StreamCorruptedException, ClassNotFoundException, FileNotFoundException, IOException, InterruptedException, ExecutionException {
		for(QueryDocumentSnapshot user: new Reader().findUsuariosFirebase()) {
			if (user.getString("name").equalsIgnoreCase(usuario.getEmail()))
			{
				
				return false;
			}
			
		}
		return true;
		
	}


}
