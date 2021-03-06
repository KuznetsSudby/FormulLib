package kusu.constructor.formul.Prototype;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import kusu.constructor.formul.LeafType.Changeable;
import kusu.constructor.formul.LeafType.Division;
import kusu.constructor.formul.LeafType.Movable;
import kusu.constructor.formul.LeafType.Nextable;
import kusu.constructor.formul.LeafType.Power;
import kusu.constructor.formul.Moduls.Settings;
import kusu.constructor.formul.Utils.Result;
import kusu.constructor.formul.Utils.Utils;

/**
 * Created by KuSu on 31.12.2016.
 */

public abstract class Leaf extends PaintableBlock {
    public Movable block = null;
    public List<Leaf> list = new ArrayList<>();
    public boolean backlight = false;

    public static Leaf create(String symbols){
        switch (symbols){
            case "/":
                return new Division(symbols);
            case "^":
                return new Power(symbols);
            default:
            case "+":
            case "-":
            case "*":
                return new Nextable(symbols);
        }
    }

    public static Leaf create(String symbols, boolean changable){
        if (changable)
            return new Changeable(symbols);
        else
            return create(symbols);
    }

    public Movable isClickInBlock(int targetX, int targetY) {
        Movable temp = null;
        for (Leaf leaf : list) {
            temp = leaf.isClickInBlock(targetX, targetY);
            if (temp != null)
                return temp;
        }
        return null;
    }

    public boolean inLeaf(Movable movable, int targetX, int targetY) {
        for (Leaf leaf : list) {
            if (leaf.inLeaf(movable, targetX, targetY))
                return true;
        }
        return false;
    }

    public abstract int getCenterDelta(int center);
    public abstract int[] getTopBottom(int[] size);

    public void setTreeSettings(Settings settings) {
        setSettings(settings);
        for(Leaf leaf : list)
            if (leaf != null)
                leaf.setTreeSettings(settings);
    }

    public void drawCenterText(Canvas canvas, int centerX, int centerY, String text){
        if (isOperation(text)){
            int txtSize = (int) getTextSize() / 2;
            drawDrawableInCanvas(canvas, getDrawable(Utils.convertOperationSymbols(text)), new Rect(centerX - txtSize, centerY - txtSize, centerX + txtSize, centerY + txtSize));
        }else {
            Rect mTextBoundRect = new Rect();
            Paint paint = getPaintText(getTextSize());
            paint.getTextBounds(text, 0, text.length(), mTextBoundRect);
            float mTextWidth = paint.measureText(text);
            float mTextHeight = mTextBoundRect.height();

            canvas.drawText(text,
                    centerX - (mTextWidth / 2f),
                    centerY + (mTextHeight / 2f),
                    paint
            );
        }
    }

    protected float getTextWidth(String text, float size){
        Rect mTextBoundRect = new Rect();
        Paint paint = getPaintText(size);
        paint.getTextBounds(text, 0, text.length(), mTextBoundRect);
        return paint.measureText(text);
    }

    protected float getTextSize() {
        return settings.getTextSize();
    }

    protected boolean isOperation(String text) {
        if (text.equals("*"))
            return true;
        if (text.equals("+"))
            return true;
        if (text.equals("-"))
            return true;
        if (text.equals("="))
            return true;
        if (text.equals("/"))
            return true;
        if (text.equals("("))
            return true;
        if (text.equals(")"))
            return true;
        return false;
    }

    public void clear() {
        block = null;
        for(Leaf leaf : list)
            if (leaf != null)
                leaf.clear();
    }

    public void setBacklight(boolean backlight) {
        this.backlight = backlight;
        for(Leaf leaf : list)
            if (leaf != null)
                leaf.setBacklight(backlight);
    }

    public void updateResult(Result result) {
        for(Leaf leaf : list)
            if (leaf != null)
                leaf.updateResult(result);
    }
}
