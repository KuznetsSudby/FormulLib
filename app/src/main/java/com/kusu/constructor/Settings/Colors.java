package com.kusu.constructor.Settings;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import com.kusu.constructor.Formul;
import com.kusu.constructor.R;

import static com.kusu.constructor.Prototype.PaintableBlock.SYMBOL_CHANGABLE;
import static com.kusu.constructor.Prototype.PaintableBlock.SYMBOL_DIVISION;
import static com.kusu.constructor.Prototype.PaintableBlock.SYMBOL_MOVABLE;
import static com.kusu.constructor.Prototype.PaintableBlock.SYMBOL_NEXTABLE;
import static com.kusu.constructor.Prototype.PaintableBlock.SYMBOL_POWER;

/**
 * Created by KuSu on 31.12.2016.
 */

public class Colors {

    private static int defBackground = Color.WHITE;
    private int background = defBackground;

    public Colors(TypedArray attrs) {
        if (attrs == null)
            return;
        background = attrs.getColor(R.styleable.fs_background_color, defBackground);
    }

    public int getColor(int type) {
        switch (type) {
            default:
            case SYMBOL_CHANGABLE:
            case SYMBOL_MOVABLE:
                return Color.LTGRAY;
            case SYMBOL_POWER:
                return Color.GREEN;
            case SYMBOL_DIVISION:
                return Color.BLACK;
            case SYMBOL_NEXTABLE:
                return Color.WHITE;
        }
    }

    public int getColorText(int type) {
        switch (type) {
            default:
            case SYMBOL_CHANGABLE:
                return Color.BLACK;
            case SYMBOL_POWER:
                return Color.BLACK;
        }
    }

    public int getBackground() {
        return background;
    }
}
