package tankbattle.core.battle.live;

import tankbattle.core.others.Extrable;

/**
 * 表示对象可生存（有生命值）<br>
 * 这也意味着对象可被摧毁<br>
 * <br>
 * 本类具有默认实现方式{@link Liver}，实现本类可以参见{@link Extrable}的实现方式<br>
 * 
 * @see Liver
 * @author Gogo
 *
 */
public interface Livable {

	public Livable liver();

	default public boolean isLive() {
		return liver().isLive();
	}

	default public int getHP() {
		return liver().getHP();
	}

	default public int getMaxHP() {
		return liver().getMaxHP();
	}

	default public int getDEF() {
		return liver().getDEF();
	}

	default public Livable setHP(int HP) {
		return liver().setHP(HP);
	}

	default public Livable setDEF(int DEF) {
		return liver().setDEF(DEF);
	}

	default public Livable setMaxHP(int MaxHP) {
		return liver().setMaxHP(MaxHP);
	}

	default public Livable setLive(boolean live) {
		return liver().setLive(live);
	}

}
