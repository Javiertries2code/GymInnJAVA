package accesoDatos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.google.cloud.firestore.QueryDocumentSnapshot;

import objects.Routine;
import objects.Usuario;
import objects.Workout;

public class Backups {
	
	public void saveData(List<?> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("backup.dat", false))) {
            oos.writeObject(data);  // Serializa la lista de datos
            System.out.println("Datos guardados exitosamente en backup.dat");
        } catch (IOException e) {
            System.err.println("Error guardando datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para guardar las listas de Workout, Routine y Usuario al mismo tiempo
    public void saveAllData(List<Workout> workouts, List<Routine> routines, List<Usuario> usuarios) {
        // Abrir un nuevo archivo para sobrescribirlo completamente y guardar todos los objetos
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("backup.dat", false))) {
            // Guardar workouts
            oos.writeObject(workouts);  // Serializa la lista de workouts
            System.out.println("Workouts guardados exitosamente en backup.dat");

            // Guardar routines
            oos.writeObject(routines);  // Serializa la lista de routines
            System.out.println("Routines guardadas exitosamente en backup.dat");

            // Guardar usuarios
            oos.writeObject(usuarios);  // Serializa la lista de usuarios
            System.out.println("Usuarios guardados exitosamente en backup.dat");

        } catch (IOException e) {
            System.err.println("Error guardando datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /*
	// Método para guardar la lista de workouts en un archivo de respaldo
    public void saveWorkouts(List<Workout> workouts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("backup.dat"))) {
            oos.writeObject(workouts);  // Serializa la lista de workouts
            System.out.println("Workouts guardados exitosamente en backup.dat");
        } catch (IOException e) {
            System.err.println("Error guardando workouts: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void saveRoutines(List<Routine> routines) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("backup.dat", true))) {
            oos.writeObject(routines);  // Serializa la lista de routines
            System.out.println("Routines guardadas exitosamente en backup.dat");
        } catch (IOException e) {
            System.err.println("Error guardando routines: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void saveUsuarios(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("backup.dat", true))) {
            oos.writeObject(usuarios);  // Serializa la lista de usuarios
            System.out.println("Usuarios guardados exitosamente en backup.dat");
        } catch (IOException e) {
            System.err.println("Error guardando usuarios: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void saveAllData(List<Workout> workouts, List<Routine> routines, List<Usuario> usuarios) {
        saveWorkouts(workouts);
        saveRoutines(routines);
        saveUsuarios(usuarios);
    }
    */
}
