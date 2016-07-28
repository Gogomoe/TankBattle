package tankbattle.core.entity;

import java.util.Set;
import java.util.stream.Collectors;

import tankbattle.core.others.Group;
import tankbattle.core.position.Point;
import tankbattle.core.shape.VectorShape;

public class EntityGroup extends Group<Entity> {

	public Set<Entity> getContain(VectorShape vshape) {
		if (vshape == null) {
			return null;
		}
		return set.stream().filter(f -> f.vshape().contains(vshape)).collect(Collectors.toSet());
	}

	public Set<Entity> getContact(VectorShape vshape) {
		if (vshape == null) {
			return null;
		}
		return set.stream().filter(f -> f.vshape().contacts(vshape)).collect(Collectors.toSet());
	}

	public Set<Entity> getPos(Point p) {
		if (p == null) {
			return null;
		}
		return set.stream().filter(f -> f.vshape().contains(p)).collect(Collectors.toSet());
	}

	public Set<Entity> getPos(double x, double y) {
		return getPos(new Point(x, y));
	}

}
