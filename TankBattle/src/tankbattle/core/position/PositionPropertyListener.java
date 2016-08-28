package tankbattle.core.position;

import tankbattle.core.event.Listener;

/**
 * 位置属性处理事件的监听器<br>
 * 
 * @author Gogo
 *
 */
public class PositionPropertyListener implements Listener<PositionPropertyEvent> {

	final public static String LID = "TankBattle:PositionPropertyListener";

	final public static String KEY_POS = "Positionable:Position";
	final public static String KEY_TOWARDS = "Positionable:Towards";
	final public static String KEY_LAYER = "Positionable:Layer";

	@Override
	public void listen(PositionPropertyEvent event) {
		if (event.canceled() || event.executed() || event.getPositionable() == null) {
			return;
		}
		Positionable poser = event.getPositionable();
		int code = event.code();
		// 位置
		Point p = poser.getObj(KEY_POS, Point.class);
		if (p == null) {
			p = new Point();
			poser.put(KEY_POS, p);
		}
		if ((code & PositionPropertyEvent.SET_POSTION) == 0) {
			event.setPosition(p.copy());
		} else {
			poser.put(KEY_POS, event.getPosition());
		}
		// 朝向
		if ((code & PositionPropertyEvent.SET_TOWARDS) == 0) {
			event.setTowards(poser.getObj(KEY_TOWARDS, Direction.class));
		} else {
			poser.put(KEY_TOWARDS, event.getTowards());
		}
		// 层(高度)
		if ((code & PositionPropertyEvent.SET_LAYER) == 0) {
			if (!poser.contains(KEY_LAYER)) {
				poser.put(KEY_LAYER, Positionable.PLANE);
			}
			event.setLayer(poser.getInt(KEY_LAYER));
		} else {
			poser.put(KEY_LAYER, event.getLayer());
		}

		event.setExecuted(true);
	}

}
