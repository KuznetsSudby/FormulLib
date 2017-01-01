package com.kusu.constructor.View;

import com.kusu.constructor.Utils.Constants;
import com.kusu.constructor.Formul;

/**
 * Created by KuSu on 01.01.2017.
 */

public class SizeValues {
    private static boolean defPercentHeight = true;
    private static float defPercent = 50;
    private static float defHeight = 600;
    private static int defPadding = Constants.block;

    private boolean percentHeight = defPercentHeight;
    private float percent = defPercent;
    private float height = defHeight;
    private int padding = defPadding;

    public SizeValues(Formul formul) {

    }

    public int getFormulHeight(int h) {
        if (percentHeight) {
            return (int) (h * percent / 100);
        }
        else
            return (int) Math.min(h, height);
    }

    public int getPadding() {
        return padding;
    }
}
