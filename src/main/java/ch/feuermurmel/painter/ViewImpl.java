package ch.feuermurmel.painter;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import javax.swing.*;

import ch.feuermurmel.geometry.Geometries;
import ch.feuermurmel.geometry.Vector2D;

import static java.awt.RenderingHints.*;
import static javax.swing.WindowConstants.*;

final class ViewImpl implements View {
	private final Paintable paintable;
	private final JFrame frame = new JFrame();
	private final Paintable.Invalidator invalidator = new Invalidator();
	private AffineTransform currentTransform;

	ViewImpl(Paintable paintable) {
		this.paintable = paintable;

		JPanel contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				Rectangle bounds = g.getClipBounds();

				g2.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
				g2.setRenderingHint(KEY_STROKE_CONTROL, VALUE_STROKE_PURE);
				g2.clearRect(bounds.x, bounds.y, bounds.width, bounds.height);

				ViewImpl.this.paintable.paint(Painters.createPainter(g2).transformed(currentTransform), invalidator);
			}
		};
		
		frame.setSize(500, 320);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setContentPane(contentPane);

		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				updateTransform();
			}
		});

		updateTransform();
	}

	@Override
	public void show() {
		frame.setVisible(true);
	}

	@Override
	public void resize(int x, int y) {
		frame.setSize(x, y);
	}

	@Override
	public Vector2D size() {
		return Geometries.vector(frame.getWidth(), frame.getHeight());
	}

	@Override
	public void repaint() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.repaint();
			}
		});
	}

	@Override
	public JFrame getFrame() {
		return frame;
	}

	private void updateTransform() {
		AffineTransform transform = new AffineTransform();

		transform.scale(1, -1);
		transform.translate(0, -frame.getContentPane().getHeight());

		currentTransform = transform;
	}

	private final class Invalidator implements Paintable.Invalidator {
		@Override
		public void invalidate() {
			repaint();
		}
	}
}
