package tankbattle.core.others;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import tankbattle.core.control.Named;

/**
 * 本类是一个组，大多数情况下你能把它当做一个Set用<br>
 * 
 * @author Gogo
 *
 * @param <T>
 */
public class Group<T> {

	protected Set<T> set = Collections.synchronizedSet(new HashSet<>());

	protected Lock lock = new ReentrantLock();

	public Set<T> getAll() {
		lock.lock();
		Set<T> t = new HashSet<>(set);
		lock.unlock();
		return t;
	}

	public Group<T> add(T e) {
		lock.lock();
		set.add(e);
		lock.unlock();
		return this;
	}

	public Group<T> remove(T e) {
		if (e == null) {
			return this;
		}
		lock.lock();
		for (Iterator<T> i = set.iterator(); i.hasNext();) {
			if (i.next().equals(e)) {
				i.remove();
			}
		}
		lock.unlock();
		return this;
	}

	public boolean contains(T e) {
		lock.lock();
		boolean b = set.contains(e);
		lock.unlock();
		return b;
	}

	/**
	 * 本类表示一个有名字的组，可以当做一个Map来用<br>
	 * 
	 * @author Gogo
	 *
	 * @param <T>
	 */
	public static class MapGroup<T extends Named> extends Group<T> {
		public T get(String name) {
			if (name == null) {
				return null;
			}
			lock.lock();
			for (T t : getAll()) {
				if (name.equals(t.name())) {
					lock.unlock();
					return t;
				}
			}
			lock.unlock();
			return null;
		}
	}
}
