package tankbattle.core.interfaces;

import tankbattle.core.battle.live.Livable;

/**
 * 实现本接口的对象可造成伤害的<br/>
 * 本接口表示的是造成伤害，而不是表示能对敌人发动攻击<br/>
 * 
 * @author Gogo
 *
 */
public interface Damagable {

	public void damage(Livable target);

}
