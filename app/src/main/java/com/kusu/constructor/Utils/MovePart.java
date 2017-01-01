package com.kusu.constructor.Utils;

import com.kusu.constructor.LeafType.Movable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by KuSu on 31.12.2016.
 */

public class MovePart {
    HashMap<Integer, Movable> blocks;

    public MovePart(HashMap<Integer, Movable> blocks) {
        this.blocks = blocks;
    }

    public Movable get(int id) {
        return blocks.get(id);
    }

    public Set<Map.Entry<Integer, Movable>> entrySet() {
        return blocks.entrySet();
    }
}
