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
import pruebasAkira.GuardarBackups;

public class AppGymInn {

	public static void main(String[] args) {
		try {
			System.out.println("Running...");
			
			GuardarBackups guardarBackups = new GuardarBackups();
            guardarBackups.obtenerDatosYGuardarBackup();

            System.out.println("Running Frame...");
            new Frame().run();
            
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
