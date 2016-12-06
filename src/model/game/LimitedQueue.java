package model.game;

import java.util.LinkedList;

public class LimitedQueue<E> extends LinkedList<E> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7312319540147746403L;
	private int limit;
	
	public LimitedQueue(int limit) {
		this.limit = limit;
	}

	@Override
	public boolean add(E o) {
		super.add(o);
		while (size() > limit) {
			super.remove();
		}
		return true;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (E e : this) {
			result += e.toString() + "\n\n";
		}
		return result.trim();
	}
	
	public void clear() {
		while (size() > 0) {
			super.remove();
		}
	}
}