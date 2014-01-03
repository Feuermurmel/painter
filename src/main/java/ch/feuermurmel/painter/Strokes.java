package ch.feuermurmel.painter;

import java.awt.BasicStroke;
import java.awt.Stroke;

public class Strokes {
	private Strokes() {
	}

	@SuppressWarnings("MagicConstant")
	public static Stroke basic(float width, Cap cap, Join join, float[] pattern) {
		return new BasicStroke(width, cap.magic, join.magic, 10, pattern, 0f);
	}
	
	public static Stroke basic(float width, Cap cap, Join join, float pattern) {
		return basic(width, cap, join, new float[] { pattern, pattern });
	}

	public static Stroke basic(float width, Cap cap, Join join) {
		return basic(width, cap, join, null);
	}

	public static Stroke basic(float width, float[] pattern) {
		return basic(width, Cap.butt, Join.round, pattern);
	}

	public static Stroke basic(float width, float pattern) {
		return basic(width, Cap.butt, Join.round, new float[] { pattern, pattern });
	}

	public static Stroke basic(float width) {
		return basic(width, null);
	}
}
