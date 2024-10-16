package accesoDatos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface DatosInterface<T> {

	
	public List<T> encontrarUsuariosFirebase() throws InvalidClassException,StreamCorruptedException , ClassNotFoundException, FileNotFoundException, IOException, InterruptedException, ExecutionException;

}
