package tankbattle.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import tankbattle.core.battle.attack.AttackCoolDownEvent;
import tankbattle.core.battle.attack.AttackCoolDownListener;
import tankbattle.core.battle.attack.DamageEvent;
import tankbattle.core.battle.attack.DamageListener;
import tankbattle.core.battle.attack.DamagePropertyEvent;
import tankbattle.core.battle.attack.DamagePropertyListener;
import tankbattle.core.battle.bullet.BulletDamageEvent;
import tankbattle.core.battle.bullet.BulletDamageListener;
import tankbattle.core.battle.bullet.BulletHitListener;
import tankbattle.core.battle.tank.TankAttackEvent;
import tankbattle.core.battle.tank.TankAttackListener;
import tankbattle.core.control.Player;
import tankbattle.core.control.PlayerPropertyEvent;
import tankbattle.core.control.PlayerPropertyListener;
import tankbattle.core.control.Team;
import tankbattle.core.control.TeamPropertyEvent;
import tankbattle.core.control.TeamPropertyListener;
import tankbattle.core.entity.EntityGroup;
import tankbattle.core.entity.EntityGroupEvent;
import tankbattle.core.entity.EntityGroupListener;
import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;
import tankbattle.core.move.EntityMoveEvent;
import tankbattle.core.move.EntityMoveListener;
import tankbattle.core.move.MoveEvent;
import tankbattle.core.move.MoveListener;
import tankbattle.core.move.collide.BaseCollideListener;
import tankbattle.core.move.collide.CollideEvent;
import tankbattle.core.move.contact.BulletContactEvent;
import tankbattle.core.move.contact.ContactListener;
import tankbattle.core.others.Extra;
import tankbattle.core.others.Extrable;
import tankbattle.core.others.Group.MapGroup;
import tankbattle.core.paint.EntityPaintEvent;
import tankbattle.core.paint.entity.PointTransListener;
import tankbattle.core.time.TimerGroup;

/**
 * 通过TankBattle对象可以获取一些与游戏全局有关的对象<br>
 * <br>
 * TankBattle有个静态的{@link #getGame()}方法，可以获取到当先线程的游戏对象<br>
 * 通过Game对象，你可以获取到游戏的一些基本组件<br>
 * 如{@link #getProcess()}可以获取到事件进程对象<br>
 * {@link #getTimer()}可以获取到根计时器对象<br>
 * <br>
 * 在主线程中，创建(new)TankBattle对象后，应该先通过{@link #setGame(TankBattle)}方法设置当前线程的游戏对象，
 * 然后再调用初始化方法{@link #init()}<br>
 * <br>
 * 一个常见的用法可能是这样<br>
 * {@code TankBattle game = new TankBattle();}<br>
 * {@code TankBattle.setGame(game);}<br>
 * {@code game.init();} <br>
 * 如果有其他线程需要使用到TankBattle类，则该线程也必须先设置当前游戏对象<br>
 * 例如监听键盘按键的GUI线程在按键被按下时要通过TankBattle发送事件，在此之前，需要先setGame<br>
 * 如果一个线程需要对多个游戏对象进行操作，比如主线程可能会初始化多个Game，那么每次切换需要设置的游戏时都需要setGame<br>
 * <br>
 * 此外，TankBattle实现了{@link Extrable}接口，你可以通过{@link #put(String, Object)}、{@link #getObj(String)}、{@link #getInt(String)}、{@link #getString(String)}等方法在同一个游戏中共享数据<br>
 * 
 * @author Gogo
 *
 */
public class TankBattle implements Extrable {

	/**
	 * 用于保存每个线程对应的Game对象
	 */
	protected static Map<Thread, TankBattle> gameMap;

	static {
		gameMap = Collections.synchronizedMap(new HashMap<>());
	}

	/**
	 * 得到当前线程的游戏对象
	 * 
	 * @return
	 */
	public static TankBattle getGame() {
		return gameMap.get(Thread.currentThread());
	}

	/**
	 * 得到指定线程的游戏对象
	 * 
	 * @param thread
	 * @return
	 */
	public static TankBattle getGame(Thread thread) {
		return gameMap.get(thread);
	}

	public static TankBattle setGame(TankBattle game) {
		return gameMap.put(Thread.currentThread(), game);
	}

	public static TankBattle setGame(Thread thread, TankBattle game) {
		return gameMap.put(thread, game);
	}

