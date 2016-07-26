package tankbattle.core;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

import tankbattle.core.Group.MapGroup;
import tankbattle.core.battle.attack.DamageEvent;
import tankbattle.core.battle.attack.DamageListener;
import tankbattle.core.battle.attack.DamagePropertyEvent;
import tankbattle.core.battle.attack.DamagePropertyListener;
import tankbattle.core.battle.bullet.BulletDamageEvent;
import tankbattle.core.battle.bullet.BulletDamageListener;
import tankbattle.core.battle.bullet.BulletHitListener;
import tankbattle.core.battle.live.LivePropertyEvent;
import tankbattle.core.battle.live.LivePropertyListener;
import tankbattle.core.battle.tank.TankAttackEvent;
import tankbattle.core.battle.tank.TankAttackListener;
import tankbattle.core.control.Player;
import tankbattle.core.control.PlayerPropertyEvent;
import tankbattle.core.control.PlayerPropertyListener;
import tankbattle.core.control.Team;
import tankbattle.core.control.TeamPropertyEvent;
import tankbattle.core.control.TeamPropertyListener;
import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;
import tankbattle.core.move.EntityMoveEvent;
import tankbattle.core.move.EntityMoveListener;
import tankbattle.core.move.MoveEvent;
import tankbattle.core.move.MoveListener;
import tankbattle.core.move.MovePropertyEvent;
import tankbattle.core.move.MovePropertyListener;
import tankbattle.core.move.collide.BaseCollideListener;
import tankbattle.core.move.collide.CollideEvent;
import tankbattle.core.move.contact.BulletContactEvent;
import tankbattle.core.move.contact.ContactListener;
import tankbattle.core.position.PositionPropertyEvent;
import tankbattle.core.position.PositionPropertyListener;
import tankbattle.core.shape.ShapePropertyEvent;
import tankbattle.core.shape.ShapePropertyListener;
import tankbattle.core.time.TimerGroup;

public class TankBattle implements Extrable {

	private Extra extra = new Extra();

	protected static TankBattle game;

	static {
		game = new TankBattle();
		game.init();
	}

	public static TankBattle getGame() {
		return game;
	}

	protected Map<String, EventProcess> processes = Collections.synchronizedMap(new Hashtable<>());

	protected TimerGroup timer = new TimerGroup(1);

	protected EntityGroup entityGroup = new EntityGroup();

	protected MapGroup<Team> teamGroup = new MapGroup<>();

	protected double fps = 60.0;

	public void init() {

		EventProcess p = new EventProcess();
		processes.put("TankBattle", p);

		teamGroup.add(Team.system);
		Team.system.add(Player.system);
		Player.system.setTeam(Team.system);

		p.addListener(PlayerPropertyListener.LID, Listener.EXECUTE, PlayerPropertyEvent.class,
				new PlayerPropertyListener());
		p.addListener(TeamPropertyListener.LID, Listener.EXECUTE, TeamPropertyEvent.class, new TeamPropertyListener());

		p.addListener(LivePropertyListener.LID, Listener.EXECUTE, LivePropertyEvent.class, new LivePropertyListener());
		p.addListener(PositionPropertyListener.LID, Listener.EXECUTE, PositionPropertyEvent.class,
				new PositionPropertyListener());
		p.addListener(ShapePropertyListener.LID, Listener.EXECUTE, ShapePropertyEvent.class,
				new ShapePropertyListener());

		p.addListener(MovePropertyListener.LID, Listener.EXECUTE, MovePropertyEvent.class, new MovePropertyListener());
		p.addListener(MoveListener.LID, Listener.EXECUTE, MoveEvent.class, new MoveListener());
		p.addListener(EntityMoveListener.ConllidListener.LID, 8000, EntityMoveEvent.class,
				new EntityMoveListener.ConllidListener());
		p.addListener(ContactListener.LID, Listener.AFTER_EXECUTE, EntityMoveEvent.class, new ContactListener());
		p.addListener(BaseCollideListener.LID, Listener.EXECUTE, CollideEvent.class, new BaseCollideListener());

		p.addListener(DamagePropertyListener.LID, Listener.EXECUTE, DamagePropertyEvent.class,
				new DamagePropertyListener());
		p.addListener(DamageListener.LID, Listener.EXECUTE, DamageEvent.class, new DamageListener());
		p.addListener(TankAttackListener.LID, Listener.EXECUTE, TankAttackEvent.class, new TankAttackListener());
		p.addListener(BulletHitListener.LID, Listener.NORMAL, BulletContactEvent.class, new BulletHitListener());
		p.addListener(BulletDamageListener.LID, Listener.AFTER_EXECUTE, BulletDamageEvent.class,
				new BulletDamageListener());

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

	public MapGroup<Team> getTeamGroup() {
		return teamGroup;
	}

	@Override
	public Extrable extra() {
		return extra;
	}

}
