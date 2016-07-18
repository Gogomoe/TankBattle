package tankbattle.core.interfaces;

/**
 * 可以进行攻击的<br/>
 * 本接口表示的是能进行攻击这个行为，而不是表示能对敌人造成伤害<br/>
 * 
 * @author Gogo
 *
 */
public interface Assailable {

	/**
	 * 进行攻击
	 */
	public void attack();

}
