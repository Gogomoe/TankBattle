package tankbattle.core.control;

import tankbattle.core.TankBattle;
import tankbattle.core.others.Extrable;

public interface Controller extends Extrable {

	default public Team team() {
		return player().getTeam();
	}

	default public Player player() {
		return TankBattle.getGame().getProcess().send(new PlayerPropertyEvent(this)).getPlayer();
	}

	default public Controller setPlayer(Player player) {
		TankBattle.getGame().getProcess()
				.send(new PlayerPropertyEvent(this).setPlayer(player).setCode(PlayerPropertyEvent.SET_PLAYER));
		return this;
	}
}
