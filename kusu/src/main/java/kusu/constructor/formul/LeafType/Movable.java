package kusu.constructor.formul.LeafType;

import android.graphics.Canvas;
import android.graphics.Rect;

import kusu.constructor.formul.Prototype.Leaf;
import kusu.constructor.formul.Prototype.PaintableBlock;


/**
 * Created by KuSu on 09.11.2016.
 */

public class Movable extends Leaf {

    public static final int VISIBLE = 1;
    public static final int MOVABLE = 2;
    public static final int INVISIBLE = 3;

    public int id;
    int visibility = VISIBLE;
    private float factor = 1;

    public Movable(int id, String symbols) {
        this.id = id;
        this.symbols = symbols;
    }

    public void move() {
        visibility = MOVABLE;
    }

    @Override
    public int getHeight() {
        return getMovableHeight(isMovable());
    }

    @Override
    public int getHeightToEnd() {
        return getHeight();
    }

    @Override
    public int getWidth() {
        return getMovableWidth(isMovable());
    }

    @Override
    public int getWidthToEnd() {
        return getWidth();
    }

    @Override
    protected void drawTerritory(Canvas canvas, int deltaX, int startY) {
        rect = new Rect(deltaX, startY, deltaX + getWidth(), startY + getHeight());
        drawDrawableInCanvas(canvas, getDrawable(getType()), rect);
    }

    @Override
    protected void drawText(Canvas canvas, int deltaX, int startY) {
        drawCenterText(canvas, deltaX + getWidth() / 2, startY + getHeight() / 2, symbols);
    }

    @Override
    protected void drawNext(Canvas canvas, int deltaX, int startY) {
    }

    @Override
    protected float getTextSize() {
        if (isMovable())
            return settings.getMovableTextSize() * factor;
        return settings.getTextSize() * factor;
    }

    @Override
    protected int getType() {
        return PaintableBlock.SYMBOL_MOVABLE;
    }

    @Override
    public int getCenterDelta(int center) {
        return getHeight() / 2;
    }

    @Override
    public int[] getTopBottom(int[] size) {
        size[1] = Math.min(size[1], size[0] - getHeight() / 2);
        size[2] = Math.max(size[2], size[0] + getHeight() / 2);
        if (list.size() > 0) {
            size = list.get(0).getTopBottom(size);
        }
        return size;
    }

    public void invis() {
        visibility = INVISIBLE;
    }

    public void back() {
        visibility = VISIBLE;
    }

    public boolean isVisible() {
        return visibility == VISIBLE;
    }

    public boolean isMovable() {
        return visibility == MOVABLE;
    }

    public int getMovableHeight(boolean move) {
        if (move)
            return (int) (settings.getMovableValue(settings.getValue(settings.getValues().getBlock())) * factor);
        return (int) (settings.getValue(settings.getValues().getBlock()) * factor);
    }

    public int getMovableWidth(boolean move) {
        int width = settings.getValue(settings.getValues().getwBlock());
        if (settings.getScale().isAutoScaleWidth()) {
            int temp = (int) (settings.getValue(settings.getValues().getBlock()) * (1 - settings.getTextPercent())
                    + getTextWidth(settings.getValues().getBigString(), move ? settings.getMovableTextSize() : settings.getTextSize()));
            width = Math.max(temp, width);
        }
        if (move)
            return (int) (settings.getMovableValue(width)*factor);
        return (int) (width * factor);
    }

    public void setFactor(float factor) {
        this.factor = factor;
    }
}
