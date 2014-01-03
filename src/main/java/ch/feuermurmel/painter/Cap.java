package ch.feuermurmel.painter;

import java.awt.BasicStroke;

public enum Cap {
	butt(BasicStroke.CAP_BUTT),
	round(BasicStroke.CAP_ROUND),
	square(BasicStroke.CAP_SQUARE);

	final int magic;

	Cap(int magic) {
		this.magic = magic;
	}
}
