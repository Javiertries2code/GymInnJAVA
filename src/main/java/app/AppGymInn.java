package app;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import accesoDatos.*;
import display.Frame;

import backups.GestionBackups;

public class AppGymInn {

	public static void main(String[] args) {
		try {
			System.out.println("runing");
			new Frame().run();
			
			System.out.println("Starting backup...");
            GestionBackups backupManager = new GestionBackups();
            backupManager.createBackup();
            System.out.println("Backup completed successfully!");
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
