package tankbattle.core;

import tankbattle.core.battle.live.Livable;
import tankbattle.core.battle.live.LivePropertyEvent;
import tankbattle.core.position.Direction;
import tankbattle.core.position.PositionPropertyEvent;
import tankbattle.core.position.move.Movable;
import tankbattle.core.position.move.MovePropertyEvent;

public class Entity extends Extra implements Movable, Livable {

	public static Direction ENTITY_TOWARDS = Direction.SOUTH;
	public static int ENTITY_LAYER = 0;
	public static double ENTITY_SPEED = 100;
	public static int ENTITY_HP = 100;
	public static int ENTITY_MAXHP = 100;
	public static int ENTITY_DEF = 0;
	public static boolean ENTITY_LIVE = true;

	{
		TankBattle.getGame().getProcess()
				.send(new LivePropertyEvent(this).setHP(ENTITY_HP).setDEF(ENTITY_DEF).setLive(ENTITY_LIVE)
						.setMaxHP(ENTITY_MAXHP).setCode(LivePropertyEvent.SET_HP + LivePropertyEvent.SET_MAXHP
								+ LivePropertyEvent.SET_DEF + LivePropertyEvent.SET_LIVE));
		TankBattle.getGame().getProcess().send(new PositionPropertyEvent(this).setTowards(ENTITY_TOWARDS)
				.setLayer(ENTITY_LAYER).setCode(PositionPropertyEvent.SET_TOWARDS + PositionPropertyEvent.SET_LAYER));
		TankBattle.getGame().getProcess()
				.send(new MovePropertyEvent(this).setSpeed(ENTITY_SPEED).setCode(MovePropertyEvent.SET_SPEED));

	}

}
