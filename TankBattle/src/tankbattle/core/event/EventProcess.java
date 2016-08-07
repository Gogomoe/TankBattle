package tankbattle.core.event;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用于控制事件，包括发送事件、添加事件监听的事件控制器<br/>
 * 
 * @author Gogo
 *
 */
public class EventProcess implements ListenerGroup {

	private Set<ListenerItem<? extends Event>> listeners = Collections
			.synchronizedSet(new HashSet<ListenerItem<? extends Event>>());;

	public <T extends Event> T send(T e) {
		listeners(e.getClass()).forEach(i -> {
			@SuppressWarnings("unchecked")
			Listener<T> l = (Listener<T>) i.getListener();
			l.listen(e);
		});
		return e;
	}

	public <T extends Event> T send(T e, List<ListenerItem<T>> listeners) {
		listeners.forEach(i -> {
			Listener<T> l = (Listener<T>) i.getListener();
			l.listen(e);
		});
		return e;
	}

	public <T extends Event> List<ListenerItem<? extends Event>> listeners(Class<T> listened) {
		if (listened == null) {
			return null;
		}
		return listeners.stream().filter(e -> e.getListened().isAssignableFrom(listened)).sorted((a, b) -> {
			int x = a.getPriority() - b.getPriority();
			return x == 0 ? a.getName().compareTo(b.getName()) : x;
		}).collect(Collectors.toList());
	}

	@Override
	public List<ListenerItem<? extends Event>> listeners() {
		return listeners.stream().sorted((a, b) -> {
			int x = a.getPriority() - b.getPriority();
			return x == 0 ? a.getName().compareTo(b.getName()) : x;
		}).collect(Collectors.toList());
	}

	@Override
	public <T extends Event> String addListener(Class<T> clazz, Listener<T> listener) {
		return addListener(listener.getClass().getName(), Listener.NORMAL, clazz, listener);
	}

	@Override
	public <T extends Event> String addListener(int priority, Class<T> clazz, Listener<T> listener) {
		return addListener(listener.getClass().getName(), priority, clazz, listener);
	}

	@Override
	public <T extends Event> String addListener(String name, Class<T> clazz, Listener<T> listener) {
		return addListener(name, Listener.NORMAL, clazz, listener);
	}

	@Override
	public <T extends Event> String addListener(String name, int priority, Class<T> clazz, Listener<T> listener) {
		if (name == null || clazz == null || listener == null) {
			throw new NullPointerException("addListener传入null");
		}
		listeners.add(new ListenerItem<>(name, priority, clazz, listener));
		listener.init(this);
		return name;
	}

	@Override
	public ListenerItem<? extends Event> getListener(String name) {
		if (name == null) {
			return null;
		}
		Optional<ListenerItem<? extends Event>> o = listeners.stream().filter(e -> e.getName().equals(name))
				.findFirst();
		if (o.isPresent()) {
			return o.get();
		}
		return null;
	}

	@Override
	public ListenerItem<? extends Event> getListener(Listener<? extends Event> listener) {
		if (listener == null) {
			return null;
		}
		Optional<ListenerItem<? extends Event>> o = listeners.stream().filter(e -> e.getListener().equals(listener))
				.findFirst();

		if (o.isPresent()) {
			return o.get();
		}
		return null;
	}

	@Override
	public ListenerItem<? extends Event> removeListener(String name) {
		ListenerItem<? extends Event> l = getListener(name);
		listeners.remove(l);
		l.getListener().destory(this);
		return l;
	}

	@Override
	public ListenerItem<? extends Event> removeListener(Listener<? extends Event> listener) {
		ListenerItem<? extends Event> l = getListener(listener);
		listeners.remove(l);
		l.getListener().destory(this);
		return l;
	}

}
