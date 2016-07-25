package tankbattle.core;

import tankbattle.core.position.PositionPropertyEvent;
import tankbattle.core.position.Positionable;

public class Block extends Entity {

	public static int BLOCK_LAYER = Positionable.LOWSET;

	{
		TankBattle.getGame().getProcess()
				.send(new PositionPropertyEvent(this).setLayer(BLOCK_LAYER).setCode(PositionPropertyEvent.SET_LAYER));
	}

}
