package tankbattle.core.move;

import tankbattle.core.TankBattle;
import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;
import tankbattle.core.event.ListenerItem;
import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;
import tankbattle.core.time.TimeListener;

/**
 * 针对移动进行一系列操作的监听器<br>
 * 
 * @author Gogo
 *
 */
public class MoveListener implements Listener<MoveEvent> {

	/**
	 * 每隔一段事件，自动发送全体实体移动事件的Timer<br>
	 * 
	 * @author Gogo
	 *
	 */
	public static class MoveTimer extends TimeListener {
		final public static String LID = "TankBattle:MoveListener:MoveTimer";

		public MoveTimer(double interval) {
			super(interval, e -> {
				// 为每一个实体发送移动事件
				TankBattle.getGame().getEntityGroup().getAll().forEach(entity -> {
					entity.move();
				});
			});
		}

	}

	/**
	 * 在移动开始前判断是否在移动，并设置初始速度的监听器<br>
	 * 
	 * @author Gogo
	 *
	 */
	public static class MoveSpeedSetter implements Listener<MoveEvent> {
		final public static String LID = "TankBattle:MoveListener:MoveSpeedSetter";

		@Override
		public void listen(MoveEvent event) {
			if (event.canceled() || event.executed() || event.getMover() == null) {
				return;
			}
			// 设置初始速度
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
	public void init(EventProcess process, ListenerItem<MoveEvent> item) {
		process.addListener(MoveListener.MoveSpeedSetter.LID, Listener.EARLY, MoveEvent.class,
				new MoveListener.MoveSpeedSetter());
		TankBattle.getGame().getTimer().addListener(MoveListener.MoveTimer.LID, Listener.EXECUTE,
				new MoveListener.MoveTimer((1000.0 / TankBattle.getGame().getFPS())));
	}

}
