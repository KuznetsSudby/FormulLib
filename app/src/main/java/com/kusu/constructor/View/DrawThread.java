package com.kusu.constructor.View;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.kusu.constructor.Example.Default;
import com.kusu.constructor.Formul;
import com.kusu.constructor.LeafType.Movable;
import com.kusu.constructor.Prototype.Leaf;
import com.kusu.constructor.Utils.Utils;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by KuSu on 08.11.2016.
 */

public class DrawThread {

	private final Formul formul;

	public DrawThread(Formul formul) {
		this.formul = formul;
	}

	public void onDraw(Canvas canvas) {
		drawBackground(canvas);
		formul.getTree().draw(canvas);
		formul.getMovePart().draw(canvas);
		formul.getWorker().draw(canvas);
	}



	protected void drawBackground(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(formul.getSettings().getBackground());
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(0, 0, formul.getWidth(), formul.getHeight(), paint);
	}
}
