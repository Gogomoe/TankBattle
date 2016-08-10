package tankbattle.core.event;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * 用于控制事件，包括发送事件、添加事件监听的事件控制器<br/>
 * 
 * @author Gogo
 *
 */
public class EventProcess implements ListenerGroup {

	private Set<ListenerItem<? extends Event>> listeners = Collections
			.synchronizedSet(new HashSet<ListenerItem<? extends Event>>());
	private Lock lock = new ReentrantLock();

	public <T extends Event> T send(T e) {
		lock.lock();
		listeners(e.getClass()).forEach(i -> {
			@SuppressWarnings("unchecked")
			Listener<T> l = (Listener<T>) i.getListener();
			l.listen(e);
		});
		lock.unlock();
		return e;
	}

	public <T extends Event> T send(T e, List<ListenerItem<T>> listeners) {
		lock.lock();
		listeners.forEach(i -> {
			Listener<T> l = (Listener<T>) i.getListener();
			l.listen(e);
		});
		lock.unlock();
		return e;
	}

	public <T extends Event> List<ListenerItem<? extends Event>> listeners(Class<T> listened) {
		if (listened == null) {
			return null;
		}
		lock.lock();
		List<ListenerItem<? extends Event>> l = listeners.stream()
				.filter(e -> e.getListened().isAssignableFrom(listened)).sorted((a, b) -> {
					int x = a.getPriority() - b.getPriority();
					return x == 0 ? a.getName().compareTo(b.getName()) : x;
				}).collect(Collectors.toList());
		lock.unlock();
		return l;
	}

	@Override
	public List<ListenerItem<? extends Event>> listeners() {
		lock.lock();
		List<ListenerItem<? extends Event>> l = listeners.stream().sorted((a, b) -> {
			int x = a.getPriority() - b.getPriority();
			return x == 0 ? a.getName().compareTo(b.getName()) : x;
		}).collect(Collectors.toList());
		lock.unlock();
		return l;
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
		lock.lock();
		listeners.add(new ListenerItem<>(name, priority, clazz, listener));
		lock.unlock();
		listener.init(this);
		return name;
	}

	@Override
	public ListenerItem<? extends Event> getListener(String name) {
		if (name == null) {
			return null;
		}
		lock.lock();
		Optional<ListenerItem<? extends Event>> o = listeners.stream().filter(e -> e.getName().equals(name))
				.findFirst();
		lock.unlock();

		return o.isPresent() ? o.get() : null;
	}

	@Override
	public ListenerItem<? extends Event> getListener(Listener<? extends Event> listener) {
		if (listener == null) {
			return null;
		}
		lock.lock();
		Optional<ListenerItem<? extends Event>> o = listeners.stream().filter(e -> e.getListener().equals(listener))
				.findFirst();
		lock.unlock();
		return o.isPresent() ? o.get() : null;
	}

	@Override
	public ListenerItem<? extends Event> removeListener(String name) {
		ListenerItem<? extends Event> l = getListener(name);
		lock.lock();
		listeners.remove(l);
		lock.unlock();
		l.getListener().destory(this);
		return l;
	}

	@Override
	public ListenerItem<? extends Event> removeListener(Listener<? extends Event> listener) {
		ListenerItem<? extends Event> l = getListener(listener);
		lock.lock();
		listeners.remove(l);
		lock.unlock();
		l.getListener().destory(this);
		return l;
	}

}
