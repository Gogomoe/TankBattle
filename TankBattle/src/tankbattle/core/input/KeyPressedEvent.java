package tankbattle.core.input;

import javafx.scene.input.KeyCode;
import tankbattle.core.view.View;

public class KeyPressedEvent extends KeyEvent {

	public KeyPressedEvent(javafx.scene.input.KeyEvent event, View view) {
		super(event, view);
	}

	public KeyPressedEvent(KeyCode code, View view) {
		super(code, KeyEvent.KEY_PRESSED, view);
	}

}
