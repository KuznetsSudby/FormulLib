package com.kusu.constructor.View;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.kusu.constructor.Example.Default;
import com.kusu.constructor.Formul;
import com.kusu.constructor.Prototype.Leaf;
import com.kusu.constructor.Utils.Result;
import com.kusu.constructor.Utils.Utils;

/**
 * Created by mikhail.chvarkou on 1/5/2017.
 */

public class Tree {
    private Formul formul;
    private Leaf root = Default.getDefRoot();

    public Tree(Formul formul) {
        this.formul = formul;
    }

    public void draw(Canvas canvas) {
        if (root == null)
            return;
        int height = formul.getSettings().getFormulHeight(canvas.getHeight());

        formul.getSettings().changeWidth(formul);
        if (formul.getSettings().getScale().isAutoScale()) {
            float temp = formul.getSettings().getScale().getScale();
            formul.getSettings().getScale().setScale(Utils.roundMore(formul.getSettings().getScale().getScale()));
            formul.getSettings().changeScale(
                    canvas.getWidth(),
                    height, root.getWidthToEnd(),
                    root.getHeightToEnd());
            while (temp != formul.getSettings().getScale().getScale()) {
                temp = formul.getSettings().getScale().getScale();
                formul.getSettings().getScale().setScale(Utils.roundMore(formul.getSettings().getScale().getScale()));
                formul.getSettings().changeScale(
                        canvas.getWidth(),
                        height, root.getWidthToEnd(),
                        root.getHeightToEnd());
            }
        }

        int[] s = root.getTopBottom(new int[]{
                height / 2, height / 2, height / 2
        });
        int dH = (height - s[1] - s[2]) / 2;
        int dW = canvas.getWidth() - formul.getSettings().getPadding() * 2 - root.getWidthToEnd();

        if (root != null)
            root.draw(canvas, formul.getSettings().getPadding() + dW / 2, height / 2 + dH);
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

    public Result getResult() {
        Result result = new Result();
        root.updateResult(result);
        return result;
    }

    public void clearBlocks(boolean clear) {
        if (clear) {
            root.clear();
            formul.getListeners().changeOccupancy();
        }
    }

    public void updateBacklight(boolean backlight) {
        root.setBacklight(backlight);
    }
}
