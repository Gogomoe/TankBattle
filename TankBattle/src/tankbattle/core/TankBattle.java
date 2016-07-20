package tankbattle.core;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

import tankbattle.core.attack.DamageEvent;
import tankbattle.core.attack.DamageListener;
import tankbattle.core.attack.DamagePropertyEvent;
import tankbattle.core.attack.DamagePropertyListener;
import tankbattle.core.attack.TankAttackEvent;
import tankbattle.core.attack.TankAttackListener;
import tankbattle.core.battle.live.LivePropertyEvent;
import tankbattle.core.battle.live.LivePropertyListener;
import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;
import tankbattle.core.position.PositionPropertyEvent;
import tankbattle.core.position.PositionPropertyListener;
import tankbattle.core.position.move.MoveEvent;
import tankbattle.core.position.move.MoveListener;
import tankbattle.core.position.move.MovePropertyEvent;
import tankbattle.core.position.move.MovePropertyListener;
import tankbattle.core.time.TimerGroup;

public class TankBattle extends Extra {

	protected static TankBattle game;

	static {
		game = new TankBattle();
	}

	public static TankBattle getGame() {
		return game;
	}

	protected Map<String, EventProcess> processes = Collections.synchronizedMap(new Hashtable<>());

	protected TimerGroup timer = new TimerGroup(1);

	protected EntityGroup entityGroup = new EntityGroup();

	protected double fps = 60.0;

	public TankBattle() {
		EventProcess p = new EventProcess();
		processes.put("TankBattle", p);
		p.addListener(LivePropertyListener.LID, Listener.EXECUTE, LivePropertyEvent.class, new LivePropertyListener());
		p.addListener(PositionPropertyListener.LID, Listener.EXECUTE, PositionPropertyEvent.class,
				new PositionPropertyListener());

		p.addListener(MovePropertyListener.LID, Listener.EXECUTE, MovePropertyEvent.class, new MovePropertyListener());
		p.addListener(MoveListener.LID, Listener.EXECUTE, MoveEvent.class, new MoveListener());
		p.addListener(MoveListener.MoveSpeedSetter.LID, Listener.EARLY, MoveEvent.class,
				new MoveListener.MoveSpeedSetter());

		p.addListener(DamagePropertyListener.LID, Listener.EXECUTE, DamagePropertyEvent.class,
				new DamagePropertyListener());
		p.addListener(DamageListener.LID, Listener.EXECUTE, DamageEvent.class, new DamageListener());
		p.addListener(TankAttackListener.LID, Listener.EXECUTE, TankAttackEvent.class, new TankAttackListener());

		timer.addListener(MoveListener.MoveTimer.LID, Listener.EXECUTE, new MoveListener.MoveTimer((1000 / fps)));
		timer.createThread();
		timer.start();
	}

	public double getFPS() {
		return fps;
	}

	public EventProcess getProcess() {
		return processes.get("TankBattle");
	}

	public EventProcess getProcess(String key) {
		return processes.get(key);
	}

	public TimerGroup getTimer() {
		return timer;
	}

	public EntityGroup getEntityGroup() {
		return entityGroup;
	}

}
