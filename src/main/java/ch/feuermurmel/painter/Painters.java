package ch.feuermurmel.painter;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Painters {
	private Painters() {
	}

	public static Painter createPainter(Graphics2D graphics) {
		return new GraphicsPainter(new GraphicsPainter.Context(graphics), new AffineTransform(), AlphaComposite.SrcOver);
	}
	
	public static InvalidatablePaintable createInvalidatablePaintable(final Paintable paintable) {
		return new InvalidatablePaintable() {
			private Invalidator currentInvalidator = null;
			
			@Override
			public void invalidate() {
				if (currentInvalidator != null) {
					currentInvalidator.invalidate();
					currentInvalidator = null;
				}
			}

			@Override
			public void paint(Painter p, Invalidator invalidator) {
				paintable.paint(p, invalidator);

				currentInvalidator = invalidator;
			}
		};
	}
}