	/**
	 * 用于扩展的Extra对象
	 */
	private Extra extra = new Extra();

	/**
	 * 储存所有事件进程的Map
	 */
	protected Map<String, EventProcess> processes = Collections.synchronizedMap(new HashMap<>());

	/**
	 * 游戏根计时器，默认频率为5
	 */
	protected TimerGroup timer = new TimerGroup(5);

	/**
	 * 用于管理游戏中所有实体的对象
	 */
	protected EntityGroup entityGroup = new EntityGroup();

	/**
	 * 用于管理游戏中所有队伍的对象
	 */
	protected MapGroup<Team> teamGroup = new MapGroup<>();

	protected double fps = 60.0;

	/**
	 * 初始化方法，当一个TankBattle被创建出来后，需要对其进行初始化，这会给该游戏对象加入默认的监听器、系统玩家、计时器<br>
	 */
	public void init() {

		EventProcess p = new EventProcess();
		processes.put("TankBattle", p);

		/* 系统队伍、玩家 */
		teamGroup.add(Team.system);
		Team.system.add(Player.system);
		Player.system.setTeam(Team.system);

		/* 玩家、队伍基本处理 */
		p.addListener(PlayerPropertyListener.LID, Listener.EXECUTE, PlayerPropertyEvent.class,
				new PlayerPropertyListener());
		p.addListener(TeamPropertyListener.LID, Listener.EXECUTE, TeamPropertyEvent.class, new TeamPropertyListener());

		/* 实体管理 */
		p.addListener(EntityGroupListener.LID, Listener.EXECUTE, EntityGroupEvent.class, new EntityGroupListener());

		/* 移动以及后续事件(碰撞)管理 */
		p.addListener(MoveListener.LID, Listener.EXECUTE, MoveEvent.class, new MoveListener());
		p.addListener(EntityMoveListener.ConllidListener.LID, 8000, EntityMoveEvent.class,
				new EntityMoveListener.ConllidListener());
		p.addListener(ContactListener.LID, Listener.AFTER_EXECUTE, EntityMoveEvent.class, new ContactListener());
		p.addListener(BaseCollideListener.LID, Listener.EXECUTE, CollideEvent.class, new BaseCollideListener());

		/* 战斗系统的管理 */
		p.addListener(DamagePropertyListener.LID, Listener.EXECUTE, DamagePropertyEvent.class,
				new DamagePropertyListener());
		p.addListener(DamageListener.LID, Listener.EXECUTE, DamageEvent.class, new DamageListener());
		p.addListener(TankAttackListener.LID, Listener.EXECUTE, TankAttackEvent.class, new TankAttackListener());
		p.addListener(BulletHitListener.LID, Listener.NORMAL, BulletContactEvent.class, new BulletHitListener());
		p.addListener(BulletDamageListener.LID, Listener.AFTER_EXECUTE, BulletDamageEvent.class,
				new BulletDamageListener());
		p.addListener(AttackCoolDownListener.LID, Listener.EXECUTE, AttackCoolDownEvent.class,
				new AttackCoolDownListener());

		/* 绘图系统的管理(我觉得加在这不合适，因为服务端很可能没有绘图系统) */
		p.addListener(PointTransListener.LID, Listener.AFTER_EXECUTE, EntityPaintEvent.class, new PointTransListener());

		timer.createThread();
	}

	/**
	 * 启动游戏的根计时器，这个方法效果与getTimer().start()相同
	 */
	public void start() {
		getTimer().start();
	}

	/**
	 * 暂停游戏的根计时器
	 */
	public void stop() {
		getTimer().stop();
	}

	public double getFPS() {
		return fps;
	}

	/**
	 * 得到默认的事件进程<br>
	 * 
	 * @return
	 */
	public EventProcess getProcess() {
		return processes.get("TankBattle");
	}

	public EventProcess getProcess(String key) {
		return processes.get(key);
	}

	/**
	 * 得到根计时器
	 * 
	 * @return
	 */
	public TimerGroup getTimer() {
		return timer;
	}

	/**
	 * 得到实体管理组
	 * 
	 * @return
	 */
	public EntityGroup getEntityGroup() {
		return entityGroup;
	}

	/**
	 * 得到队伍管理组
	 * 
	 * @return
	 */
	public MapGroup<Team> getTeamGroup() {
		return teamGroup;
	}

	@Override
	public Extrable extra() {
		return extra;
	}

}
