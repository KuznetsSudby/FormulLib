package com.kusu.constructor.Settings;

import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.kusu.constructor.Formul;
import com.kusu.constructor.R;

/**
 * Created by KuSu on 31.12.2016.
 */

public class Scale {

    private static float defMaxScale = 10.0f;
    private static float defMinScale = 0.1f;
    private static boolean defAuto = true;

    private float maxScale = defMaxScale;
    private float minScale = defMinScale;
    private float scale = 1.0f;
    private boolean auto = defAuto;

    public Scale(TypedArray attrs) {
        if (attrs == null)
            return;
        maxScale = attrs.getFloat(R.styleable.fs_max_scale, defMaxScale);
        minScale = attrs.getFloat(R.styleable.fs_min_scale, defMinScale);
        auto = attrs.getBoolean(R.styleable.fs_autoscale, defAuto);
    }

    public void changeScale(int wView, int hView, int wBlock, int hBlock, int padding) {
        wView -= padding * 2;
        hView -= padding * 2;
        float newDelta = Math.min(wView / wBlock, hView / hBlock);
        scale *= newDelta;
        if (scale > maxScale)
            scale = maxScale;
        if (scale < minScale)
            scale = minScale;
    }

    public int getScaledValue(int value) {
        return Math.round(scale * value);
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public float getMinScale() {
        return minScale;
    }

    public void setMinScale(float minScale) {
        this.minScale = minScale;
    }

    public float getMaxScale() {
        return maxScale;
    }

    public void setMaxScale(float maxScale) {
        this.maxScale = maxScale;
    }
}
