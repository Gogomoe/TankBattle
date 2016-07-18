package tankbattle.core.others;

import java.util.Set;

public class SetUtils {

	public static <T> boolean contains(Set<T> set, T t) {
		for (T t1 : set) {
			if (t1.equals(t)) {
				return true;
			}
		}
		return false;
	}

}
