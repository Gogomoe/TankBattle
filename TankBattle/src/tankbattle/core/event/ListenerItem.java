package tankbattle.core.event;

/**
 * 事件项是事件储存在事件进程中的方式<br>
 * 相比于事件，事件项多了一些属性<br>
 * 用于表示事件id的name属性<br>
 * 表示事件执行优先级的priority属性<br>
 * 
 * @author Gogo
 *
 * @param <T>
 */
public class ListenerItem<T extends Event> {

	private String name;

	private Listener<T> listener;

	private int priority;

	private Class<T> listened;

	public ListenerItem(String name, int priority, Class<T> listened, Listener<T> listener) {
		this.name = name;
		this.priority = priority;
		this.listened = listened;
		this.listener = listener;
	}

	public Listener<T> getListener() {
		return listener;
	}

	public int getPriority() {
		return priority;
	}

	public ListenerItem<T> setPriority(int priority) {
		this.priority = priority;
		return this;
	}

	public String getName() {
		return name;
	}

	public Class<T> getListened() {
		return listened;
	}

}