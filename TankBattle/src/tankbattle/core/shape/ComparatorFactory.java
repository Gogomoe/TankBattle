package tankbattle.core.shape;

import java.util.HashMap;

public class ComparatorFactory {

	public static ComparatorFactory factory = new ComparatorFactory();

	private HashMap<ComparatorType, ShapeComparator<? extends VectorShape, ? extends VectorShape>> comparators = new HashMap<>();

	public ComparatorFactory() {

	}

	private static class ComparatorType {
		private Class<? extends VectorShape> c1, c2;

		public ComparatorType(Class<? extends VectorShape> c1, Class<? extends VectorShape> c2) {
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
	public <T extends VectorShape, S extends VectorShape> ShapeComparator<T, S> get(Class<T> c1, Class<S> c2) {
		return (ShapeComparator<T, S>) comparators.get(new ComparatorType(c1, c2));
	}

	public <T extends VectorShape, S extends VectorShape> void add(Class<T> c1, Class<S> c2, ShapeComparator<T, S> c) {
		comparators.put(new ComparatorType(c1, c2), c);
	}

}
