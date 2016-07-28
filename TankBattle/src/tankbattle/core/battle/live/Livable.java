package tankbattle.core.battle.live;

import tankbattle.core.TankBattle;
import tankbattle.core.battle.live.LivePropertyEvent;
import tankbattle.core.others.Extrable;

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

	default public int getDEF() {
		return TankBattle.getGame().getProcess().send(new LivePropertyEvent(this)).getDEF();
	}

	default public Livable setHP(int HP) {
		TankBattle.getGame().getProcess().send(new LivePropertyEvent(this).setHP(HP).setCode(LivePropertyEvent.SET_HP));
		return this;
	}

	default public Livable setDEF(int DEF) {
		TankBattle.getGame().getProcess()
				.send(new LivePropertyEvent(this).setDEF(DEF).setCode(LivePropertyEvent.SET_DEF));
		return this;
	}

	default public Livable setMaxHP(int MaxHP) {
		TankBattle.getGame().getProcess()
				.send(new LivePropertyEvent(this).setMaxHP(MaxHP).setCode(LivePropertyEvent.SET_MAXHP));
		return this;
	}

	default public Livable setLive(boolean live) {
		TankBattle.getGame().getProcess()
				.send(new LivePropertyEvent(this).setLive(live).setCode(LivePropertyEvent.SET_LIVE));
		return this;
	}

}
