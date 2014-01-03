package ch.feuermurmel.painter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.List;

import ch.feuermurmel.geometry.Vector2D;

public interface Painter {
	void draw(Color color, Stroke stroke, Shape... shapes);

	void draw(Color color, Stroke stroke, List<Shape> shapes);

	void fill(Color color, Shape... shapes);

	void fill(Color color, List<Shape> shapes);

	Painter transformed(AffineTransform transform);

	Painter translated(Vector2D vector);

	Painter rotated(double angle);

	Painter scaled(double factor);

	Painter transluced(float opacity);

	Rectangle2D getBounds();

	Graphics2D getGraphics();
}
