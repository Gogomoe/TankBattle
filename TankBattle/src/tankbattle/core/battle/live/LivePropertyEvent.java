package tankbattle.core.battle.live;

import tankbattle.core.event.Event;

/**
 * 实体生存属性有关的监听器，可获取或设置以下属性:生命(HP)，最大生命(MaxHP)，防御力(DEF)，是否生存(Live)
 * @author Gogo
 *
 */
public class LivePropertyEvent extends Event {

	final public static int GET_HP = 0;
	final public static int GET_MAXHP = 0;
	final public static int GET_DEF = 0;
	final public static int GET_LIVE = 0;

	final public static int SET_HP = Integer.valueOf("0001", 2);
	final public static int SET_MAXHP = Integer.valueOf("0010", 2);
	final public static int SET_DEF = Integer.valueOf("0100", 2);
	final public static int SET_LIVE = Integer.valueOf("1000", 2);

	private Livable liver;

	private boolean live;

	private int HP, MaxHP, DEF;

	public LivePropertyEvent(Livable liver) {
		super();
		this.liver = liver;
	}

	public LivePropertyEvent(Livable liver, int code) {
		this.code = code;
		this.liver = liver;
	}

	public boolean isLive() {
		return live;
	}

	public LivePropertyEvent setLive(boolean live) {
		this.live = live;
		return this;
	}

	public int getHP() {
		return HP;
	}

	public LivePropertyEvent setHP(int HP) {
		this.HP = HP;
		return this;
	}

	public int getMaxHP() {
		return MaxHP;
	}

	public LivePropertyEvent setMaxHP(int MaxHP) {
		this.MaxHP = MaxHP;
		return this;
	}

	public int getDEF() {
		return DEF;
	}

	public LivePropertyEvent setDEF(int DEF) {
		this.DEF = DEF;
		return this;
	}

	public Livable getLiver() {
		return liver;
	}

	public LivePropertyEvent setLiver(Livable liver) {
		this.liver = liver;
		return this;
	}

}
