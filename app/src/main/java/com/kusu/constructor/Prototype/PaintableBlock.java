package com.kusu.constructor.Prototype;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.kusu.constructor.Settings.Colors;
import com.kusu.constructor.LeafType.Movable;
import com.kusu.constructor.Settings.Scale;
import com.kusu.constructor.View.Settings;

/**
 * Created by KuSu on 09.11.2016.
 */

public abstract class PaintableBlock implements Drawable {
    public final static int SYMBOL_CHANGABLE = 0;
    public final static int SYMBOL_NEXTABLE = 1;
    public final static int SYMBOL_DIVISION = 2;
    public final static int SYMBOL_POWER = 3;
    public final static int SYMBOL_MOVABLE = 4;

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

    protected Paint getPaint(String symbols, Movable block) {
        //todo позволить перееопределить метод, а точнее написать метод, который будет говорить, валидное ли значение, если changable
        if (paint == null)
            paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        if (block == null) {
            paint.setColor(Color.LTGRAY);
            return paint;
        }
        if (symbols.equals(block.symbols)) {
            paint.setColor(Color.GREEN);
        } else {
            paint.setColor(Color.RED);
        }
        return paint;
    }

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
        paint.setColor(settings.getColorText(getType()));
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(settings.getTextSize());
        return paint;
    }

    public Paint getPaint() {
        if (paint == null)
            paint = new Paint();
        paint.setColor(settings.getColor(getType()));
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

}
