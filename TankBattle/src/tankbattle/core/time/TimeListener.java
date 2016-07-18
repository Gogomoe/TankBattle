package tankbattle.core.time;

import java.util.function.Consumer;

import tankbattle.core.event.Listener;

public class TimeListener implements Listener<TimeEvent> {

	private double next = 0;

	private double interval;

	private Consumer<TimeEvent> run;

	public TimeListener(double interval, Consumer<TimeEvent> run) {
		this.interval = interval;
		this.run = run;
	}

	@Override
	public void listen(TimeEvent event) {
		if (next == 0) {
			next = event.getTotal() + interval;
		}
		if (event.getTotal() >= next) {
			next += interval;
			run.accept(event);
		}
	}

	public double getInterval() {
		return interval;
	}

	public TimeListener setInterval(int interval) {
		this.interval = interval;
		return this;
	}

	public Consumer<TimeEvent> getRun() {
		return run;
	}

	public TimeListener setRun(Consumer<TimeEvent> run) {
		this.run = run;
		return this;
	}

}
