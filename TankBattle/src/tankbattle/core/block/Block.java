package tankbattle.core.block;

import tankbattle.core.TankBattle;
import tankbattle.core.entity.Entity;
import tankbattle.core.paint.BlockPaintEvent;
import tankbattle.core.position.Positionable;
import tankbattle.core.view.View;

/**
 * 地图上的一个方块<br>
 * 绝大多数的方块是不可移动的，部分方块可能会与玩家产生交互<br>
 * 
 * @author Gogo
 *
 */
public class Block extends Entity {

	public static int BLOCK_LAYER = Positionable.LOWSET;

	{
		setLayer(BLOCK_LAYER);
	}

	@Override
	public void paint(View view) {
		TankBattle.getGame().getProcess().send(new BlockPaintEvent(this, view));
	}

}
