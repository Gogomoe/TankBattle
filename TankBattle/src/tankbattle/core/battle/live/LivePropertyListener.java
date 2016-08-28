package tankbattle.core.battle.live;

import tankbattle.core.event.Listener;

/**
 * 实体生存属性监听器
 * 
 * @author Gogo
 *
 */
public class LivePropertyListener implements Listener<LivePropertyEvent> {

	final public static String LID = "TankBattle:LivePropertyListener";

	final public static String KEY_HP = "Livable:HP";
	final public static String KEY_DEF = "Livable:DP";
	final public static String KEY_MAXHP = "Livable:MaxHP";
	final public static String KEY_LIVE = "Livable:Live";

	public void listen(LivePropertyEvent event) {
		if (event.canceled() || event.executed() || event.getLiver() == null) {
			return;
		}

		// 生命值
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
		// 最大生命值
		if ((code & LivePropertyEvent.SET_MAXHP) == 0) {
			if (!liver.contains(KEY_MAXHP)) {
				liver.put(KEY_MAXHP, 0);
			}
			event.setMaxHP(liver.getInt(KEY_MAXHP));
		} else {
			liver.put(KEY_MAXHP, event.getMaxHP());
		}
		// 防御力
		if ((code & LivePropertyEvent.SET_DEF) == 0) {
			if (!liver.contains(KEY_DEF)) {
				liver.put(KEY_DEF, 0);
			}
			event.setDEF(liver.getInt(KEY_DEF));
		} else {
			liver.put(KEY_DEF, event.getDEF());
		}
		// 是否生存
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
