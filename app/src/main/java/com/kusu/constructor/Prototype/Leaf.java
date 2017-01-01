package com.kusu.constructor.Prototype;

import com.kusu.constructor.View.Colors;
import com.kusu.constructor.LeafType.Movable;
import com.kusu.constructor.LeafType.Changeable;
import com.kusu.constructor.LeafType.Division;
import com.kusu.constructor.LeafType.Nextable;
import com.kusu.constructor.LeafType.Power;
import com.kusu.constructor.View.Scale;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KuSu on 31.12.2016.
 */

public abstract class Leaf extends PaintableBlock {
    public Movable block = null;
    public List<Leaf> list = new ArrayList<>();

    public static Leaf create(String symbols){
        switch (symbols){
            case "/":
                return new Division(symbols);
            case "^":
                return new Power(symbols);
            default:
            case "+":
            case "-":
            case "*":
                return new Nextable(symbols);
        }
    }

    public static Leaf create(String symbols, boolean changable){
        if (changable)
            return new Changeable(symbols);
        else
            return create(symbols);
    }

    public Movable isClickInBlock(int targetX, int targetY) {
        Movable temp = null;
        for (Leaf leaf : list) {
            temp = leaf.isClickInBlock(targetX, targetY);
            if (temp != null)
                return temp;
        }
        return null;
    }

    public boolean inLeaf(Movable movable, int targetX, int targetY) {
        for (Leaf leaf : list) {
            if (leaf.inLeaf(movable, targetX, targetY))
                return true;
        }
        return false;
    }

    public abstract int getCenterDelta(int center);
    public abstract int[] getTopBottom(int[] size);

    public void setTreeScale(Scale scale) {
        setScale(scale);
        for(Leaf leaf : list)
            if (leaf != null)
                leaf.setTreeScale(scale);
    }

    public void setTreeColors(Colors colors) {
        setColors(colors);
        for(Leaf leaf : list)
            if (leaf != null)
                leaf.setTreeColors(colors);
    }
}
