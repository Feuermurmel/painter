package ch.feuermurmel.painter;

import java.awt.*;
import java.awt.geom.*;
import java.util.Arrays;
import java.util.List;

import ch.feuermurmel.geometry.Vector2D;

final class GraphicsPainter implements Painter {
	private final Context context;
	private final AffineTransform transform;
	private final AlphaComposite composite;

	GraphicsPainter(Context context, AffineTransform transform, AlphaComposite composite) {
		this.context = context;
		this.transform = transform;
		this.composite = composite;
	}

	@Override
	public Painter transformed(AffineTransform transform) {
		AffineTransform newTransform = new AffineTransform(this.transform);

		newTransform.concatenate(transform);

		return new GraphicsPainter(context, newTransform, composite);
	}

	@Override
	public Rectangle2D getBounds() {
		Shape clip = context.graphics.getClip();

		try {
			return transform.createInverse().createTransformedShape(clip).getBounds2D();
		} catch (NoninvertibleTransformException e) {
			throw new AssertionError(e);
		}
	}

	@Override
	public void draw(Color color, Stroke stroke, List<Shape> shapes) {
		Graphics2D g = context.getGraphics(transform, composite, color, stroke);

		for (Shape i : shapes)
			g.draw(i);
	}

	@Override
	public void fill(Color color, List<Shape> shapes) {
		Graphics2D g = context.getGraphics(transform, composite, color);

		for (Shape i : shapes) {
			g.fill(i);
		}
	}

	@Override
	public void draw(Color color, Stroke stroke, Shape... shapes) {
		draw(color, stroke, Arrays.asList(shapes));
	}

	@Override
	public void fill(Color color, Shape... shapes) {
		fill(color, Arrays.asList(shapes));
	}

	@Override
	public Painter translated(Vector2D vector) {
		return transformed(AffineTransform.getTranslateInstance(vector.getX(), vector.getY()));
	}

	@Override
	public Painter rotated(double angle) {
		return transformed(AffineTransform.getRotateInstance(angle));
	}

	@Override
	public Painter scaled(double factor) {
		return transformed(AffineTransform.getScaleInstance(factor, factor));
	}

	@Override
	public Painter transluced(float opacity) {
		return new GraphicsPainter(context, transform, composite.derive(composite.getAlpha() * opacity));
	}

	@Override
	@Deprecated
	public Graphics2D getGraphics() {
		return context.graphics;
	}

	static final class Context {
		private final Graphics2D graphics;
		private AffineTransform currentTransform = null;
		private Composite currentComposite = null;
		private Color currentColor = null;
		private Stroke currentStroke = null;

		Context(Graphics2D graphics) {
			this.graphics = graphics;
		}

		Graphics2D getGraphics(AffineTransform transform, Composite composite, Color color) {
			applyTransform(transform);
			applyComposite(composite);
			applyColor(color);

			return graphics;
		}

		Graphics2D getGraphics(AffineTransform transform, Composite composite, Color color, Stroke stroke) {
			applyTransform(transform);
			applyComposite(composite);
			applyColor(color);
			applyStroke(stroke);

			return graphics;
		}

		private void applyComposite(Composite composite) {
			if (currentComposite != composite) {
				currentComposite = composite;
				graphics.setComposite(composite);
			}
		}

		private void applyTransform(AffineTransform transform) {
			if (currentTransform != transform) {
				currentTransform = transform;
				graphics.setTransform(transform);
			}
		}

		private void applyColor(Color color) {
			if (currentColor != color) {
				currentColor = color;
				graphics.setPaint(Colors.toAWTColor(color));
			}
		}

		private void applyStroke(Stroke stroke) {
			if (currentStroke != stroke) {
				currentStroke = stroke;
				graphics.setStroke(stroke);
			}
		}
	}
}
