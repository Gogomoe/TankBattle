package tankbattle.core.battle.live;

import tankbattle.core.event.Event;

public class LivePropertyEvent extends Event {

	final public static int GET_HP = 0;
	final public static int GET_MAXHP = 0;
	final public static int GET_DP = 0;
	final public static int GET_LIVE = 0;

	final public static int SET_HP = Integer.valueOf("0001", 2);
	final public static int SET_MAXHP = Integer.valueOf("0010", 2);
	final public static int SET_DP = Integer.valueOf("0100", 2);
	final public static int SET_LIVE = Integer.valueOf("1000", 2);

	private Livable liver;

	private boolean live;

	private int HP, MaxHP, DP;

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

	public int getDP() {
		return DP;
	}

	public LivePropertyEvent setDP(int dP) {
		DP = dP;
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
