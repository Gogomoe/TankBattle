package tankbattle.core.input;

import tankbattle.core.event.Listener;

/**
 * 按键事件后自动调用指定的Operate的监听器<br>
 * 
 * @author Gogo
 *
 */
public class KeyOperateListener implements Listener<KeyEvent> {

	public static final String LID = "TankBattle:KeyOperateListener";

	@Override
	public void listen(KeyEvent event) {
		if (event.canceled() || event.executed() || event.getView() == null) {
			return;
		}
		// 调用绑定的操作(Operate)
		event.getView().getKeyManager().getAll(event.getKeyCode()).forEach(e -> {
			e.getFun().accept(event);
		});
		event.setExecuted(true);
	}

}
