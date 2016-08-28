package tankbattle.core.input;

import javafx.scene.input.KeyCode;
import tankbattle.core.event.Event;
import tankbattle.core.view.View;

/**
 * 按键事件，可以获取到一些按键的基本属性<br>
 * 推荐使用 KeyEvent.create(javafx.scene.input.KeyEvent, View)方法来创建按键事件<br>
 * 
 * @author Gogo
 *
 */
public class KeyEvent extends Event {

	public static final int KEY_PRESSED = 0;
	public static final int KEY_RELEASED = 1;
	public static final int KEY_TYPED = 2;

	private KeyCode keyCode;
	private int keyType;

	private View view;

	private boolean alt;
	private boolean control;
	private boolean shift;
	private boolean meta;

	public KeyEvent(javafx.scene.input.KeyEvent event, View view) {
		this.keyCode = event.getCode();
		this.view = view;
		if (event.getEventType().equals(javafx.scene.input.KeyEvent.KEY_PRESSED)) {
			keyType = KEY_PRESSED;
		} else if (event.getEventType().equals(javafx.scene.input.KeyEvent.KEY_RELEASED)) {
			keyType = KEY_RELEASED;
		} else if (event.getEventType().equals(javafx.scene.input.KeyEvent.KEY_TYPED)) {
			keyType = KEY_TYPED;
		}
		alt = event.isAltDown();
		control = event.isControlDown();
		shift = event.isShiftDown();
		meta = event.isMetaDown();
	}

	public KeyEvent(KeyCode code, int type, View view) {
		super();
		this.keyCode = code;
		this.keyType = type;
		this.view = view;
	}

	public static KeyEvent create(javafx.scene.input.KeyEvent event, View view) {
		if (event.getEventType().equals(javafx.scene.input.KeyEvent.KEY_PRESSED)) {
			return new KeyPressedEvent(event, view);
		} else if (event.getEventType().equals(javafx.scene.input.KeyEvent.KEY_RELEASED)) {
			return new KeyReleasedEvent(event, view);
		} else if (event.getEventType().equals(javafx.scene.input.KeyEvent.KEY_TYPED)) {
			return new KeyTypedEvent(event, view);
		}
		return new KeyEvent(event, view);
	}

	public KeyCode getKeyCode() {
		return keyCode;
	}

	public KeyEvent setKeyCode(KeyCode code) {
		this.keyCode = code;
		return this;
	}

	public int getKeyType() {
		return keyType;
	}

	public KeyEvent setKeyType(int keyType) {
		this.keyType = keyType;
		return this;
	}

	public View getView() {
		return view;
	}

	public KeyEvent setView(View view) {
		this.view = view;
		return this;
	}

	public boolean isAltDown() {
		return alt;
	}

	public KeyEvent setAltDown(boolean alt) {
		this.alt = alt;
		return this;
	}

	public boolean isControlDown() {
		return control;
	}

	public KeyEvent setControlDown(boolean control) {
		this.control = control;
		return this;
	}

	public boolean isShiftDown() {
		return shift;
	}

	public KeyEvent setShiftDown(boolean shift) {
		this.shift = shift;
		return this;
	}

	public boolean isMetaDown() {
		return meta;
	}

	public KeyEvent setMetaDown(boolean meta) {
		this.meta = meta;
		return this;
	}

}
