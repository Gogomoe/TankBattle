package tankbattle.core.control;

import tankbattle.core.Extrable;

public interface Controller extends Extrable {

	public Team team();

	public Player player();
}
