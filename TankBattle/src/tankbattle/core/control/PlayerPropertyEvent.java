package tankbattle.core.control;

import tankbattle.core.event.Event;

public class PlayerPropertyEvent extends Event {

	final public static int GET_PLAYER = 0;
	final public static int SET_PLAYER = 1;

	private Controller controller;
	private Player player;

	public PlayerPropertyEvent(Controller controller) {
		super();
		this.controller = controller;
	}

	public PlayerPropertyEvent(Controller controller, int code) {
		super(code);
		this.controller = controller;
	}

	public Player getPlayer() {
		return player;
	}

	public PlayerPropertyEvent setPlayer(Player player) {
		this.player = player;
		return this;
	}

	public Controller getController() {
		return controller;
	}

}
