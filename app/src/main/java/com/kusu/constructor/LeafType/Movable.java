package com.kusu.constructor.LeafType;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.kusu.constructor.Prototype.Leaf;
import com.kusu.constructor.Prototype.PaintableBlock;

/**
 * Created by KuSu on 09.11.2016.
 */

public class Movable extends Leaf {

    public static final int VISIBLE = 1;
    public static final int MOVABLE = 2;
    public static final int INVISIBLE = 3;

    public int id;
    int visibility = VISIBLE;

    public Movable(int id, String symbols) {
        this.id = id;
        this.symbols = symbols;
    }

    public void move() {
        visibility = MOVABLE;
    }

    @Override
    public int getHeight() {
        return settings.getValue(settings.getValues().getBlock());
    }

    @Override
    public int getHeightToEnd() {
        return getHeight();
    }

    @Override
    public int getWidth() {
        return settings.getValue(settings.getValues().getwBlock());
    }

    @Override
    public int getWidthToEnd() {
        return getWidth();
    }

    @Override
    protected void drawTerritory(Canvas canvas, int deltaX, int startY) {
        deltaX -= getHeight() / 2;
        startY -= getWidth() / 2;
        rect = new Rect(deltaX, startY, deltaX + getWidth(), startY + getHeight());
        canvas.drawRect(rect, getPaint());
    }

    @Override
    protected void drawText(Canvas canvas, int deltaX, int startY) {
        drawCenterText(canvas, deltaX + getWidth()/2, startY, symbols);
    }

    @Override
    protected void drawNext(Canvas canvas, int deltaX, int startY) {
    }

    @Override
    protected int getType() {
        return PaintableBlock.SYMBOL_MOVABLE;
    }

    @Override
    public int getCenterDelta(int center) {
        return getHeight() / 2;
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

    public void invis() {
        visibility = INVISIBLE;
    }

    public void back() {
        visibility = VISIBLE;
    }

    public boolean isVisible() {
        return visibility == VISIBLE;
    }


}
