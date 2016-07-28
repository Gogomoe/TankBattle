package tankbattle.core.paint;

import tankbattle.core.TankBattle;
import tankbattle.core.others.Extrable;

public interface Paintable extends Extrable {
	
	default public void paint(){
		TankBattle.getGame().getProcess().send(new PaintEvent(this));
	}

}
