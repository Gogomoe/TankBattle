package tankbattle.core.paint;

import tankbattle.core.tank.Tank;

public class TankPaintEvent extends EntityPaintEvent {

	public TankPaintEvent(Tank paintable) {
		super(paintable);
	}

	@Override
	public Tank getPaintable() {
		return (Tank) super.getPaintable();
	}

	public TankPaintEvent setPaintable(Tank paintable) {
		return (TankPaintEvent) super.setPaintable(paintable);
	}

}
