package com.kusu.constructor.LeafType;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.kusu.constructor.Prototype.Leaf;
import com.kusu.constructor.Prototype.PaintableBlock;

public class Nextable extends Leaf {

    public Nextable(String symbols) {
        this.symbols = symbols;
    }

    @Override
    public int getType() {
        return PaintableBlock.SYMBOL_NEXTABLE;
    }

    @Override
    public int getWidth() {
      //  return settings.getValue(settings.getValues().getwBlock());
        return settings.getValue(settings.getValues().getBlock());
    }

    @Override
    public int getHeight() {
        return settings.getValue(settings.getValues().getBlock());
    }

    @Override
    public int getHeightToEnd() {
        if (list.size() > 0)
            return Math.max(getHeight(), list.get(0).getHeightToEnd());
        return getHeight();
    }

    @Override
    public int getCenterDelta(int center) {
        if (list.size() > 0)
            return list.get(0).getCenterDelta(center);
        return center;
    }

    @Override
    public int getWidthToEnd() {
        if (list.size() == 0)
            return getWidth();
        else
            return getWidth() + list.get(0).getWidthToEnd();
    }

    @Override
    public void drawNext(Canvas canvas, int deltaX, int startY) {
        if (list.size() > 0)
            list.get(0).draw(canvas, deltaX + getWidth(), startY);
    }

    @Override
    public int[] getTopBottom(int[] size) {
        size[1] = Math.min(size[1], size[0] - getHeight() / 2);
        size[2] = Math.max(size[2], size[0] + getHeight() / 2);
        if (list.size() > 0) {
            size = list.get(0).getTopBottom(size);
        }
        return size;
    }

    @Override
    public void drawText(Canvas canvas, int deltaX, int startY) {
        drawCenterText(canvas, deltaX + getWidth()/2, startY, symbols);
    }

    @Override
    public void drawTerritory(Canvas canvas, int deltaX, int startY) {
        rect = new Rect(deltaX, startY - getHeight() / 2, deltaX + getWidth(), startY + getHeight() / 2);
        drawDrawableInCanvas(canvas, getDrawable(getType()), rect);
    }
}
