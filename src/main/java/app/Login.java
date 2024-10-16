package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;

import com.google.cloud.firestore.QueryDocumentSnapshot;

import accesoDatos.AccesoDatos;

public class Login {

	public boolean verifyUser(String user, String password) throws InvalidClassException, StreamCorruptedException, ClassNotFoundException
	, FileNotFoundException, IOException, InterruptedException, ExecutionException {
		
		List<QueryDocumentSnapshot> usuarios = new AccesoDatos().encontrarUsuariosFirebase();
		
		return findUserList(user, password, usuarios);
		
	}

	private boolean findUserList(String user, String password, List<QueryDocumentSnapshot> usuarios) {

		for (QueryDocumentSnapshot usuario : usuarios) {
		   
		    if(usuario.getString("user").equalsIgnoreCase(user) && usuario.getString("password").equals(password))
		    { System.out.println("ID: " + usuario.getId());
		    System.out.println("Nombre: " + usuario.getString("user"));
		    System.out.println("password: " + usuario.getString("password"));
			//JOptionPane.showMessageDialog("Login result", "Nombre: " + usuario.getString("user logueado"));
		    return true;
		    }
		}
		JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
		return false;
	}
	
}
