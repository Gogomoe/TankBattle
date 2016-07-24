package tankbattle.core.control;

import tankbattle.core.Extrable;
import tankbattle.core.TankBattle;

public interface Controller extends Extrable {

	default public Team team() {
		return player().getTeam();
	}

	default public Player player() {
		return TankBattle.getGame().getProcess().send(new PlayerPropertyEvent(this)).getPlayer();
	}
}
