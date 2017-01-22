package team.fastflow.kusu.constructor.LeafType;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import team.fastflow.kusu.constructor.Prototype.Leaf;
import team.fastflow.kusu.constructor.Prototype.PaintableBlock;
import team.fastflow.kusu.constructor.Moduls.Settings;

public class Division extends Leaf {

	public Division(String symbols) {
		this.symbols = symbols;
	}

	@Override
	public int getType() {
		return PaintableBlock.SYMBOL_DIVISION;
	}

	@Override
	public int getHeight() {
		return DivisionLine.getHeight(settings) + list.get(0).getHeightToEnd() + list.get(1).getHeightToEnd();
	}

	@Override
	public int getWidth() {
		return Math.max(list.get(0).getWidthToEnd(), list.get(1).getWidthToEnd());
	}

	@Override
	public int getHeightToEnd() {
		if (list.size() > 2) {
			return Math.max(getHeight(), list.get(2).getHeight());
		} else {
			return getHeight();
		}
	}

	@Override
	public int getCenterDelta(int center) {
		if (list.size() > 2)
			return list.get(2).getCenterDelta(center);
		return center;
	}

	@Override
	public int[] getTopBottom(int[] size) {
		size[1] = Math.min(size[1], size[0] - DivisionLine.getHeight(settings) / 2 - list.get(0).getHeightToEnd());
		size[2] = Math.max(size[2], size[0] + DivisionLine.getHeight(settings) / 2 + list.get(1).getHeightToEnd());
		if (list.size() > 2) {
			size = list.get(2).getTopBottom(size);
		}
		return size;
	}

	@Override
	public int getWidthToEnd() {
		if (list.size() > 2) {
			return getWidth() + list.get(2).getWidthToEnd();
		}
		return getWidth();
	}

	@Override
	public void drawNext(Canvas canvas, int deltaX, int startY) {
		if (list.size() > 2) {
			list.get(2).draw(canvas, deltaX + getWidth(), startY);
		}
		list.get(0).draw(
				canvas,
				deltaX + (getWidth() - list.get(0).getWidthToEnd()) / 2,
				startY - list.get(0).getCenterDelta(0) - DivisionLine.getHeight(settings) / 2 - list.get(0).getHeightToEnd() / 2);
		list.get(1).draw(
				canvas,
				deltaX + (getWidth() - list.get(1).getWidthToEnd()) / 2,
				startY - list.get(1).getCenterDelta(0) + DivisionLine.getHeight(settings) / 2 + list.get(1).getHeightToEnd() / 2);
	}

	@Override
	public void drawText(Canvas canvas, int deltaX, int startY) {
	}

	@Override
	public void drawTerritory(Canvas canvas, int deltaX, int startY) {
		DivisionLine.drawLine(canvas, deltaX, startY, deltaX + getWidth(), this);
	}

	public static class DivisionLine {

		public static Paint paint;

		public static int getHeight(Settings settings) {
			return settings.getValue(settings.getValues().getDivision());
		}

		public static void drawLine(Canvas canvas, int startX, int centerY, int endX, PaintableBlock paintable) {
			if (paint == null) {
				paint = new Paint();
				paint.setColor(paintable.settings.getColors().getDivisionLine());
				paint.setStyle(Paint.Style.FILL);
			}
			paintable.rect = new Rect(
					                         startX,
					                         (int) (centerY - getHeight(paintable.settings) * paintable.settings.getValues().getDivisionPaddingFactor()),
					                         endX,
					                         (int) (centerY + getHeight(paintable.settings) * paintable.settings.getValues().getDivisionPaddingFactor()));
			canvas.drawRect(paintable.rect, paint);
		}
	}
}