package tankbattle.core.move;

import tankbattle.core.event.Listener;

/**
 * 用于操作与移动相关属性的监听器<br>
 * 
 * @author Gogo
 *
 */
public class MovePropertyListener implements Listener<MovePropertyEvent> {

	final public static String LID = "TankBattle:MovePropertyListener";

	final public static String KEY_SPEED = "Movable:Speed";
	final public static String KEY_MOVING = "Movable:Moving";

	@Override
	public void listen(MovePropertyEvent event) {
		if (event.canceled() || event.executed() || event.getMover() == null) {
			return;
		}
		// 速度
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
		// 是否在移动
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
