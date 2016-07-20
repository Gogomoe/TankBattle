package tankbattle.core.bullet;

import tankbattle.core.Bullet;
import tankbattle.core.attack.Assailable;

/**
 * 东方中标准的小玉弹幕<br/>
 * 默认大小为16*16<br/>
 * 图片共有32种，前16种为有光圈，后16种为无光圈<br/>
 * 有光圈和无光圈的各有16种颜色<br/>
 * 
 * @author Gogo
 *
 */
public class Xiaoyu extends Bullet {

	public Xiaoyu(Assailable attacker, int ATK) {
		super(attacker, ATK);
	}

}
