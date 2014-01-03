package ch.feuermurmel.painter;

public final class Colors {
	public static final Color black = create(0f, 0f, 0f);
	public static final Color blue = create(0f, 0f, 1f);
	public static final Color green = create(0f, 1f, 0f);
	public static final Color cyan = create(0f, 1f, 1f);
	public static final Color red = create(1f, 0f, 0f);
	public static final Color magenta = create(1f, 0f, 1f);
	public static final Color yellow = create(1f, 1f, 0f);
	public static final Color white = create(1f, 1f, 1f);

	private Colors() {
	}

	public static Color gray(float v) {
		return create(v, v, v);
	}

	public static Color create(float r, float g, float b) {
		return new ColorImpl(r, g, b);
	}

	public static java.awt.Color toAWTColor(Color color) {
		return new java.awt.Color(color.red(), color.green(), color.blue());
	}
}
