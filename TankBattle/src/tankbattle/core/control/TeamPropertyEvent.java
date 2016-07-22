package tankbattle.core.control;

import tankbattle.core.event.Event;

public class TeamPropertyEvent extends Event {

	final public static int GET_TEAM = 0;
	final public static int SET_TEAM = 1;

	private Player player;
	private Team team;

	public TeamPropertyEvent(Player player) {
		super();
		this.player = player;
	}

	public TeamPropertyEvent(Player player, int code) {
		super(code);
		this.player = player;
	}

	public Team getTeam() {
		return team;
	}

	public TeamPropertyEvent setTeam(Team team) {
		this.team = team;
		return this;
	}

	public Player getPlayer() {
		return player;
	}

}
