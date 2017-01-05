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
import com.kusu.constructor.View.Tree;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KuSu on 08.11.2016.
 */

public class Formul extends View {

    private DrawThread drawThread;
    private MovePart part;
    private Tree tree;
    private TouchWorker worker = new TouchWorker(this);
    private Settings settings;

    private void init(Context context, AttributeSet attrs) throws Exception {
        if (attrs == null)
            settings = new Settings(null, context);
        else {
            TypedArray pianoAttrs = context.obtainStyledAttributes(attrs, R.styleable.fs);
            settings = new Settings(pianoAttrs, context);
        }
        part = new MovePart(this);
        tree = new Tree(this);
        drawThread = new DrawThread(this);
        tree.updateRootReferences();
        part.updateBlockReferences(settings);
    }

    public Formul(Context context) throws Exception {
        super(context);
        init(context, null);
    }

    public Formul(Context context, AttributeSet attrs) throws Exception {
        super(context, attrs);
        init(context, attrs);
    }

    public Formul setBlocks(ArrayList<Movable> blocks) {
        part = new MovePart(this);
        worker.clear();
        part.update(blocks);
        part.updateBlockReferences(settings);
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
        return tree.getRoot();
    }

    public Formul setRoot(Leaf root) {
        tree.setRoot(root);
        tree.updateRootReferences();
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

    public Tree getTree() {
        return tree;
    }
}
