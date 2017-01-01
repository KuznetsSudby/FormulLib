package com.kusu.constructor.View;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.kusu.constructor.Formul;
import com.kusu.constructor.LeafType.Movable;
import com.kusu.constructor.Utils.Constants;

import java.util.Map;

/**
 * Created by KuSu on 08.11.2016.
 */

public class DrawThread extends Thread {

    private boolean running = false;
    private SurfaceHolder surfaceHolder;
    private Formul formul;
    private SizeValues values;
    private Colors colors;
    private Scale scale;


    public DrawThread(SurfaceHolder surfaceHolder, Formul formul) {
        this.surfaceHolder = surfaceHolder;
        this.formul = formul;
        values = new SizeValues(formul);
        colors = new Colors(formul);
        scale = new Scale(formul);
        formul.updateRootReferences();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        Canvas canvas;
        while (running) {
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas(null);
                if (canvas == null)
                    continue;
                drawM(canvas);

            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    protected void drawM(Canvas canvas) {
        drawBackground(canvas);
        drawFormul(canvas);
        //      drawBlocks(canvas);
        drawMovable(canvas);
    }

    private void drawFormul(Canvas canvas) {
        if (formul.getRoot() == null)
            return;
        int height = values.getFormulHeight(canvas.getHeight());

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(values.getPadding(), values.getPadding(), canvas.getWidth() - values.getPadding(), height - values.getPadding(), paint);

        int[] s = formul.getRoot().getTopBottom(new int[]{
                height / 2, height / 2, height / 2
        });
        int dH = (height - s[1] - s[2]) / 2;

        scale.changeScale(canvas.getWidth(), height, formul.getRoot().getWidthToEnd(), formul.getRoot().getHeightToEnd(), values.getPadding());
        int dW = canvas.getWidth() - values.getPadding() * 2 - formul.getRoot().getWidthToEnd();
        if (formul.getRoot() != null)
            formul.getRoot().draw(canvas, values.getPadding() + dW / 2, height / 2 +dH);
    }

    public void drawLine(Canvas canvas, int y, int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, y - 1, canvas.getWidth(), y + 1, paint);
    }

    protected void drawBackground(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(colors.getBackground());
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

    public Scale getScale() {
        return scale;
    }

    public Colors getColors() {
        return colors;
    }
}
