package tankbattle.core.move;

import tankbattle.core.TankBattle;
import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;
import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;
import tankbattle.core.time.TimeListener;

public class MoveListener implements Listener<MoveEvent> {

	public static class MoveTimer extends TimeListener {
		final public static String LID = "TankBattle:MoveListener:MoveTimer";

		public MoveTimer(double interval) {
			super(interval, e -> {
				TankBattle.getGame().getEntityGroup().getAll().forEach(entity -> {
					entity.move();
				});
			});
		}

	}

	public static class MoveSpeedSetter implements Listener<MoveEvent> {
		final public static String LID = "TankBattle:MoveListener:MoveSpeedSetter";

		@Override
		public void listen(MoveEvent event) {
			if (event.canceled() || event.executed() || event.getMover() == null) {
				return;
			}
			if (event.getMover().isMoving()) {
				Vector v = new Vector(event.getMover().towards(),
						event.getMover().speed() / TankBattle.getGame().getFPS());
				event.setVector(v);
			}
		}

	}

	final public static String LID = "TankBattle:MoveListener";

	@Override
	public void listen(MoveEvent event) {
		if (event.canceled() || event.executed() || event.getMover() == null) {
			return;
		}
		event.setExecuted(true);
		if (event.getVector() == null) {
			return;
		}
		Point p = event.getMover().position();
		event.getMover().setPosition(p.add(event.getVector()));
		event.setExecuted(true);
	}

	@Override
	public void init(EventProcess process) {
		process.addListener(MoveListener.MoveSpeedSetter.LID, Listener.EARLY, MoveEvent.class,
				new MoveListener.MoveSpeedSetter());
		TankBattle.getGame().getTimer().addListener(MoveListener.MoveTimer.LID, Listener.EXECUTE,
				new MoveListener.MoveTimer((1000.0 / TankBattle.getGame().getFPS())));
	}

}
