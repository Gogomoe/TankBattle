package tankbattle.core.control;

import tankbattle.core.event.Listener;

public class PlayerPropertyListener implements Listener<PlayerPropertyEvent> {

	final public static String LID = "TankBattle:PlayerPropertyListener";
	final public static String KEY_PLAYER = "Controller:Player";

	@Override
	public void listen(PlayerPropertyEvent event) {
		if (event.canceled() || event.executed() || event.getController() == null) {
			return;
		}
		Controller c = event.getController();
		if ((event.code() & PlayerPropertyEvent.SET_PLAYER) == 0) {
			if (!c.contains(KEY_PLAYER)) {
				c.put(KEY_PLAYER, Player.system);
			}
			event.setPlayer(c.getObj(KEY_PLAYER, Player.class));
		} else {
			c.put(KEY_PLAYER, event.getPlayer() == null ? Player.system : event.getPlayer());
		}
	}

}
