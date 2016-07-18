package tankbattle.core.event;

import java.util.List;

public interface ListenerGroup {

	public List<ListenerItem<? extends Event>> listeners();

	public <T extends Event> List<ListenerItem<? extends Event>> listeners(Class<T> listened);

	public <T extends Event> String addListener(Class<T> clazz, Listener<T> listener);

	public <T extends Event> String addListener(int priority, Class<T> clazz, Listener<T> listener);

	public <T extends Event> String addListener(String name, Class<T> clazz, Listener<T> listener);

	public <T extends Event> String addListener(String name, int priority, Class<T> clazz, Listener<T> listener);

	public ListenerItem<? extends Event> getListener(String name);

	public ListenerItem<? extends Event> getListener(Listener<? extends Event> listener);

	public ListenerItem<? extends Event> removeListener(String name);

	public ListenerItem<? extends Event> removeListener(Listener<? extends Event> listener);
}
