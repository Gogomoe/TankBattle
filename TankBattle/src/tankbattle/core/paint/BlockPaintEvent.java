package tankbattle.core.paint;

import tankbattle.core.block.Block;
import tankbattle.core.view.View;

public class BlockPaintEvent extends EntityPaintEvent {

	public BlockPaintEvent(Block paintable, View view) {
		super(paintable ,view);
	}

	@Override
	public Block getPaintable() {
		return (Block) super.getPaintable();
	}

	public BlockPaintEvent setPaintable(Block paintable) {
		return (BlockPaintEvent) super.setPaintable(paintable);
	}

}
