package tankbattle.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import tankbattle.core.others.SetUtils;

public class EntityGroup {

	private Set<Entity> entities = Collections.synchronizedSet(new HashSet<>());

	public Set<Entity> getAll() {
		return new HashSet<>(entities);
	}

	public EntityGroup add(Entity e) {
		entities.add(e);
		return this;
	}

	public EntityGroup remove(Entity e) {
		if (e == null) {
			return this;
		}
		for (Iterator<Entity> i = entities.iterator(); i.hasNext();) {
			if (i.next().equals(e)) {
				i.remove();
			}
		}
		return this;
	}

	public boolean contains(Entity e) {
		return SetUtils.contains(entities, e);
	}

}
