package tankbattle.core.position.move;

import tankbattle.core.event.Listener;

public class MovePropertyListener implements Listener<MovePropertyEvent> {

	final public static String LID = "TankBattle:MovePropertyListener";

	final public static String KEY_SPEED = "Movable:Speed";
	final public static String KEY_MOVING = "Movable:Moving";

	@Override
	public void listen(MovePropertyEvent event) {
		if (event.canceled() || event.executed() || event.getMover() == null) {
			return;
		}

		Movable mover = event.getMover();
		int code = event.code();
		if ((code & MovePropertyEvent.SET_SPEED) == 0) {
			if (!mover.contains(KEY_SPEED)) {
				mover.put(KEY_SPEED, 0);
			}
			event.setSpeed(mover.getDouble(KEY_SPEED));
		} else {
			mover.put(KEY_SPEED, event.getSpeed());
		}
		if ((code & MovePropertyEvent.SET_MOVING) == 0) {
			if (!mover.contains(KEY_MOVING)) {
				mover.put(KEY_MOVING, false);
			}
			event.setMoving(mover.getBool(KEY_MOVING));
		} else {
			mover.put(KEY_MOVING, event.isMoving());
		}
		event.setExecuted(true);
	}

}
