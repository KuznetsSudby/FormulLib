package com.kusu.constructor.View;

import android.graphics.Canvas;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.kusu.constructor.Formul;
import com.kusu.constructor.LeafType.Movable;

import java.util.Map;

/**
 * Created by KuSu on 31.12.2016.
 */

public class TouchWorker extends GestureDetector.SimpleOnGestureListener{
    int targetX, targetY;
    Movable movable;
    Formul formul;

    public TouchWorker(Formul formul) {
        this.formul = formul;
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        targetX = (int) event.getX();
        targetY = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Movable block = formul.getRoot().isClickInBlock(targetX, targetY);
                if (block != null) {
                    formul.getMovePart().get(block.id).move();
                    movable = block;
                } else {
                    for (Movable bl : formul.getMovePart().getBlocks())
                        if (bl.isInRect(targetX, targetY)) {
                            movable = bl;
                            movable.move();
                            return true;
                        }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (movable != null) {
                    if (formul.getRoot().inLeaf(movable, targetX, targetY)) {
                        movable.invis();
                    } else {
                        movable.back();
                    }
                    movable = null;
                }
                break;
        }
        return true;
    }

    public void draw(Canvas canvas) {
        if (movable != null)
            movable.draw(canvas, targetX - movable.getHeight()/2, targetY - movable.getHeight()/2);
    }

    public void clear() {
        movable = null;
    }
}
