package tankbattle.core.others;

import tankbattle.core.shape.Circle;
import tankbattle.core.shape.Rect;
import tankbattle.core.shape.Shape;
import tankbattle.core.shape.VCircle;
import tankbattle.core.shape.VRect;

public class ShapeUtils {

	public static double getWidth(Shape s) {
		if (s instanceof Rect) {
			return ((Rect) s).getWidth();
		}
		if (s instanceof VRect) {
			return ((VRect) s).getRect().getWidth();
		}
		if (s instanceof Circle) {
			return ((Circle) s).getRadius();
		}
		if (s instanceof VCircle) {
			return ((VCircle) s).getCircle().getRadius();
		}
		return 0;
	}

	public static double getHeight(Shape s) {
		if (s instanceof Rect) {
			return ((Rect) s).getHeight();
		}
		if (s instanceof VRect) {
			return ((VRect) s).getRect().getHeight();
		}
		if (s instanceof Circle) {
			return ((Circle) s).getRadius();
		}
		if (s instanceof VCircle) {
			return ((VCircle) s).getCircle().getRadius();
		}
		return 0;
	}

}
