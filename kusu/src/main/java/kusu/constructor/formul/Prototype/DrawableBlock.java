package kusu.constructor.formul.Prototype;

import android.graphics.Canvas;

/**
 * Created by KuSu on 08.11.2016.
 */

public interface DrawableBlock {
    int getHeight();
    int getWidth();
    int getHeightToEnd();
    int getWidthToEnd();
    void draw(Canvas canvas, int deltaX, int startY);
}
