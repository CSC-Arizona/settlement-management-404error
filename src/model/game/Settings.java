package model.game;

public class Settings {
	public static final int SMALL = 0, MEDIUM = 1, LARGE = 2, EXTRA_LARGE = 3;
	public static final int EASY = 0, NORMAL = 1, HARD = 3, IMPOSSIBLE = 4;
	
	private int size;
	private int difficulty;
	
	public Settings(int size, int difficulty) {
		this.size = size;
		this.difficulty = difficulty;
	}

	public int getSize() {
		return size;
	}

	public int getDifficulty() {
		return difficulty;
	}
	
	
}
