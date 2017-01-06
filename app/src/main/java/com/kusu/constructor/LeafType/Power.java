package com.kusu.constructor.LeafType;

import android.graphics.Canvas;

import com.kusu.constructor.Prototype.Leaf;
import com.kusu.constructor.Prototype.PaintableBlock;

public class Power extends Leaf {

    public Power(String symbols) {
        this.symbols = symbols;
    }

    @Override
    public int getType() {
        return PaintableBlock.SYMBOL_DIVISION;
    }

    @Override
    public int getHeight() {
        return getPowerStep() + list.get(0).getHeightToEnd();
    }

    @Override
    public int getWidth() {
        return list.get(0).getWidthToEnd();
    }

    @Override
    public int getHeightToEnd() {
        if (list.size() > 1) {
            return list.get(0).getHeightToEnd() + list.get(1).getHeightToEnd() / 2;
        } else {
            return getHeight();
        }
    }

    @Override
    public int getCenterDelta(int center) {
        if (list.size() > 1) {
            return center - getHeightToEnd() / 2 + list.get(1).getHeightToEnd() / 2;
        } else {
            return center - getHeightToEnd() / 2 + getPowerStep();
        }
    }

    @Override
    public int[] getTopBottom(int[] size) {
        size[1] = Math.min(size[1], size[0] + getPowerStep() - getHeight());
        size[2] = Math.max(size[2], size[0] + getPowerStep());
        if (list.size() > 0) {
            size = list.get(0).getTopBottom(size);
        }
        return size;
    }

    @Override
    protected float getTextSize() {
        return settings.getTextSize();
    }

    @Override
    public int getWidthToEnd() {
        if (list.size() > 1)
            return getWidth() + list.get(1).getWidthToEnd();
        else
            return getWidth();
    }

    @Override
    public void drawNext(Canvas canvas, int deltaX, int startY) {
        list.get(0).draw(canvas, deltaX, startY - list.get(0).getHeightToEnd() / 2);
        if (list.size() > 1)
            list.get(1).draw(canvas, deltaX + list.get(0).getWidthToEnd(), startY);
    }

    @Override
    public void drawText(Canvas canvas, int deltaX, int startY) {
    }

    @Override
    public void drawTerritory(Canvas canvas, int deltaX, int startY) {
        //     canvas.drawRect(deltaX, startY - getHeightToEnd() / 2, deltaX + list.get(0).getWidthToEnd(), startY + getHeightToEnd() / 2, getPaint());
    }

    public int getPowerStep() {
        return settings.getValue(settings.getValues().getBlock() / 2);
    }
}