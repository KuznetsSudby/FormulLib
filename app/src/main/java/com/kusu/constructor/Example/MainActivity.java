package com.kusu.constructor.Example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kusu.constructor.LeafType.Movable;
import com.kusu.constructor.Prototype.Leaf;
import com.kusu.constructor.R;
import com.kusu.constructor.Formul;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Formul formul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formul = (Formul) findViewById(R.id.formul);
        formul
                .setBlocks(getDefBlock())
                .setRoot(getDefRoot());
    }

    public static HashMap<Integer, Movable> getDefBlock() {
        HashMap<Integer, Movable> blocks = new HashMap<>();
        blocks.put(0, new Movable(0, "H"));
        blocks.put(1, new Movable(1, "A"));
        blocks.put(2, new Movable(2, "B"));
        blocks.put(3, new Movable(3, "C"));
        blocks.put(4, new Movable(4, "D"));
        blocks.put(5, new Movable(5, "E"));
        blocks.put(6, new Movable(6, "F"));
        blocks.put(7, new Movable(7, "J"));
        blocks.put(8, new Movable(8, "G"));
        blocks.put(9, new Movable(9, "U"));
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
        leaf.list.add(Leaf.create("+"));

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

