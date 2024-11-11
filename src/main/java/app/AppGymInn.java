package app;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import accesoDatos.Backups;
import display.Frame;
import objects.Routine;
import objects.Usuario;
import objects.Workout;

public class AppGymInn {

	public static void main(String[] args) {
		try {
			System.out.println("Running...");

            System.out.println("Running Frame...");
            new Frame().run();
			
			//pruebas. Borrar
			List<Workout> workouts = new ArrayList<>();
			workouts.add(new Workout("w1", "Workout1", 3, 3, new ArrayList<>(), "https://www.youtube.com/watch?v=NfVUrG2wNG0")); // Ejemplo de Workout
            
            List<Routine> routines = new ArrayList<>();
            routines.add(new Routine("1", "Routine1", "Description", 30, 10, 60)); // Ejemplo de Routine
            
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(new Usuario("1", "User1", "Surname", "01-01-2000", "password", "user1@example.com", true, 1, 50, false, null, null)); // Ejemplo de Usuario
            
            // Instanciar Backups y llamar a saveAllData
            Backups backups = new Backups();
            backups.saveAllData(workouts, routines, usuarios);
            
            // Leer y mostrar el contenido del archivo backup.dat
            displayBackupContent("backup.dat");
            
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//metodo de prueba. Borrar
	public static void displayBackupContent(String filename) {
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
	        // Intentar leer los objetos hasta el final
	        while (true) {
	            try {
	                // Leer cada objeto del archivo
	                Object data = ois.readObject();
	                System.out.println("Contenido del archivo: " + data);
	            } catch (EOFException e) {
	                // Se alcanza el final del archivo
	                System.out.println("Fin del archivo alcanzado.");
	                break;  // Salir del ciclo cuando se alcanza el final
	            }
	        }
	    } catch (IOException | ClassNotFoundException e) {
	        System.out.println("Error al leer el archivo: " + e.getMessage());
	    }
	}
}
