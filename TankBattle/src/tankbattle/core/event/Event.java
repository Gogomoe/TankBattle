package tankbattle.core.event;

import tankbattle.core.interfaces.Extrable;

/**
 * 事件是事件驱动的核心<br/>
 * 游戏中每发生一个动作都会有对应的事件产生<br/>
 *
 * @author Gogo
 */
public class Event implements Extrable {

	protected boolean canceled;
	protected boolean executed;

	protected int code;

	public Event() {
		super();
	}

	public Event(int code) {
		super();
		this.code = code;
	}

	public boolean canceled() {
		return canceled;
	}

	public Event setcanceled(boolean cancel) {
		this.canceled = cancel;
		return this;
	}

	public boolean executed() {
		return executed;
	}

	public Event setExecuted(boolean executed) {
		this.executed = executed;
		return this;
	}

	public int code() {
		return code;
	}

	public Event setCode(int code) {
		this.code = code;
		return this;
	}

}
