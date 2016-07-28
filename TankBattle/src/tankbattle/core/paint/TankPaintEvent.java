package tankbattle.core.paint;

import tankbattle.core.tank.Tank;
import tankbattle.core.view.View;

public class TankPaintEvent extends EntityPaintEvent {

	public TankPaintEvent(Tank paintable, View view) {
		super(paintable, view);
	}

	@Override
	public Tank getPaintable() {
		return (Tank) super.getPaintable();
	}

	public TankPaintEvent setPaintable(Tank paintable) {
		return (TankPaintEvent) super.setPaintable(paintable);
	}

}
