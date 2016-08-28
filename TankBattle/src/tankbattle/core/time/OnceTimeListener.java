package tankbattle.core.time;

import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;
import tankbattle.core.event.ListenerItem;

/**
 * 指定时间后执行一次的事件<br>
 * 
 * @author Gogo
 *
 */
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
	public void init(EventProcess process, ListenerItem<TimeEvent> item) {
		this.process = process;
	}

}
