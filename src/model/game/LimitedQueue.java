package model.game;

import java.util.LinkedList;

public class LimitedQueue<E> extends LinkedList<E> {
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
}