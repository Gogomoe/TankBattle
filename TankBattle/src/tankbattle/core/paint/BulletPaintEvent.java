package tankbattle.core.paint;

import tankbattle.core.bullet.Bullet;

public class BulletPaintEvent extends EntityPaintEvent {

	public BulletPaintEvent(Bullet paintable) {
		super(paintable);
	}

	@Override
	public Bullet getPaintable() {
		return (Bullet) super.getPaintable();
	}

	public BulletPaintEvent setPaintable(Bullet paintable) {
		return (BulletPaintEvent) super.setPaintable(paintable);
	}

}
