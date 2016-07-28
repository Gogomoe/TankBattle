package tankbattle.core.paint;

import tankbattle.core.block.Block;

public class BlockPaintEvent extends EntityPaintEvent {

	public BlockPaintEvent(Block paintable) {
		super(paintable);
	}

	@Override
	public Block getPaintable() {
		return (Block) super.getPaintable();
	}

	public BlockPaintEvent setPaintable(Block paintable) {
		return (BlockPaintEvent) super.setPaintable(paintable);
	}

}
