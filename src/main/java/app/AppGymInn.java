package app;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import accesoDatos.*;
import backups.GuardarBackups;
import display.Frame;

public class AppGymInn {

	public static void main(String[] args) {
		try {
          
            new Frame().run();
            GuardarBackups backup = new GuardarBackups();
            
            try {
                backup.crearBackup();
                System.out.println("Backup realizado correctamente.");
            } catch (IOException | ExecutionException | InterruptedException e) {
                e.printStackTrace();
                System.out.println("Error al realizar el backup.");
            }
        } catch ( Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
