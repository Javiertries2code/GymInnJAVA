package thread;


import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;

import objects.Routine;

public class Countdown  extends Thread {

	private long startTime;
	private long stoptTime;
	private long elapsedTime;
	private boolean running;
	private boolean pause;
	private Component chronoTextField;
	private Component countDownTextField;
	private ArrayList<Routine> list;
	private long elapsedTimeRemaning;
	
	private ArrayList<Long> timeRecords;
	private int accomplishment;
	private int setTime;

	public Countdown(Component countDownTextField, Component chronoTextField, ArrayList<Routine> arrayList) {
		super();
		this.startTime = 0L;
		this.stoptTime = 0L;
		this.elapsedTime = 0L;
		this.elapsedTimeRemaning = 0L;
		this.running = true;
		this.accomplishment =arrayList.size();
		this.chronoTextField = chronoTextField;
		this.countDownTextField = countDownTextField;
		this.list = arrayList;
		this.pause = true;
		this.timeRecords=  new ArrayList<Long>();
		this.setTime = 90000;
		
	}

	public void run() {

		startTime = System.currentTimeMillis();
		while (running ==true) {
			if (pause == true) {
				try {
					Thread.sleep(100);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				elapsedTime = (System.currentTimeMillis() - startTime) + elapsedTimeRemaning;
				((JFormattedTextField) countDownTextField).setValue( setTime - elapsedTime);
			} else
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	//to avoid a busy waiting	
				
			}

	}
	
	/**
	 * saves the executed time of the chrono in the array of time, will be added later
	 */
	private void newxtRoutine() {
		pause = true;
		running =true;
		startTime = 0L;
		stoptTime = 0L;
		timeRecords.add(elapsedTime);
		elapsedTime = 0L;
		
	}

	public void startStop() {
		if (pause == true) {
			pause = false;
			//System.out.println("elapsedTime = " + elapsedTime);
		//	System.out.println("elapsedTimeRemaning = " + elapsedTime);

		} else if (pause == false) {
			elapsedTimeRemaning = elapsedTime;

			startTime = System.currentTimeMillis();
			pause = true;
			//System.out.println("elapsedTimeRemaning = " + startTime);
		}

	}
	
	public void reset() {
		
		pause = false;
		running =false;
		startTime = 0L;
		stoptTime = 0L;
		elapsedTime = 0L;
		
	}

}
