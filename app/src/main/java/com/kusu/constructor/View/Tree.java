package com.kusu.constructor.View;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.kusu.constructor.Example.Default;
import com.kusu.constructor.Formul;
import com.kusu.constructor.Prototype.Leaf;

/**
 * Created by mikhail.chvarkou on 1/5/2017.
 */

public class Tree {
	private Formul formul;
	private Leaf root = Default.getDefRoot();

	public Tree(Formul formul) {
		this.formul = formul;
	}

	public void draw(Canvas canvas) {
		if (root == null)
			return;
		int height = formul.getSettings().getFormulHeight(canvas.getHeight());
		formul.getSettings().changeScale(
				canvas.getWidth(),
				height, root.getWidthToEnd(),
				root.getHeightToEnd());

		int[] s = root.getTopBottom(new int[]{
				height / 2, height / 2, height / 2
		});
		int dH = (height - s[1] - s[2]) / 2;
		int dW = canvas.getWidth() - formul.getSettings().getPadding() * 2 - root.getWidthToEnd();

		if (root != null)
			root.draw(canvas, formul.getSettings().getPadding() + dW / 2, height / 2 + dH);
	}

	public Leaf getRoot() {
		return root;
	}

	public void setRoot(Leaf root) {
		this.root = root;
	}

	public void updateRootReferences() {
		root.setTreeSettings(formul.getSettings());
	}
}
