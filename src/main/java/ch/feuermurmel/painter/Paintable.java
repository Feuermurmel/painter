package ch.feuermurmel.painter;

public interface Paintable {
	void paint(Painter p, Invalidator invalidator);
	
	interface Invalidator {
		void invalidate();
	}
}
