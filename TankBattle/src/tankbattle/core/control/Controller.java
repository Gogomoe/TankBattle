package tankbattle.core.control;

import tankbattle.core.TankBattle;
import tankbattle.core.others.Extrable;

/**
 * 表示一个实体是可控制的，它通常会属于一个玩家(Player)或一个队伍(System)<br>
 * 
 * @author Gogo
 *
 */
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
