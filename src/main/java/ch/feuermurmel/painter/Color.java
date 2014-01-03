package ch.feuermurmel.painter;

public interface Color {
	/**
	 * Returns the pre-multiplied red component of the color.
	 * 
	 * This is a number between 0 and 1.
	 */
	float red();

	/**
	 * Returns the pre-multiplied red component of the color.
	 *
	 * This is a number between 0 and 1.
	 */
	float green();

	/**
	 * Returns the pre-multiplied red component of the color.
	 *
	 * This is a number between 0 and 1.
	 */
	float blue();

	float brightness();

	ColorImpl add(Color other);

	ColorImpl multiply(Color other);

	Color darker(float factor);

	Color darker();

	Color lighter(float factor);

	Color lighter();

	Color scale(float factor);

	Color blend(Color other, float where);

	Color blend(Color other);
}
