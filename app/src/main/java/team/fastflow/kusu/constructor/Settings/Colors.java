package team.fastflow.kusu.constructor.Settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

import team.fastflow.kusu.R;

/**
 * Created by KuSu on 31.12.2016.
 */

public class Colors {

    private static @ColorInt int defBackground;
    private static @ColorInt int defText;
    private static @ColorInt int defDivision;

    private static @ColorRes int defIDBackground = R.color.background;
    private static @ColorRes int defIDText = R.color.textColor;
    private static @ColorRes int defIDDivision = R.color.divisionLine;

    private @ColorInt int background;
    private @ColorInt int text;
    private @ColorInt int divisionLine;

    public Colors(Context context) {
        initColors(context);
    }

    public Colors initAttr(TypedArray attrs) {
        if (attrs == null)
            return this;
        background = attrs.getColor(R.styleable.fs_background_color, defBackground);
        text = attrs.getColor(R.styleable.fs_text_color, defText);
        divisionLine = attrs.getColor(R.styleable.fs_division_color, defDivision);
        return this;
    }

    public Colors initColors(Context context){
        defBackground = context.getResources().getColor(defIDBackground);
        defText = context.getResources().getColor(defIDText);
        defDivision = context.getResources().getColor(defIDDivision);

        background = defBackground;
        text = defText;
        divisionLine = defDivision;

        return this;
    }

    public @ColorInt int getDivisionLine() {
        return divisionLine;
    }

    public void setDivisionLine(@ColorInt int divisionLine) {
        this.divisionLine = divisionLine;
    }

    public @ColorInt int getText() {
        return text;
    }

    public void setText(@ColorInt int text) {
        this.text = text;
    }

    public @ColorInt int getBackground() {
        return background;
    }

    public void setBackground(@ColorInt int background) {
        this.background = background;
    }
}
