package tankbattle.core.control;

import tankbattle.core.event.Listener;

public class TeamPropertyListener implements Listener<TeamPropertyEvent> {

	final public static String LID = "TankBattle:TeamPropertyListener";

	@Override
	public void listen(TeamPropertyEvent event) {
		if (event.canceled() || event.executed() || event.getPlayer() == null) {
			return;
		}
		Player p = event.getPlayer();
		if ((event.code() & PlayerPropertyEvent.SET_PLAYER) == 0) {
			event.setTeam(p.team());
		} else {
			p.team().remove(p);
			event.getTeam().add(p);
			p.setTeam(event.getTeam());
		}
		event.setExecuted(true);
	}

}
