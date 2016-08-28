package tankbattle.core.others;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * 本类封装了一些处理图片的常用方法<br>
 * 
 * @author Gogo
 *
 */
public class ImageUtils {

	/**
	 * 截取部分图片<br>
	 * 
	 * @param img
	 *            源图片
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage cutImage(Image img, int x, int y, int width, int height) {
		if (img == null) {
			return null;
		}
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = bi.createGraphics();
		g.drawImage(img, 0, 0, width, height, x, y, x + width, y + height, null);
		g.dispose();
		return bi;
	}

	/**
	 * 根据给出的行和列将图片切割成等大的小图<br>
	 * 
	 * @param img
	 *            源图片
	 * @param hsize
	 *            每行的图片数
	 * @param vsize
	 *            每列的图片数
	 * @return
	 */
	public static BufferedImage[] cutImage(Image img, int hsize, int vsize) {
		if (img == null || hsize < 1 || vsize < 1) {
			return null;
		}
		BufferedImage[] imgs = new BufferedImage[hsize * vsize];
		int w = img.getWidth(null) / hsize, h = img.getHeight(null) / vsize;
		for (int i = 0; i < imgs.length; i++) {
			int x = i % hsize, y = i / hsize;
			imgs[i] = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D g = imgs[i].createGraphics();
			g.drawImage(img, 0, 0, w, h, x * w, y * h, x * w + w, y * h + h, null);
			g.dispose();
		}
		return imgs;
	}

	/**
	 * 复制一张图片<br>
	 * 
	 * @param img
	 * @return
	 */
	public static BufferedImage copyImage(BufferedImage img) {
		if (img == null) {
			return null;
		}
		return new BufferedImage(img.getColorModel(), img.copyData(null), img.getColorModel().isAlphaPremultiplied(),
				null);
	}

	/**
	 * 转化一张图片为 BufferedImage<br>
	 * 
	 * @param img
	 * @return
	 */
	public static BufferedImage toBufferedImage(Image img) {
		if (img == null) {
			return null;
		}
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = bi.createGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return bi;
	}

}
