package com.kusu.constructor.View;

import com.kusu.constructor.Formul;

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

    public Scale(Formul formul) {

    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
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

    public int getValue(int value) {
        return Math.round(scale * value);
    }


}
