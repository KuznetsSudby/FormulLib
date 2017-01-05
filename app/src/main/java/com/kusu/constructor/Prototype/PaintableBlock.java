package com.kusu.constructor.Prototype;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.kusu.constructor.View.Settings;

/**
 * Created by KuSu on 09.11.2016.
 */

public abstract class PaintableBlock implements DrawableBlock {
    public final static int SYMBOL_CHANGABLE = 0;
    public final static int SYMBOL_NEXTABLE = 1;
    public final static int SYMBOL_DIVISION = 2;
    public final static int SYMBOL_POWER = 3;
    public final static int SYMBOL_MOVABLE = 4;
    public final static int SYMBOL_MOVABLE_GOOD = 5;
    public final static int SYMBOL_MOVABLE_BAD = 6;
    public final static int SYMBOL_MINUS = 7;
    public final static int SYMBOL_PLUS = 8;
    public final static int SYMBOL_MULTIPLY = 9;

    public String symbols;
    public Rect rect;
    public static Paint paint;

    public Settings settings;

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public void draw(Canvas canvas, int deltaX, int startY) {
        drawTerritory(canvas, deltaX, startY);
        drawText(canvas, deltaX, startY);
        drawNext(canvas, deltaX, startY);
    }

    protected abstract void drawTerritory(Canvas canvas, int deltaX, int startY);

    protected abstract void drawText(Canvas canvas, int deltaX, int startY);

    protected abstract void drawNext(Canvas canvas, int deltaX, int startY);

    protected abstract int getType();

    public boolean isInRect(int targetX, int targetY) {
        if (rect == null)
            return false;
        if ((rect.left < targetX) && (rect.right > targetX))
            if ((rect.top < targetY) && (rect.bottom > targetY))
                return true;
        return false;
    }

    public Paint getPaintText() {
        if (paint == null)
            paint = new Paint();
        paint.setColor(settings.getColors().getText());
        paint.setStyle(Paint.Style.FILL);
        paint.setFakeBoldText(true);
        paint.setTextSize(settings.getTextSize());
        return paint;
    }

    public void drawDrawableInCanvas(Canvas canvas, Drawable drawable, Rect rect){
        drawable.setBounds(rect);
        drawable.draw(canvas);
    }

    public Drawable getDrawable(int type){
        switch (type){
            default:
                return settings.getDrawables().getDefaultBlock();
            case SYMBOL_CHANGABLE:
                return settings.getDrawables().getChangeableBlock();
            case SYMBOL_MOVABLE_BAD:
                return settings.getDrawables().getBadBlock();
            case SYMBOL_MOVABLE_GOOD:
                return settings.getDrawables().getGoodBlock();
            case SYMBOL_MOVABLE:
                return settings.getDrawables().getMovableBlock();
            case SYMBOL_MULTIPLY:
                return settings.getDrawables().getMultiplyBlock();
            case SYMBOL_PLUS:
                return settings.getDrawables().getPlusBlock();
            case SYMBOL_MINUS:
                return settings.getDrawables().getMinusBlock();
        }
    }
}
