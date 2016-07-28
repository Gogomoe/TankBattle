package tankbattle.core.block;

import tankbattle.core.TankBattle;
import tankbattle.core.entity.Entity;
import tankbattle.core.paint.BlockPaintEvent;
import tankbattle.core.position.PositionPropertyEvent;
import tankbattle.core.position.Positionable;

public class Block extends Entity {

	public static int BLOCK_LAYER = Positionable.LOWSET;

	{
		TankBattle.getGame().getProcess()
				.send(new PositionPropertyEvent(this).setLayer(BLOCK_LAYER).setCode(PositionPropertyEvent.SET_LAYER));
	}

	@Override
	public void paint() {
		TankBattle.getGame().getProcess().send(new BlockPaintEvent(this));
	}

}
