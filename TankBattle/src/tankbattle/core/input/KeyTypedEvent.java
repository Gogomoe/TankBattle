package tankbattle.core.input;

import javafx.scene.input.KeyCode;
import tankbattle.core.view.View;

public class KeyTypedEvent extends KeyEvent {

	public KeyTypedEvent(javafx.scene.input.KeyEvent event, View view) {
		super(event, view);
	}

	public KeyTypedEvent(KeyCode code, View view) {
		super(code, KeyEvent.KEY_TYPED, view);
	}

}
