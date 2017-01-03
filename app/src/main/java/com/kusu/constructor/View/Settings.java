package com.kusu.constructor.View;

import android.content.res.TypedArray;

import com.kusu.constructor.Settings.Colors;
import com.kusu.constructor.Settings.Scale;
import com.kusu.constructor.Settings.SizeValues;

/**
 * Created by KuSu on 02.01.2017.
 */

public class Settings {
    private SizeValues values;
    private Colors colors;
    private Scale scale;

    public Settings(TypedArray attrs) {
        values = new SizeValues(attrs);
        colors = new Colors(attrs);
        scale = new Scale(attrs);
        if (attrs != null)
            attrs.recycle();
    }

    public int getFormulHeight(int height) {
        return values.getFormulHeight(height);
    }

    public int getPadding() {
        return values.getPadding();
    }

    public void changeScale(int width, int height, int widthToEnd, int heightToEnd) {
        scale.changeScale(width, height, widthToEnd, heightToEnd, values.getPadding());
    }

    public int getBackground() {
        return colors.getBackground();
    }

    public int getValue(int wBlock) {
        return scale.getScaledValue(wBlock);
    }

    public int getColor(int type) {
        return colors.getColor(type);
    }

    public float getTextPercent() {
        return values.getTextPercent();
    }

    public int getColorText(int type) {
        return colors.getColorText(type);
    }

    public SizeValues getValues() {
        return values;
    }

    public Colors getColors() {
        return colors;
    }

    public Scale getScale() {
        return scale;
    }

    public float getTextSize() {
        return getTextPercent() * scale.getScaledValue(values.getBlock());
    }
}
