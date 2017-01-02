package com.kusu.constructor.View;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.kusu.constructor.Formul;
import com.kusu.constructor.LeafType.Changeable;
import com.kusu.constructor.LeafType.Movable;
import com.kusu.constructor.Prototype.Leaf;
import com.kusu.constructor.Settings.Colors;
import com.kusu.constructor.Settings.Scale;
import com.kusu.constructor.Settings.SizeValues;
import com.kusu.constructor.Utils.Constants;

import java.util.Map;

/**
 * Created by KuSu on 08.11.2016.
 */

public class DrawThread {

    private final Formul formul;
    private Leaf root = new Changeable("^");

    public DrawThread(Formul formul) {
        this.formul = formul;
    }

    public void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawFormul(canvas);
        //      drawBlocks(canvas);
        drawMovable(canvas);
    }

    private void drawFormul(Canvas canvas) {
        if (root == null)
            return;
        int height = formul.getSettings().getFormulHeight(canvas.getHeight());

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(
                formul.getSettings().getPadding(),
                formul.getSettings().getPadding(),
                canvas.getWidth() - formul.getSettings().getPadding(),
                height - formul.getSettings().getPadding(), paint);

        formul.getSettings().changeScale(
                canvas.getWidth(),
                height, root.getWidthToEnd(),
                root.getHeightToEnd());

        int[] s = root.getTopBottom(new int[]{
                height / 2, height / 2, height / 2
        });
        int dH = (height - s[1] - s[2]) / 2;
        int dW = canvas.getWidth() - formul.getSettings().getPadding() * 2 - root.getWidthToEnd();

        if (root != null)
            root.draw(canvas, formul.getSettings().getPadding() + dW / 2, height / 2 + dH);
    }

    protected void drawBackground(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(formul.getSettings().getBackground());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, formul.getWidth(), formul.getHeight(), paint);
    }

    private void drawBlocks(Canvas canvas) {
        int i = 0;
        for (Map.Entry<Integer, Movable> entry : formul.getMovePart().entrySet()) {
            if (entry.getValue().isVisible()) {
                entry.getValue().draw(canvas, 100 + (Constants.wBlock + Constants.block) * (i % 5), 100 + Constants.block * 2 * (i / 5));
            }
            i++;
        }
    }

    private void drawMovable(Canvas canvas) {
        formul.getWorker().draw(canvas);
    }

    public Leaf getRoot() {
        return root;
    }

    public void setRoot(Leaf root) {
        this.root = root;
    }

    public void updateRootReferences() {
        root.setTreeSettings(formul.getSettings());
    }
}
