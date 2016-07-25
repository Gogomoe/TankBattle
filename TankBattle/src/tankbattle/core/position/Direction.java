package tankbattle.core.position;

/**
 * 用于表示地图中四个方向<br/>
 * 上下左右 分别对应 北南西东<br/>
 * 
 * @author Gogo
 *
 */
abstract public class Direction {
	final public static class North extends Direction {
		public North() {
			super("North");
		}

		@Override
		public Vector vector() {
			return new Vector(0, -1);
		}

	}

	final public static class South extends Direction {
		public South() {
			super("South");
		}

		@Override
		public Vector vector() {
			return new Vector(0, 1);
		}

	}

	final public static class East extends Direction {
		public East() {
			super("East");
		}

		@Override
		public Vector vector() {
			return new Vector(1, 0);
		}

	}

	final public static class West extends Direction {
		public West() {
			super("West");
		}

		@Override
		public Vector vector() {
			return new Vector(-1, 0);
		}

	}

	final public static Direction NORTH = new North();
	final public static Direction SOUTH = new South();
	final public static Direction EAST = new East();
	final public static Direction WEST = new West();

	private String direction;

	public Direction(String direction) {
		this.direction = direction;
	}

	/**
	 * @return 该方向的单位向量
	 */
	abstract public Vector vector();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
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
		Direction other = (Direction) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Direction{" + direction + "}";
	}

}
