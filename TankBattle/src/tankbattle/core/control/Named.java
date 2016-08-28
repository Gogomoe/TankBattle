package tankbattle.core.control;

/**
 * 拥有名字的对象，名字通常是不重复的，因此可以当做键来使用<br>
 * @author Gogo
 *
 */
@FunctionalInterface
public interface Named {

	public String name();

}
