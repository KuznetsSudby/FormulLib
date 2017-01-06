package com.kusu.constructor.Utils;

import android.content.Context;

import com.kusu.constructor.R;
import com.kusu.constructor.Settings.Scale;
import com.kusu.constructor.Settings.SizeValues;

/**
 * Created by KuSu on 04.01.2017.
 */

public class ExceptionWorker {
    private static void validate(float value, float left, float right, String name, Context context) throws Exception {
        if ((value < left) || (value > right))
            throw new Exception(context.getString(R.string.exception, name, String.valueOf(left), String.valueOf(right), String.valueOf(value)));
    }

    private static void validate(int value, int left, int right, String name, Context context) throws Exception {
        if ((value < left) || (value > right))
            throw new Exception(context.getString(R.string.exception, name, String.valueOf(left), String.valueOf(right), String.valueOf(value)));
    }

    public static void validate(Context context, SizeValues values) throws Exception {
        validate(values.getTextPercent(), 0.01f, 0.99f, "TextPercent", context);
        validate(values.getBlockFactor(), 0.2f, 5.0f, "BlockFactor", context);
        validate(values.getDivisionFactor(), 0.01f, 0.99f, "DivisionFactor", context);
        validate(values.getPercent(), 0.1f, 0.9f, "Percent", context);
        validate(values.getDivisionPaddingFactor(), 0.01f, 0.5f, "DivisionPaddingFactor", context);
        validate(values.getBlock(), 10, 1000, "Block", context);
        validate(values.getHeight(), 1, Integer.MAX_VALUE, "Block", context);
        validate(values.getPadding(), 0, Integer.MAX_VALUE, "Padding", context);
    }


    public static void validate(Context context, Scale scale) throws Exception {
        validate(scale.getMinScale(), 0.1f, 200f, "MinScale", context);
        validate(scale.getMaxScale(), 0.1f, 200f, "MaxScale", context);
        validate(scale.getMovableExtraScale(), 0.1f, 20f, "MovableExtraScale", context);
        validate(scale.getMinScale(), scale.getMaxScale(), "MinScale", "MaxScale", context);
    }

    private static void validate(float minScale, float maxScale, String minScale1, String maxScale1, Context context) throws Exception {
        if (minScale > maxScale)
            throw new Exception(context.getString(R.string.exception_min_max, maxScale1, minScale1, String.valueOf(maxScale), String.valueOf(minScale)));
    }
}
