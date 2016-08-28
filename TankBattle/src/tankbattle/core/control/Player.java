package tankbattle.core.control;

import tankbattle.core.TankBattle;

/**
 * 描述一个玩家对象<br>
 * 玩家拥有一个名字，这个名字不应该重复<br>
 * 玩家通常属于某一支队伍<br>
 * @author Gogo
 *
 */
public class Player implements Named {

	/**
	 * 默认的系统队伍
	 */
	final public static Player system = new Player("System", Team.system);

	private String name;

	private Team team;

	public Player(String name) {
		this(name, null);
	}

	public Player(String name, Team team) {
		this.name = name;
		if (team != null) {
			this.team = team;
		} else {
			team = TankBattle.getGame().getTeamGroup().get("System");
		}
	}

	public String name() {
		return name;
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
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Team team() {
		return team;
	}

	public Team getTeam() {
		return team;
	}

	public Player setTeam(Team team) {
		if (team != null) {
			this.team = team;
		} else {
			this.team = Team.system;
		}
		return this;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", team=" + team + "]";
	}

}
