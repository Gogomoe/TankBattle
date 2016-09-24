package tankbattle.core.battle.live;

/**
 * 本类是{@link Livable}的默认实现方式<br>
 * 
 * @see Livable
 * @author Gogo
 *
 */
public class Liver implements Livable {

	private int HP;
	private int MaxHP;
	private boolean living;
	private int DEF;

	@Override
	public Livable liver() {
		return this;
	}

	@Override
	public boolean isLive() {
		return living;
	}

	@Override
	public int getHP() {
		return HP;
	}

	@Override
	public int getMaxHP() {
		return MaxHP;
	}

	@Override
	public int getDEF() {
		return DEF;
	}

	@Override
	public Livable setHP(int HP) {
		this.HP = HP;
		return this;
	}

	@Override
	public Livable setDEF(int DEF) {
		this.DEF = DEF;
		return this;
	}

	@Override
	public Livable setMaxHP(int MaxHP) {
		this.MaxHP = MaxHP;
		return this;
	}

	@Override
	public Livable setLive(boolean live) {
		this.living = live;
		return this;
	}

}
