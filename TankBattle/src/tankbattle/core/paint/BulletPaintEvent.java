package tankbattle.core.paint;

import tankbattle.core.bullet.Bullet;
import tankbattle.core.view.View;

public class BulletPaintEvent extends EntityPaintEvent {

	public BulletPaintEvent(Bullet paintable, View view) {
		super(paintable, view);
	}

	@Override
	public Bullet getPaintable() {
		return (Bullet) super.getPaintable();
	}

	public BulletPaintEvent setPaintable(Bullet paintable) {
		return (BulletPaintEvent) super.setPaintable(paintable);
	}

}
