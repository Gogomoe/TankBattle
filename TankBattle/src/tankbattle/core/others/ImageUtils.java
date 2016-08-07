package tankbattle.core.others;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageUtils {

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

	public static BufferedImage copyImage(BufferedImage img) {
		if (img == null) {
			return null;
		}
		return new BufferedImage(img.getColorModel(), img.copyData(null), img.getColorModel().isAlphaPremultiplied(),
				null);
	}

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
