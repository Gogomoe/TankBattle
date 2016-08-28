package tankbattle.core.battle.attack;

import tankbattle.core.TankBattle;
import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;
import tankbattle.core.event.ListenerItem;
import tankbattle.core.time.OnceTimeListener;

/**
 * 攻击后开始冷却的监听器
 * 
 * @author Gogo
 *
 */
public class AttackCoolDownListener implements Listener<AttackCoolDownEvent> {

	public static final String LID = "TankBattle:AttackCoolDownListener";

	public static final String KEY_CD = "Assailable:CoolDownTime";

	@Override
	public void listen(AttackCoolDownEvent event) {
		if (event.canceled() || event.executed() || event.getAttacker() == null || event.getTime() <= 0) {
			return;
		}
		Assailable attacker = event.getAttacker();
		// 添加禁止攻击的监听器
		Listener<AttackEvent> l = e -> {
			if (attacker.equals(e.getAttacker())) {
				e.setcanceled(true);
			}
		};
		TankBattle.getGame().getProcess().addListener(AttackEvent.class, l);
		// 移除禁止攻击
		TankBattle.getGame().getTimer().addListener(new OnceTimeListener(event.getTime(), () -> {
			TankBattle.getGame().getProcess().removeListener(l);
		}));
	}

	/**
	 * 攻击后发送冷却的监听器
	 * 
	 * @author Gogo
	 *
	 */
	public class StartCoolDownListener implements Listener<AttackEvent> {

		public static final String LID = "TankBattle:AttackCoolDownListener:StartCoolDownListener";

		@Override
		public void listen(AttackEvent event) {
			Assailable attacker = event.getAttacker();
			if (event.canceled() || !event.executed() || attacker == null || !attacker.contains(KEY_CD)) {
				return;
			}
			// 发送冷却事件
			int cd = attacker.getInt(KEY_CD);
			if (cd > 0) {
				TankBattle.getGame().getProcess().send(new AttackCoolDownEvent(attacker, cd));
			}
		}

	}

	@Override
	public void init(EventProcess process, ListenerItem<AttackCoolDownEvent> item) {
		process.addListener(StartCoolDownListener.LID, Listener.AFTER_EXECUTE, AttackEvent.class,
				new StartCoolDownListener());
	}

}
