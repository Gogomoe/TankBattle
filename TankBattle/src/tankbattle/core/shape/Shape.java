package tankbattle.core.shape;

import java.util.HashSet;
import java.util.Set;

import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;

public abstract class Shape {

	protected double accuracy = 1;

	abstract public Set<Point> points();

	public Set<Point> rpoints() {
		Set<Point> ps = new HashSet<>();
		points().forEach(p -> {
			ps.add(p.rval());
		});
		return ps;
	}

	public VectorShape toVectorShape(Vector v) {
		return new VectorShape(this, v);
	}

	public double accuracy() {
		return accuracy;
	}

	public Shape setAccuracy(double accuracy) {
		this.accuracy = accuracy;
		return this;
	}

}
