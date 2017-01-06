package com.kusu.constructor.Example;

import com.kusu.constructor.LeafType.Movable;
import com.kusu.constructor.Prototype.Leaf;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KuSu on 02.01.2017.
 */

public class Default {
    //todo написать парсеры;
    public static ArrayList<Movable> getDefBlock() {
        ArrayList<Movable> blocks = new ArrayList<>();
        blocks.add(new Movable(0, "H"));
        blocks.add(new Movable(1, "A"));
        blocks.add(new Movable(2, "B"));
        blocks.add(new Movable(3, "C"));
        blocks.add(new Movable(4, "D"));
        blocks.add(new Movable(5, "*"));
        blocks.add(new Movable(6, "F"));
        blocks.add(new Movable(7, "J"));
        blocks.add(new Movable(8, "G"));
        blocks.add(new Movable(9, "U"));
        return blocks;
    }

    public static Leaf getDefRoot() {
        Leaf root = Leaf.create("H", true);
        Leaf leaf = Leaf.create("*");
        root.list.add(leaf);
        leaf.list.add(Leaf.create("/"));
        leaf = leaf.list.get(0);
        leaf.list.add(Leaf.create("A", true));
        leaf.list.add(Leaf.create("B", true));
        leaf.list.add(Leaf.create("-"));

        leaf.list.get(2).list.add(Leaf.create("C", true));
        Leaf leaf2 = leaf.list.get(2).list.get(0);
        leaf2.list.add(Leaf.create("^"));
        leaf2 = leaf2.list.get(0);
        leaf2.list.add(Leaf.create("F"));

        leaf2 = leaf.list.get(1);
        leaf2.list.add(Leaf.create("^"));
        leaf2 = leaf2.list.get(0);
        leaf2.list.add(Leaf.create("J"));

        leaf = leaf.list.get(0);
        leaf.list.add(Leaf.create("^"));
        leaf = leaf.list.get(0);
        leaf.list.add(Leaf.create("2"));
        Leaf leaf1 = leaf.list.get(0);
        leaf1.list.add(Leaf.create("/"));
        leaf1 = leaf1.list.get(0);
        leaf1.list.add(Leaf.create("G", true));
        leaf1.list.add(Leaf.create("U"));
        leaf.list.add(Leaf.create("+"));

        leaf = leaf.list.get(1);
        leaf.list.add(Leaf.create("/"));
        leaf = leaf.list.get(0);
        leaf.list.add(Leaf.create("D", true));
        leaf.list.add(Leaf.create("E", true));
        return root;
    }
}
