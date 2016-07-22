package tankbattle.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import tankbattle.core.control.Named;
import tankbattle.core.others.SetUtils;

public class Group<T> {

	private Set<T> set = Collections.synchronizedSet(new HashSet<>());

	public Set<T> getAll() {
		return new HashSet<>(set);
	}

	public Group<T> add(T e) {
		set.add(e);
		return this;
	}

	public Group<T> remove(T e) {
		if (e == null) {
			return this;
		}
		for (Iterator<T> i = set.iterator(); i.hasNext();) {
			if (i.next().equals(e)) {
				i.remove();
			}
		}
		return this;
	}

	public boolean contains(T e) {
		return SetUtils.contains(set, e);
	}

	public static class MapGroup<T extends Named> extends Group<T> {
		public T get(String name) {
			if (name == null) {
				return null;
			}
			for (T t : getAll()) {
				if (name.equals(t.name())) {
					return t;
				}
			}
			return null;
		}
	}
}
