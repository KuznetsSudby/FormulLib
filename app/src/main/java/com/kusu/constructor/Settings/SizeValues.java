package com.kusu.constructor.Settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.kusu.constructor.R;
import com.kusu.constructor.Utils.Constants;
import com.kusu.constructor.Formul;

/**
 * Created by KuSu on 01.01.2017.
 */

public class SizeValues {
    private static boolean defPercentHeight = true;
    private static float defPercent = 50;
    private static int defHeight = 600;
    private static int defPadding = Constants.block;
    private static int defTextSize = 40;

    private boolean percentHeight = defPercentHeight;
    private float percent = defPercent;
    private int height = defHeight;
    private int padding = defPadding;
    private int textSize = defTextSize;

    public SizeValues(TypedArray attrs) {
        if (attrs == null)
            return;
        percent = attrs.getFloat(R.styleable.fs_percent, defPercent);
        height = attrs.getDimensionPixelSize(R.styleable.fs_height, defHeight);
        padding = attrs.getDimensionPixelSize(R.styleable.fs_padding, defPadding);
        percentHeight = attrs.getBoolean(R.styleable.fs_percent_height, defPercentHeight);
        textSize = attrs.getDimensionPixelSize(R.styleable.fs_text_size, defTextSize);
    }

    public int getFormulHeight(int h) {
        if (percentHeight) {
            return (int) (h * percent / 100);
        }
        else
            return (int) Math.min(h, height);
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public boolean isPercentHeight() {
        return percentHeight;
    }

    public void setPercentHeight(boolean percentHeight) {
        this.percentHeight = percentHeight;
    }
}
