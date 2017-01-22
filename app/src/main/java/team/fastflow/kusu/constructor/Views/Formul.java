package team.fastflow.kusu.constructor.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import team.fastflow.kusu.R;
import team.fastflow.kusu.constructor.LeafType.Movable;
import team.fastflow.kusu.constructor.Prototype.Leaf;
import team.fastflow.kusu.constructor.Utils.Result;
import team.fastflow.kusu.constructor.Moduls.DrawThread;
import team.fastflow.kusu.constructor.Moduls.Listeners;
import team.fastflow.kusu.constructor.Moduls.MovePart;
import team.fastflow.kusu.constructor.Moduls.Settings;
import team.fastflow.kusu.constructor.Moduls.TouchWorker;
import team.fastflow.kusu.constructor.Moduls.Tree;

/**
 * Created by KuSu on 08.11.2016.
 */

public class Formul extends View {

    private DrawThread drawThread = new DrawThread(this);
    private MovePart part = new MovePart(this);
    private Tree tree = new Tree(this);
    private TouchWorker worker = new TouchWorker(this);
    private Settings settings = new Settings(null, this.getContext());
    private Listeners listeners = new Listeners(this);

    private void init(Context context, AttributeSet attrs) throws Exception {
        if (attrs != null) {
            TypedArray pianoAttrs = context.obtainStyledAttributes(attrs, R.styleable.fs);
            settings = new Settings(pianoAttrs, context);
        }
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
        invalidate();
        return this;
    }

    public Formul setStringsBlocks(Iterable<String> blocks) {
        ArrayList<Movable> list = new ArrayList<>();
        int i = 0;
        for (String str : blocks) {
            list.add(new Movable(i, str));
            i++;
        }
        return setBlocks(list);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawThread.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (worker.onTouchEvent(event)) {
            invalidate();
            return true;
        }
        return false;
    }

    public Leaf getRoot() {
        return tree.getRoot();
    }

    public Formul setRoot(Leaf root) {
        tree.setRoot(root);
        tree.updateRootReferences();
        invalidate();
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

    public Listeners getListeners() {
        return listeners;
    }

    public Result getResult(boolean backlight, boolean movable, boolean clear, boolean check) {
        getWorker().setMove(movable);
        Result result = getTree().getResult();
        getTree().updateBacklight(backlight);
        getMovePart().clearBlocks(clear);
        getTree().clearBlocks(clear);
        if (check) {
            drawThread.setCheck(result.getGood_count() == result.getCount() ? DrawThread.CHECK_GOOD : DrawThread.CHECK_BAD);
        } else
            drawThread.setCheck(DrawThread.CHECK_NON);
        invalidate();
        return result;
    }

    public void clearBlocks() {
        getMovePart().clearBlocks(true);
        getTree().clearBlocks(true);
        invalidate();
    }

    public void setMove(boolean movable) {
        getWorker().setMove(movable);
        invalidate();
    }

    public void setBacklight(boolean backlight) {
        getTree().updateBacklight(backlight);
        invalidate();
    }

    public void clearCheck() {
        drawThread.setCheck(DrawThread.CHECK_NON);
        invalidate();
    }

    public void setCheck(int check) {
        drawThread.setCheck(check);
        invalidate();
    }
}
