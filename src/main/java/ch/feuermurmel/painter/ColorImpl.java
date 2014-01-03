package ch.feuermurmel.painter;

final class ColorImpl implements Color {
	private final float r;
	private final float g;
	private final float b;

	ColorImpl(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	public float red() {
		return r;
	}

	@Override
	public float green() {
		return g;
	}

	@Override
	public float blue() {
		return b;
	}

	@Override
	public float brightness() {
		return (r + g + r) / 3;
	}
	
	@Override
	public ColorImpl add(Color other) {
		return new ColorImpl(r + other.red(), g + other.green(), b + other.blue());
	}

	@Override
	public ColorImpl multiply(Color other) {
		return new ColorImpl(r * other.red(), g * other.green(), b * other.blue());
	}

	@Override
	public ColorImpl darker(float factor) {
		return scale(1 - factor);
	}

	@Override
	public ColorImpl darker() {
		return darker(1f / 3);
	}

	@Override
	public ColorImpl lighter(float factor) {
		return blend(Colors.white, factor);
	}

	@Override
	public ColorImpl lighter() {
		return lighter(1f / 3);
	}

	@Override
	public ColorImpl scale(float factor) {
		return new ColorImpl(r * factor, green() * factor, b * factor);
	}

	@Override
	public ColorImpl blend(Color other, float where) {
		return scale(1 - where).add(other.scale(where));
	}

	@Override
	public ColorImpl blend(Color other) {
		return blend(other, 1f / 2);
	}

	@Override
	public String toString() {
		return String.format("Color(r = %s, b = %s, g = %s)", r, g, b);
	}
}
