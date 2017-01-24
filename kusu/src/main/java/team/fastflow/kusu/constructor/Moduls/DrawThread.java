package team.fastflow.kusu.constructor.Moduls;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import team.fastflow.kusu.constructor.Views.Formul;

/**
 * Created by KuSu on 08.11.2016.
 */

public class DrawThread {

    public final static int CHECK_NON = 0;
    public final static int CHECK_GOOD = 1;
    public final static int CHECK_BAD = 2;

    private int check = CHECK_NON;
    private final Formul formul;

    public DrawThread(Formul formul) {
        this.formul = formul;
    }

    public void onDraw(Canvas canvas) {
        drawBackground(canvas);
//		debugDraw(canvas);
        formul.getTree().draw(canvas);
        formul.getMovePart().draw(canvas);
        formul.getWorker().draw(canvas);
        drawCheck(canvas);
    }

    private void drawCheck(Canvas canvas) {
        if (check != CHECK_NON) {
            int height = formul.getSettings().getFormulHeight(canvas.getHeight());
            int width = canvas.getWidth();
            int value = (int) (Math.min(
                    height - formul.getSettings().getPadding() * 2,
                    width - formul.getSettings().getPadding() * 2
            ) * formul.getSettings().getValues().getCheckSize());
            int top = (height - value) / 2;
            int left = (width - value) / 2;
            Drawable drawable;
            switch (check) {
                default:
                case CHECK_BAD:
                    drawable = formul.getSettings().getDrawables().getCheckBad();
                    break;
                case CHECK_GOOD:
                    drawable = formul.getSettings().getDrawables().getCheckGood();
                    break;
            }
            drawable.setBounds(left, top, left + value, top + value);
            drawable.draw(canvas);
        }
    }

    private void debugDraw(Canvas canvas) {
        int height = formul.getSettings().getFormulHeight(canvas.getHeight());
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(
                formul.getSettings().getPadding(),
                height + formul.getSettings().getPadding(),
                canvas.getWidth() - formul.getSettings().getPadding(),
                canvas.getHeight() - formul.getSettings().getPadding(), paint);

        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(
                formul.getSettings().getPadding(),
                formul.getSettings().getPadding(),
                canvas.getWidth() - formul.getSettings().getPadding(),
                height - formul.getSettings().getPadding(), paint);

    }


    protected void drawBackground(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(formul.getSettings().getBackground());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, formul.getWidth(), formul.getHeight(), paint);
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}
