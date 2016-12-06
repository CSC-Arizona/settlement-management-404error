package model.game;

public class Log {

	private static int messageLimit = 50;
	private static LimitedQueue<String> log = new LimitedQueue<>(messageLimit);
	
	public static String getLog() {
		return log.toString();
	}
	
	public static boolean add(String s) {
		return log.add(s);
	}
	
	public static void clear() {
		log.clear();
	}
	
}
