package tankbattle.core.entity;

import java.util.HashMap;
import java.util.Map;

import tankbattle.core.TankBattle;
import tankbattle.core.battle.live.Livable;
import tankbattle.core.battle.live.Liver;
import tankbattle.core.control.Controller;
import tankbattle.core.control.Player;
import tankbattle.core.control.PlayerPropertyEvent;
import tankbattle.core.move.EntityMoveEvent;
import tankbattle.core.move.Movable;
import tankbattle.core.move.Mover;
import tankbattle.core.others.Extra;
import tankbattle.core.others.Extrable;
import tankbattle.core.paint.EntityPaintEvent;
import tankbattle.core.paint.Paintable;
import tankbattle.core.position.Direction;
import tankbattle.core.position.Point;
import tankbattle.core.position.Positionable;
import tankbattle.core.position.Positioner;
import tankbattle.core.shape.Rect;
import tankbattle.core.shape.Shapable;
import tankbattle.core.shape.Shaper;
import tankbattle.core.view.EntityNode;
import tankbattle.core.view.View;

/**
 * 实体是游戏中最常见的单元<br>
 * 坦克、炮弹、方块都继承于实体<br>
 * 实体具有位置、可以移动(部分如方块除外)、具有生命(部分如炮弹除外)、具有形状、可被控制、可绘画在地图上<br>
 * 实体还可以进行扩展，可以储存一些用户数据<br>
 * 
 * @author Gogo
 *
 */
public class Entity implements Positionable, Movable, Livable, Shapable, Extrable, Controller, Paintable {

	private Extra extra = new Extra();

	public static Direction ENTITY_TOWARDS = Direction.SOUTH;
	public static int ENTITY_LAYER = 0;
	public static double ENTITY_SPEED = 100;

	public static int ENTITY_HP = 100;
	public static int ENTITY_MAXHP = 100;
	public static int ENTITY_DEF = 0;
	public static boolean ENTITY_LIVE = true;

	public static Player ENTITY_PLAYER = Player.system;

	public Entity() {
		if (!TankBattle.getGame().contains("Entity:StaticInit:" + getClass().getName())) {
			TankBattle.getGame().put("Entity:StaticInit:" + getClass().getName(), true);
			staticInit();
		}
		init();
	}

	public void init() {
		this.setHP(ENTITY_HP).setDEF(ENTITY_DEF).setLive(ENTITY_LIVE).setMaxHP(ENTITY_MAXHP);
		this.setPosition(new Point()).setTowards(Direction.SOUTH).setLayer(ENTITY_LAYER);
		this.setSpeed(ENTITY_SPEED).setMoving(false);
		this.setShape(new Rect(50, 50));

		TankBattle.getGame().getProcess()
				.send(new PlayerPropertyEvent(this).setPlayer(ENTITY_PLAYER).setCode(PlayerPropertyEvent.SET_PLAYER));

		this.put(KEY_NODE, new HashMap<View, EntityNode>());
	}

	public void staticInit() {

	}

	@Override
	public Extrable extra() {
		return extra;
	}

	private final Positionable positioner = new Positioner();

	@Override
	public Positionable positioner() {
		return positioner;
	}

	private final Movable mover = new Mover(positioner);

	@Override
	public Movable mover() {
		return mover;
	}

	private final Shapable shaper = new Shaper(positioner);

	@Override
	public Shapable shaper() {
		return shaper;
	}

	private final Livable liver = new Liver();

	@Override
	public Livable liver() {
		return liver;
	}

	@Override
	public void move() {
		TankBattle.getGame().getProcess().send(new EntityMoveEvent(this));
	}

	@Override
	public void paint(View view) {
		TankBattle.getGame().getProcess().send(new EntityPaintEvent(this, view));
	}

	@Override
	public EntityNode getNode(View view) {
		if (view == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		Map<View, EntityNode> map = this.getObj(KEY_NODE, Map.class);
		if (!map.containsKey(view)) {
			map.put(view, new EntityNode(this));
		}
		return map.get(view);
	}
}
