package tankbattle.core;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

import tankbattle.core.Group.MapGroup;
import tankbattle.core.attack.DamageEvent;
import tankbattle.core.attack.DamageListener;
import tankbattle.core.attack.DamagePropertyEvent;
import tankbattle.core.attack.DamagePropertyListener;
import tankbattle.core.attack.TankAttackEvent;
import tankbattle.core.attack.TankAttackListener;
import tankbattle.core.battle.live.LivePropertyEvent;
import tankbattle.core.battle.live.LivePropertyListener;
import tankbattle.core.control.Player;
import tankbattle.core.control.PlayerPropertyEvent;
import tankbattle.core.control.PlayerPropertyListener;
import tankbattle.core.control.Team;
import tankbattle.core.control.TeamPropertyEvent;
import tankbattle.core.control.TeamPropertyListener;
import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;
import tankbattle.core.position.PositionPropertyEvent;
import tankbattle.core.position.PositionPropertyListener;
import tankbattle.core.position.move.MoveEvent;
import tankbattle.core.position.move.MoveListener;
import tankbattle.core.position.move.MovePropertyEvent;
import tankbattle.core.position.move.MovePropertyListener;
import tankbattle.core.time.TimerGroup;

public class TankBattle implements Extrable {

	private Extra extra = new Extra();

	protected static TankBattle game;

	static {
		game = new TankBattle();
	}

	public static TankBattle getGame() {
		return game;
	}

	protected Map<String, EventProcess> processes = Collections.synchronizedMap(new Hashtable<>());

	protected TimerGroup timer = new TimerGroup(1);

	protected Group<Entity> entityGroup = new Group<Entity>();

	protected MapGroup<Team> teamGroup = new MapGroup<>();

	protected double fps = 60.0;

	public TankBattle() {
		EventProcess p = new EventProcess();
		processes.put("TankBattle", p);

		teamGroup.add(Team.system);
		Team.system.add(Player.system);

		p.addListener(PlayerPropertyListener.LID, Listener.EXECUTE, PlayerPropertyEvent.class,
				new PlayerPropertyListener());
		p.addListener(TeamPropertyListener.LID, Listener.EXECUTE, TeamPropertyEvent.class, new TeamPropertyListener());

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

	public Group<Entity> getEntityGroup() {
		return entityGroup;
	}

	public MapGroup<Team> getTeamGroup() {
		return teamGroup;
	}

	@Override
	public Extrable extra() {
		return extra;
	}

}
