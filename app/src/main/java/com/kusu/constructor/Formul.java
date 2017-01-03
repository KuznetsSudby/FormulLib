package com.kusu.constructor;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.kusu.constructor.LeafType.Movable;
import com.kusu.constructor.View.MovePart;
import com.kusu.constructor.Prototype.Leaf;
import com.kusu.constructor.View.TouchWorker;
import com.kusu.constructor.View.DrawThread;
import com.kusu.constructor.View.Settings;

import java.util.HashMap;

/**
 * Created by KuSu on 08.11.2016.
 */

public class Formul extends View {

    private DrawThread drawThread;
    private MovePart part = new MovePart(new HashMap<Integer, Movable>());
    private TouchWorker worker = new TouchWorker(this);
    private Settings settings;

    private void init(Context context, AttributeSet attrs) throws Exception {
        if (attrs == null)
            settings = new Settings(null, context);
        else {
            TypedArray pianoAttrs = context.obtainStyledAttributes(attrs, R.styleable.fs);
            settings = new Settings(pianoAttrs, context);
        }
        drawThread = new DrawThread(this);
        drawThread.updateRootReferences();
    }

    public Formul(Context context) throws Exception {
        super(context);
        init(context, null);
    }

    public Formul(Context context, AttributeSet attrs) throws Exception {
        super(context, attrs);
        init(context, attrs);
    }

    public Formul setBlocks(HashMap<Integer, Movable> blocks) {
        part = new MovePart(blocks);
        return this;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawThread.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return worker.onTouchEvent(event);
    }

    public Leaf getRoot() {
        return drawThread.getRoot();
    }

    public Formul setRoot(Leaf root) {
        drawThread.setRoot(root);
        drawThread.updateRootReferences();
        return this;
    }

    public MovePart getMovePart() {
        return part;
    }

    public TouchWorker getWorker() {
        return worker;
    }

    public Settings getSettings() {
        return settings;
    }
}
