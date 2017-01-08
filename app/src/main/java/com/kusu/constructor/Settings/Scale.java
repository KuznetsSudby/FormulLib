package com.kusu.constructor.Settings;

import android.content.res.TypedArray;

import com.kusu.constructor.R;

/**
 * Created by KuSu on 31.12.2016.
 */

public class Scale {
    private static float defMaxScale = 10.0f;
    private static float defMinScale = 0.1f;
    private static float defMovableExtraScale = 1.5f;
    private static boolean defAutoScale = true;
    private static boolean defAutoScaleWidth = true;

    private float maxScale = defMaxScale;
    private float minScale = defMinScale;
    private float movableExtraScale = defMovableExtraScale;
    private float scale = -0.1f;
    private boolean autoScale = defAutoScale;
    private boolean autoScaleWidth = defAutoScaleWidth;

    public Scale(TypedArray attrs) {
        if (attrs == null)
            return;
        maxScale = attrs.getFloat(R.styleable.fs_max_scale, defMaxScale);
        minScale = attrs.getFloat(R.styleable.fs_min_scale, defMinScale);
        movableExtraScale = attrs.getFloat(R.styleable.fs_movable_extrascale, defMovableExtraScale);
        autoScale = attrs.getBoolean(R.styleable.fs_autoscale, defAutoScale);
        autoScaleWidth = attrs.getBoolean(R.styleable.fs_autoscale_width, defAutoScaleWidth);
    }

    public void changeScale(int wView, int hView, int wBlock, int hBlock, int padding) {
        wView -= padding * 2;
        hView -= padding * 2;
        float newDelta = Math.min(wView * 1.0f / wBlock, hView * 1.0f / hBlock);
        scale *= newDelta;
        if (scale > maxScale)
            scale = maxScale;
        if (scale < minScale)
            scale = minScale;
    }

    public int getScaledValue(int value) {
        return Math.round(scale * value);
    }

    public int getMovableScaledValue(int value) {
        return Math.round(movableExtraScale * value);
    }

    public boolean isAutoScale() {
        return autoScale;
    }

    public void setAutoScale(boolean autoScale) {
        this.autoScale = autoScale;
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

    public float getMovableExtraScale() {
        return movableExtraScale;
    }

    public void setMovableExtraScale(float movableExtraScale) {
        this.movableExtraScale = movableExtraScale;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean isAutoScaleWidth() {
        return autoScaleWidth;
    }

    public void setAutoScaleWidth(boolean autoScaleWidth) {
        this.autoScaleWidth = autoScaleWidth;
    }
}
