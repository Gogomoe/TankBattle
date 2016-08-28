package tankbattle.core.input;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.scene.input.KeyCode;

/**
 * 用于绑定某个键的功能<br>
 * 
 * @author Gogo
 *
 */
public class KeyManager {

	private Map<KeyCode, Set<KeyOperation>> map = Collections.synchronizedMap(new HashMap<>());

	public void add(KeyCode code, KeyOperation op) {
		Set<KeyOperation> set = map.get(code);
		if (set == null) {
			set = Collections.synchronizedSet(new HashSet<>());
			map.put(code, set);
		}
		set.add(op);
	}

	public void remove(KeyCode code, KeyOperation op) {
		Set<KeyOperation> set = map.get(code);
		if (set == null) {
			set = Collections.synchronizedSet(new HashSet<>());
			map.put(code, set);
		}
		set.remove(op);
	}

	public Set<KeyOperation> getAll(KeyCode code) {
		Set<KeyOperation> set = map.get(code);
		if (set == null) {
			set = Collections.synchronizedSet(new HashSet<>());
			map.put(code, set);
		}
		return set;
	}

	public Map<KeyCode, Set<KeyOperation>> getMap() {
		return map;
	}

}
