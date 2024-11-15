package app;

import backups.GuardarBackups;
import display.Frame;

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
