package com.kusu.constructor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.kusu.constructor.LeafType.Changeable;
import com.kusu.constructor.LeafType.Movable;
import com.kusu.constructor.Utils.MovePart;
import com.kusu.constructor.Prototype.Leaf;
import com.kusu.constructor.Utils.TouchWorker;
import com.kusu.constructor.View.DrawThread;

import java.util.HashMap;

/**
 * Created by KuSu on 08.11.2016.
 */

public class Formul extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread drawThread;
    Leaf root = new Changeable("^");
    MovePart part = new MovePart(new HashMap<Integer, Movable>());
    TouchWorker worker = new TouchWorker(this);

    public Formul(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public Formul(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    public Formul setBlocks(HashMap<Integer, Movable> blocks) {
        part = new MovePart(blocks);
        return this;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder(), this);
        drawThread.setRunning(true);
        drawThread.start();
        updateRootReferences();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return worker.onTouchEvent(event);
    }

    public Leaf getRoot() {
        return root;
    }

    public Formul setRoot(Leaf root) {
        this.root = root;
        updateRootReferences();
        return this;
    }

    public MovePart getMovePart() {
        return part;
    }

    public TouchWorker getWorker() {
        return worker;
    }

    public void updateRootReferences() {
        if (drawThread != null) {
            root.setTreeScale(drawThread.getScale());
            root.setTreeColors(drawThread.getColors());
        }
    }
}
