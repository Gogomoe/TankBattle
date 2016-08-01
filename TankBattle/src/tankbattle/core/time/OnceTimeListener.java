package tankbattle.core.time;

import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;

public class OnceTimeListener implements Listener<TimeEvent> {

	private double time;

	private double pass;

	private EventProcess process;

	private Runnable run;

	public OnceTimeListener(double time, Runnable run) {
		super();
		this.time = Math.abs(time);
		this.run = run;
	}

	@Override
	public void listen(TimeEvent event) {
		pass += event.getInterval();
		if (pass >= time) {
			run.run();
			process.removeListener(this);
		}
	}

	@Override
	public void init(EventProcess process) {
		this.process = process;
	}

}
