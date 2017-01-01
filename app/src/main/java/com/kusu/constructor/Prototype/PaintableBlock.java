package com.kusu.constructor.Prototype;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.kusu.constructor.View.Colors;
import com.kusu.constructor.LeafType.Movable;
import com.kusu.constructor.View.Scale;

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

    public Colors colors;
    public Scale scale;

    public void setScale(Scale scale) {
        this.scale = scale;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
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

    public Paint getPaint(String symbols, Movable block) {
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
        paint.setColor(Colors.getColorText(getType()));
        paint.setStyle(Paint.Style.FILL);

        //// TODO: 31.12.2016 text size
        paint.setTextSize(40);
        return paint;
    }

    public Paint getPaint() {
        if (paint == null)
            paint = new Paint();
        paint.setColor(Colors.getColor(getType()));
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

}
