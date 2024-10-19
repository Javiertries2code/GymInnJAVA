package accesoDatos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import objects.Workout;

public interface FirebaseDatosInterface<T> {

	
	public List<T> findUsuariosFirebase() throws InvalidClassException,StreamCorruptedException , ClassNotFoundException, FileNotFoundException, IOException, InterruptedException, ExecutionException;

	public List<QueryDocumentSnapshot> findWorkoutsFirebase() throws InvalidClassException, StreamCorruptedException,
			ClassNotFoundException, FileNotFoundException, IOException, InterruptedException, ExecutionException;

	public Workout FindOneWorkout(DocumentReference docRef) throws InterruptedException, ExecutionException;


	
}
