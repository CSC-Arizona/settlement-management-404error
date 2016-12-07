/**
 * 
 */
package controller;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Jonathon Davis
 *
 */
public class SongPlayer extends Thread {
	public static int MAIN = 0, ATTACK = 1;
	private static int currentSong;
	private static int newSong;
	private static Clip current, main, attack;
	private static SongPlayerThread thread;
	
	public SongPlayer(){
		try {
			AudioInputStream mainIO = AudioSystem.getAudioInputStream(new File("src/resources/sounds/main.wav").getAbsoluteFile());
			main = AudioSystem.getClip();
			main.open(mainIO);
			main.loop(Clip.LOOP_CONTINUOUSLY);
			main.stop();
			AudioInputStream attackIO = AudioSystem.getAudioInputStream(new File("src/resources/sounds/attack.wav").getAbsoluteFile());
			attack = AudioSystem.getClip();
			attack.open(attackIO);
			attack.loop(Clip.LOOP_CONTINUOUSLY);
			attack.stop();
			currentSong = 0;
			thread = new SongPlayerThread(main);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		thread.run();
		while(true){
			if(getCurrentSong() != getNewSong()){
				thread.playingSong.stop();
				if(getNewSong() == MAIN)
					thread.playingSong = main;
				if(getNewSong() == ATTACK)
					thread.playingSong = attack;
				thread.run();
				thread.playingSong.loop(Clip.LOOP_CONTINUOUSLY);
				setCurrentSong(getNewSong());
			}
		}
	}
	
	public synchronized static void setNewSong(int song){
		newSong = song;
	}
	
	private synchronized static int getNewSong(){
		return newSong;
	}
	
	private synchronized static void setCurrentSong(int song){
		currentSong = song;
	}
	
	private synchronized static int getCurrentSong(){
		return currentSong;
	}
	
	private class SongPlayerThread implements Runnable{
		
		public Clip playingSong;

		public SongPlayerThread(Clip playingSong) {
			super();
			this.playingSong = playingSong;
		}

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			playingSong.start();
		}
		
	}

}
