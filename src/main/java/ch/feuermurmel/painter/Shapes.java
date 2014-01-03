package ch.feuermurmel.painter;

import java.awt.Font;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.*;
import java.util.Arrays;
import java.util.List;

import ch.feuermurmel.geometry.Vector2D;

public class Shapes {
	private Shapes() {
	}

	private static final FontRenderContext fontRenderContext = new FontRenderContext(null, true, true);

	public static Shape circle(Vector2D center, double radius) {
		return new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
	}

	public static Shape rectangle(Vector2D corner, double sizeX, double sizeY) {
		return new Rectangle2D.Double(corner.getX(), corner.getY(), sizeX, sizeY);
	}

	public static Shape rectangle(Vector2D corner1, Vector2D corner2) {
		Vector2D size = corner2.minus(corner1);

		return new Rectangle2D.Double(corner1.getX(), corner1.getY(), size.getX(), size.getY());
	}

	public static Shape line(Vector2D p1, Vector2D p2) {
		return new Line2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}

	public static Shape arc(Vector2D center, double r, double angle1, double angle2) {
		return new Arc2D.Double(center.getX() - r, center.getY() - r, r * 2, r * 2, -angle1 * 180 / Math.PI, (angle1 - angle2) * 180 / Math.PI, Arc2D.OPEN);
	}

	public static Shape polygon(List<Vector2D> points) {
		Path2D.Double path = new Path2D.Double(Path2D.WIND_EVEN_ODD, points.size());

		path.moveTo(points.get(0).getX(), points.get(0).getY());

		for (Vector2D i : points.subList(1, points.size()))
			path.lineTo(i.getX(), i.getY());

		path.closePath();

		return path;
	}

	public static Shape polygon(Vector2D... points) {
		return polygon(Arrays.asList(points));
	}

	public static Shape text(Vector2D position, Font font, String string, double alignment) {
		GlyphVector glyphVector2D = font.createGlyphVector(fontRenderContext, string);

		AffineTransform transform = new AffineTransform(1, 0, 0, -1, position.getX() - glyphVector2D.getLogicalBounds().getWidth() * alignment, position.getY());

		return transform.createTransformedShape(glyphVector2D.getOutline());
	}

	public static Shape text(Vector2D position, Font font, String string) {
		return text(position, font, string, 0);
	}
}
