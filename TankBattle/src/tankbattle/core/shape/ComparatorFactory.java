package tankbattle.core.shape;

import java.util.HashMap;

/**
 * 创建图形比较器的工厂<br>
 * 
 * @author Gogo
 *
 */
public class ComparatorFactory {

	public static ComparatorFactory factory = new ComparatorFactory();

	private HashMap<ComparatorType<?, ?>, ShapeComparator<?, ?>> comparators = new HashMap<>();

	public ComparatorFactory() {
		this.add(Rect.class, Rect.class, new ShapeComparator.RectComparator());
		this.add(Rect.class, Circle.class, new ShapeComparator.RectCircleComparator());
		this.add(Circle.class, Rect.class, new ShapeComparator.CircleRectComparator());
		this.add(Circle.class, Circle.class, new ShapeComparator.CircleComparator());
	}

	private static class ComparatorType<T extends Shape, S extends Shape> {
		private Class<T> c1;
		private Class<S> c2;

		public ComparatorType(Class<T> c1, Class<S> c2) {
			super();
			this.c1 = c1;
			this.c2 = c2;
		}

		@Override
		public int hashCode() {

			final int prime = 31;
			int result = 1;
			result = prime * result + ((c1 == null) ? 0 : c1.hashCode());
			result = prime * result + ((c2 == null) ? 0 : c2.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			@SuppressWarnings("rawtypes")
			ComparatorType other = (ComparatorType) obj;
			if (c1 == null) {
				if (other.c1 != null)
					return false;
			} else if (!c1.equals(other.c1))
				return false;
			if (c2 == null) {
				if (other.c2 != null)
					return false;
			} else if (!c2.equals(other.c2))
				return false;
			return true;
		}

	}

	@SuppressWarnings("unchecked")
	public <T extends Shape, S extends Shape> ShapeComparator<T, S> get(Class<T> c1, Class<S> c2) {
		return (ShapeComparator<T, S>) comparators.get(new ComparatorType<T, S>(c1, c2));
	}

	public <T extends Shape, S extends Shape> void add(Class<T> c1, Class<S> c2, ShapeComparator<T, S> c) {
		comparators.put(new ComparatorType<T, S>(c1, c2), c);
	}

}
