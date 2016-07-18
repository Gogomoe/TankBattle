package tankbattle.core.time;

import tankbattle.core.event.Event;

public class TimeEvent extends Event {

	private int total;

	private int interval;

	public TimeEvent() {
		super();
	}

	public TimeEvent(int interval, int total) {
		super();
		this.total = total;
		this.interval = interval;
	}

	public int getTotal() {
		return total;
	}

	public TimeEvent setTotal(int total) {
		this.total = total;
		return this;
	}

	public int getInterval() {
		return interval;
	}

	public TimeEvent setInterval(int interval) {
		this.interval = interval;
		return this;
	}

}
