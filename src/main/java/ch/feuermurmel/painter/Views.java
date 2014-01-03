package ch.feuermurmel.painter;

public final class Views {
	private Views() {
	}

	public static View create(Paintable paintable) {
		return new ViewImpl(paintable);
	}
}
