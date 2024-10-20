package manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;

import com.google.cloud.firestore.QueryDocumentSnapshot;

import accesoDatos.Reader;
import objects.Usuario;

public class Login {
	
	public static Usuario currentUser =null;
	

	public boolean verifyUser(String user, String password) throws InvalidClassException, StreamCorruptedException, ClassNotFoundException
	, FileNotFoundException, IOException, InterruptedException, ExecutionException {
		
		List<QueryDocumentSnapshot> usuarios = new Reader().findUsuariosFirebase();
		
		return findUserList(user, password, usuarios);
		
	}

	/**
	 * We are using email as a user for validation,  we could add also the autogenerated  user id, as it is unique I believe
	 * 
	 * @param user user inserted in a textfiel in login panel
	 * @param passwordinserted in a textfiel in login panel
	 * @param usuarios list of usuarios retirve from firebase
	 * @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @throws StreamCorruptedException 
	 * @throws InvalidClassException 
	 */
	private boolean findUserList(String email, String password, List<QueryDocumentSnapshot> usuarios) throws InvalidClassException, StreamCorruptedException, ClassNotFoundException, FileNotFoundException, IOException, InterruptedException, ExecutionException {

		for (QueryDocumentSnapshot usuario : usuarios) {
		   
		    if(usuario.getString("email").equalsIgnoreCase(email)  && usuario.getString("password").equals(password))
		    {  
		    	currentUser = new UserInfo().getUserInfor(email);
		  		    return true;
		    }
		}
		return false;
	}

	private void loadCurrentUser(QueryDocumentSnapshot usuario) {
		//currentUser = new Usuario();


		currentUser.setName(usuario.getString("name"));
		currentUser.setSurname(usuario.getString("surname"));
		currentUser.setTrainer(usuario.getBoolean("isTrainer"));
		currentUser.setLevel(usuario.getDouble("level").intValue());
		currentUser.setEmail(usuario.getString("email"));
		
		
	}
	
}
