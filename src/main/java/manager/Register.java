package manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import accesoDatos.AccesoDatos;
import objects.Usuario;

public class Register {

	public  Usuario getUserInfor(String user) throws InvalidClassException, StreamCorruptedException, ClassNotFoundException,FileNotFoundException
	, IOException, InterruptedException, ExecutionException{
		
		Usuario miUsuario = new Usuario();
		List<QueryDocumentSnapshot> usuarios = new AccesoDatos().encontrarUsuariosFirebase();
		for (QueryDocumentSnapshot usuario : usuarios) {
			   
		    if(usuario.getString("user").equalsIgnoreCase(user))
		    { 	
		    	miUsuario.setUser(usuario.getString("user"));
		    	miUsuario.setName(usuario.getString("name"));
		    	miUsuario.setSurname(usuario.getString("surname"));
		    	miUsuario.setPassword(usuario.getString("password"));
		    	miUsuario.setRegistered(usuario.getBoolean("registered"));
		    	
		    	    DocumentReference wkRef = (DocumentReference) usuario.getData().get("ref_workouts");
	
		    	    if(wkRef != null)
		    	    	miUsuario.setWorkout(wkRef.getId());
	
		System.out.println(miUsuario.toString() + "register - >get userinfo");
		  
		 
		}  
	}
		return miUsuario;

	
	}
}
