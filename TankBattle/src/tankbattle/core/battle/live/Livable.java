package tankbattle.core.battle.live;

import tankbattle.core.TankBattle;
import tankbattle.core.battle.live.LivePropertyEvent;
import tankbattle.core.interfaces.Extrable;

/**
 * 表示对象可生存（有生命值）<br/>
 * 这也意味着对象可被摧毁<br/>
 * 
 * @author Gogo
 *
 */
public interface Livable extends Extrable {

	default public boolean isLive() {
		return TankBattle.getGame().getProcess().send(new LivePropertyEvent(this)).isLive();
	}

	default public int getHP() {
		return TankBattle.getGame().getProcess().send(new LivePropertyEvent(this)).getHP();
	}

	default public int getMaxHP() {
		return TankBattle.getGame().getProcess().send(new LivePropertyEvent(this)).getMaxHP();
	}

	default public int getDP() {
		return TankBattle.getGame().getProcess().send(new LivePropertyEvent(this)).getDP();
	}

	default public Livable setHP(int HP) {
		TankBattle.getGame().getProcess().send(new LivePropertyEvent(this).setHP(HP).setCode(LivePropertyEvent.SET_HP));
		return this;
	}

	default public Livable setLive(boolean live) {
		TankBattle.getGame().getProcess().send(new LivePropertyEvent(this).setLive(live).setCode(LivePropertyEvent.SET_LIVE));
		return this;
	}

	default public Livable set(int HP, int maxHP, boolean live, int DP) {
		TankBattle.getGame().getProcess().send(new LivePropertyEvent(this).setHP(HP).setDP(DP).setLive(live).setMaxHP(maxHP)
				.setCode(LivePropertyEvent.SET_HP + LivePropertyEvent.SET_MAXHP + LivePropertyEvent.SET_DP + LivePropertyEvent.SET_LIVE));
		return this;
	}

}
