package com.kusu.constructor.LeafType;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.kusu.constructor.Utils.Constants;
import com.kusu.constructor.Prototype.Leaf;
import com.kusu.constructor.Prototype.PaintableBlock;

public class Changeable extends Leaf {

    public Changeable(String symbols) {
        this.symbols = symbols;
    }

    @Override
    public int getType() {
        return PaintableBlock.SYMBOL_CHANGABLE;
    }

    @Override
    public int getHeight() {
        return settings.getValue(Constants.block);
    }

    @Override
    public int getWidth() {
        return settings.getValue(Constants.wBlock);
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
    public int[] getTopBottom(int[] size) {
        size[1] = Math.min(size[1], size[0] - getHeight()/2);
        size[2] = Math.max(size[2], size[0] + getHeight()/2);
        if (list.size()>0) {
            size = list.get(0).getTopBottom(size);
        }
        return size;
    }

    @Override
    public int getWidthToEnd() {
        if (list.size() == 0)
            return getWidth();
        else
            return getWidth() + list.get(0).getWidthToEnd();
    }

    @Override
    public  void drawNext(Canvas canvas, int deltaX, int startY) {
        if (list.size() > 0)
            list.get(0).draw(canvas, deltaX + getWidth(), startY);
    }

    @Override
    public  void drawText(Canvas canvas, int deltaX, int startY) {
        if (block != null)
            canvas.drawText(block.symbols, deltaX + Constants.deltaTextX, startY + getHeight() / 2 - Constants.deltaTextY, getPaintText());
    }

    @Override
    public  void drawTerritory(Canvas canvas, int deltaX, int startY) {
        rect = new Rect(deltaX, startY - getHeight() / 2, deltaX + getWidth(), startY + getHeight() / 2);
        canvas.drawRect(rect, getPaint());
    }

    @Override
    public Movable isClickInBlock(int targetX, int targetY) {
        if (isInRect(targetX, targetY)) {
            Movable b = block;
            block = null;
            return b;
        }
        return super.isClickInBlock(targetX, targetY);
    }

    @Override
    public boolean inLeaf(Movable movable, int targetX, int targetY) {
        if (isInRect(targetX, targetY)) {
            if (block != null)
                block.back();
            block = movable;
            return true;
        }
        return super.inLeaf(movable, targetX, targetY);
    }
}