package tankbattle.core.entity;

import java.util.HashMap;
import java.util.Map;

import tankbattle.core.TankBattle;
import tankbattle.core.battle.live.Livable;
import tankbattle.core.battle.live.LivePropertyEvent;
import tankbattle.core.control.Controller;
import tankbattle.core.control.Player;
import tankbattle.core.control.PlayerPropertyEvent;
import tankbattle.core.move.EntityMoveEvent;
import tankbattle.core.move.Movable;
import tankbattle.core.move.MovePropertyEvent;
import tankbattle.core.others.Extra;
import tankbattle.core.others.Extrable;
import tankbattle.core.paint.EntityPaintEvent;
import tankbattle.core.paint.Paintable;
import tankbattle.core.position.Direction;
import tankbattle.core.position.PositionPropertyEvent;
import tankbattle.core.shape.Rect;
import tankbattle.core.shape.Shapable;
import tankbattle.core.shape.ShapePropertyEvent;
import tankbattle.core.view.EntityNode;
import tankbattle.core.view.View;

public class Entity implements Movable, Livable, Shapable, Extrable, Controller, Paintable {

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
		TankBattle.getGame().getProcess()
				.send(new LivePropertyEvent(this).setHP(ENTITY_HP).setDEF(ENTITY_DEF).setLive(ENTITY_LIVE)
						.setMaxHP(ENTITY_MAXHP).setCode(LivePropertyEvent.SET_HP + LivePropertyEvent.SET_MAXHP
								+ LivePropertyEvent.SET_DEF + LivePropertyEvent.SET_LIVE));
		TankBattle.getGame().getProcess().send(new PositionPropertyEvent(this).setTowards(ENTITY_TOWARDS)
				.setLayer(ENTITY_LAYER).setCode(PositionPropertyEvent.SET_TOWARDS + PositionPropertyEvent.SET_LAYER));
		TankBattle.getGame().getProcess()
				.send(new MovePropertyEvent(this).setSpeed(ENTITY_SPEED).setCode(MovePropertyEvent.SET_SPEED));

		TankBattle.getGame().getProcess()
				.send(new PlayerPropertyEvent(this).setPlayer(ENTITY_PLAYER).setCode(PlayerPropertyEvent.SET_PLAYER));

		TankBattle.getGame().getProcess()
				.send(new ShapePropertyEvent(this).setShape(new Rect(50, 50)).setCode(ShapePropertyEvent.SET_SHAPE));

		this.put(KEY_NODE, new HashMap<View, EntityNode>());
	}

	public void staticInit() {

	}

	@Override
	public Extrable extra() {
		return extra;
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
