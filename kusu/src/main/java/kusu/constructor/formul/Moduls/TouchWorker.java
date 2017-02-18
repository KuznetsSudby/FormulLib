package kusu.constructor.formul.Moduls;

import android.graphics.Canvas;
import android.view.GestureDetector;
import android.view.MotionEvent;

import kusu.constructor.formul.LeafType.Movable;
import kusu.constructor.formul.Views.Formul;

/**
 * Created by KuSu on 31.12.2016.
 */

public class TouchWorker extends GestureDetector.SimpleOnGestureListener{
    int targetX, targetY;
    Movable movable;
    Formul formul;

    boolean move = true;

    public TouchWorker(Formul formul) {
        this.formul = formul;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!isMove())
            return true;
        int action = event.getAction();
        targetX = (int) event.getX();
        targetY = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Movable block = formul.getRoot().isClickInBlock(targetX, targetY);
                if (block != null) {
                    formul.getMovePart().get(block.id).move();
                    movable = block;
                    formul.getListeners().startMoveBlock(movable);
                } else {
                    for (Movable bl : formul.getMovePart().getBlocks())
                        if (bl.isInRect(targetX, targetY)) {
                            if (bl.isVisible()) {
                                movable = bl;
                                movable.move();
                                formul.getListeners().startMoveBlock(movable);
                                return true;
                            }
                        }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (movable != null) {
                    if (formul.getRoot().inLeaf(movable, targetX, targetY)) {
                        movable.invis();
                        formul.getListeners().endMoveBlock(movable);
                    } else {
                        movable.back();
                        formul.getListeners().endMoveBlock(movable);
                    }
                    movable = null;
                }
                break;
        }
        return true;
    }

    public void draw(Canvas canvas) {
        if (movable != null)
            movable.draw(canvas, targetX - movable.getWidth()/2, targetY - movable.getHeight()/2);
    }

    public void clear() {
        if (movable != null)
            movable.back();
        movable = null;
        formul.getListeners().changeOccupancy();
    }

    public void setMove(boolean move){
        this.move = move;
        if (!move){
            clear();
        }
    }

    public boolean isMove(){
        return move;
    }
}
