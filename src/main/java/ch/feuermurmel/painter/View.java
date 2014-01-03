package ch.feuermurmel.painter;

import javax.swing.JFrame;

import ch.feuermurmel.geometry.Vector2D;

public interface View {
	void show();

	void resize(int x, int y);

	Vector2D size();

	void repaint();

	JFrame getFrame();
}
