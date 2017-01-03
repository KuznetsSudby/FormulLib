package com.kusu.constructor.Settings;

import android.content.res.TypedArray;

import com.kusu.constructor.R;

/**
 * Created by KuSu on 01.01.2017.
 */

public class SizeValues {
    private static int defBlock = 10;
    private static float defBlockFactor = 1.5f;
    private static float defDivisionFactor = 0.2f;
    private static float defDivisionPaddingFactor = 3 / 8.0f;

    private static boolean defPercentHeight = true;
    private static float defPercent = 0.5f;
    private static int defHeight = 600;
    private static float defTextPercent = 0.6f;

    private int block = defBlock;
    private float percent = defPercent;
    private int height = defHeight;
    private int padding = block;
    private boolean percentHeight = defPercentHeight;
    private float textPercent = defTextPercent;
    private int wBlock = (int) (block * defBlockFactor);
    private int division = (int) (block * defDivisionFactor);
    private float divisionPaddingFactor = defDivisionPaddingFactor;

    public SizeValues(TypedArray attrs) {
        if (attrs == null)
            return;
        block = attrs.getDimensionPixelSize(R.styleable.fs_block_size, defBlock);
        percent = attrs.getFloat(R.styleable.fs_percent, defPercent);
        height = attrs.getDimensionPixelSize(R.styleable.fs_height, defHeight);
        padding = attrs.getDimensionPixelSize(R.styleable.fs_padding, block);
        percentHeight = attrs.getBoolean(R.styleable.fs_percent_height, defPercentHeight);
        textPercent = attrs.getFloat(R.styleable.fs_text_percent, defTextPercent);
        wBlock = (int) (block * attrs.getFloat(R.styleable.fs_block_factor, defBlockFactor));
        division = (int) (block * attrs.getFloat(R.styleable.fs_division_factor, defDivisionFactor));
        divisionPaddingFactor = attrs.getFloat(R.styleable.fs_division_padding_factor, defDivisionPaddingFactor);
    }

    public int getFormulHeight(int h) {
        if (percentHeight) {
            return (int) (h * percent);
        } else
            return (int) Math.min(h, height);
    }

    public float getDivisionPaddingFactor() {
        return divisionPaddingFactor;
    }

    public void setDivisionPaddingFactor(float divisionPaddingFactor) {
        this.divisionPaddingFactor = divisionPaddingFactor;
    }

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }

    public int getwBlock() {
        return wBlock;
    }

    public void setwBlock(int wBlock) {
        this.wBlock = wBlock;
    }

    public boolean isPercentHeight() {
        return percentHeight;
    }

    public void setPercentHeight(boolean percentHeight) {
        this.percentHeight = percentHeight;
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

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public float getTextPercent() {
        return textPercent;
    }

    public void setTextPercent(float textPercent) {
        this.textPercent = textPercent;
    }
}
