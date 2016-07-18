package tankbattle.core.event;

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