package tankbattle.core.position;

import tankbattle.core.event.Listener;

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
		Point p = poser.getObj(KEY_POS, Point.class);
		if (p == null) {
			p = new Point();
			poser.put(KEY_POS, p);
		}
		if ((code & PositionPropertyEvent.SET_POSTION) == 0) {
			event.setPosition(p.copy());
		} else {
			p.set(event.getPosition());
		}
		if ((code & PositionPropertyEvent.SET_TOWARDS) == 0) {
			event.setTowards(poser.getObj(KEY_TOWARDS, Direction.class));
		} else {
			poser.put(KEY_TOWARDS, event.getTowards());
		}
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
