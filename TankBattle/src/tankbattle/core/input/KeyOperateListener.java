package tankbattle.core.input;

import tankbattle.core.event.Listener;

public class KeyOperateListener implements Listener<KeyEvent> {

	public static final String LID = "TankBattle:KeyOperateListener";

	@Override
	public void listen(KeyEvent event) {
		if (event.canceled() || event.executed() || event.getView() == null) {
			return;
		}
		event.getView().getKeyManager().getAll(event.getKeyCode()).forEach(e -> {
			e.getFun().accept(event);
		});
		event.setExecuted(true);
	}

}
