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
	/**
     * Guarda los datos de entrenamiento (workouts), rutinas y usuarios en un archivo.
     * 
     * @param workouts lista de entrenamientos a guardar
     * @param routines lista de rutinas a guardar
     * @param usuarios lista de usuarios a guardar
     */
    public void saveAllData(List<Workout> workouts, List<Routine> routines, List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("backup.dat"))) {
            // Guardar los entrenamientos
            oos.writeObject(workouts);
            // Guardar las rutinas
            oos.writeObject(routines);
            // Guardar los usuarios
            oos.writeObject(usuarios);
            System.out.println("Datos guardados exitosamente en backup.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
