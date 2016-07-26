package tankbattle.core.control;

import java.util.Set;

import tankbattle.core.Group.MapGroup;

public class Team implements Named {

	final public static Team system = new Team("System");

	private String name;

	private MapGroup<Player> players = new MapGroup<>();

	public Team(String name) {
		super();
		this.name = name;
	}

	public String name() {
		return name;
	}

	public boolean contains(Player player) {
		return players.contains(player);
	}

	public void add(Player player) {
		players.add(player);
	}

	public void remove(Player player) {
		players.remove(player);
	}

	public Set<Player> players() {
		return players.getAll();
	}

	public Player getPlayer(String name) {
		return players.get(name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Team other = (Team) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Team [" + name + "]";
	}

}
