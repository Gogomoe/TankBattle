package tankbattle.core.battle.live;

import tankbattle.core.event.Listener;

public class LivePropertyListener implements Listener<LivePropertyEvent> {

	final public static String LID = "TankBattle:LivePropertyListener";

	final public static String KEY_HP = "Livable:HP";
	final public static String KEY_DP = "Livable:DP";
	final public static String KEY_MAXHP = "Livable:MaxHP";
	final public static String KEY_LIVE = "Livable:Live";

	public void listen(LivePropertyEvent event) {
		if (event.canceled() || event.executed() || event.getLiver() == null) {
			return;
		}

		int code = event.code();
		Livable liver = event.getLiver();
		if ((code & LivePropertyEvent.SET_HP) == 0) {
			if (!liver.contains(KEY_HP)) {
				liver.put(KEY_HP, 0);
			}
			event.setHP(liver.getInt(KEY_HP));
		} else {
			liver.put(KEY_HP, event.getHP());
		}
		if ((code & LivePropertyEvent.SET_MAXHP) == 0) {
			if (!liver.contains(KEY_MAXHP)) {
				liver.put(KEY_MAXHP, 0);
			}
			event.setMaxHP(liver.getInt(KEY_MAXHP));
		} else {
			liver.put(KEY_MAXHP, event.getMaxHP());
		}
		if ((code & LivePropertyEvent.SET_DP) == 0) {
			if (!liver.contains(KEY_DP)) {
				liver.put(KEY_DP, 0);
			}
			event.setDP(liver.getInt(KEY_DP));
		} else {
			liver.put(KEY_DP, event.getDP());
		}
		if ((code & LivePropertyEvent.SET_LIVE) == 0) {
			if (!liver.contains(KEY_LIVE)) {
				liver.put(KEY_LIVE, false);
			}
			event.setLive(liver.getBool(KEY_LIVE));
		} else {
			liver.put(KEY_LIVE, event.isLive());
		}

		event.setExecuted(true);
	}

}
