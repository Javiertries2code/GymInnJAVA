package app;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import accesoDatos.*;
import display.Frame;

public class AppGymInn {

	public static void main(String[] args) {
		try {
			System.out.println("runing");
			new Frame().run();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
