package tankbattle.core.input;

import javafx.scene.input.KeyCode;
import tankbattle.core.view.View;

public class KeyReleasedEvent extends KeyEvent {

	public KeyReleasedEvent(javafx.scene.input.KeyEvent event, View view) {
		super(event, view);
	}

	public KeyReleasedEvent(KeyCode code, View view) {
		super(code, KeyEvent.KEY_RELEASED, view);
	}

}
