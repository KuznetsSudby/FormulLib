package com.kusu.constructor.View;

import android.content.Context;
import android.content.res.TypedArray;

import com.kusu.constructor.Formul;
import com.kusu.constructor.Prototype.PaintableBlock;
import com.kusu.constructor.Settings.Colors;
import com.kusu.constructor.Settings.Drawables;
import com.kusu.constructor.Settings.Scale;
import com.kusu.constructor.Settings.SizeValues;
import com.kusu.constructor.Utils.ExceptionWorker;

/**
 * Created by KuSu on 02.01.2017.
 */

public class Settings {
	private SizeValues values;
	private Colors colors;
	private Scale scale;
	private Drawables drawables;

	public Settings(TypedArray attrs, Context context) throws Exception {
		values = new SizeValues(attrs);
		scale = new Scale(attrs);

		colors = new Colors(context)
				         .initAttr(attrs);

		drawables = new Drawables(attrs)
				            .initDrawables(context);

		if (attrs != null) {
			attrs.recycle();
			ExceptionWorker.validate(context, scale);
			ExceptionWorker.validate(context, values);
		}
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

	public int getMovableValue(int wBlock) {
		return scale.getMovableScaledValue(wBlock);
	}

	public float getTextPercent() {
		return values.getTextPercent();
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

	public float getMovableTextSize() {
		return getTextPercent() * scale.getMovableScaledValue(getValue(values.getBlock()));
	}

	public Drawables getDrawables() {
		return drawables;
	}

	public void setDrawables(Drawables drawables) {
		this.drawables = drawables;
	}

	public void changeWidth(Formul formul) {
		if (scale.isAutoScaleWidth()){
			values.setBigString(formul.getMovePart().getBiggestString());
		}
	}
}
