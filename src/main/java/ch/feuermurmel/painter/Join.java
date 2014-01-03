package ch.feuermurmel.painter;

import java.awt.BasicStroke;

public enum Join {
	bevel(BasicStroke.JOIN_BEVEL),
	miter(BasicStroke.JOIN_MITER),
	round(BasicStroke.JOIN_ROUND);

	final int magic;

	Join(int magic) {
		this.magic = magic;
	}
}
