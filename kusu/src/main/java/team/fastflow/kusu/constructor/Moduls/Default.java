package team.fastflow.kusu.constructor.Moduls;

import android.content.Context;

import java.util.ArrayList;

import team.fastflow.kusu.constructor.LeafType.Movable;
import team.fastflow.kusu.constructor.Parser.Parser;
import team.fastflow.kusu.constructor.Prototype.Leaf;

/**
 * Created by KuSu on 02.01.2017.
 */

public class Default {

    public static ArrayList<Movable> getDefBlock() {
        ArrayList<Movable> blocks = new ArrayList<>();
        blocks.add(new Movable(0, "A"));
        blocks.add(new Movable(1, "B"));
        blocks.add(new Movable(2, "C"));
        blocks.add(new Movable(3, "D"));
        blocks.add(new Movable(4, "E"));
        blocks.add(new Movable(5, "F"));
        blocks.add(new Movable(6, "/"));
        blocks.add(new Movable(7, "+"));
        blocks.add(new Movable(8, "*"));
        blocks.add(new Movable(9, "-"));
        return blocks;
    }

    public static ArrayList<Movable> getDefWordBlock() {
        ArrayList<Movable> blocks = new ArrayList<>();
        blocks.add(new Movable(0, "A"));
        blocks.add(new Movable(1, "O"));
        blocks.add(new Movable(2, "H"));
        blocks.add(new Movable(3, "НН"));
        blocks.add(new Movable(4, "ННН"));
        blocks.add(new Movable(5, "Е"));
        blocks.add(new Movable(6, "У"));
        return blocks;
    }

    public static Leaf getDefTree(Context context) throws Exception {
        Parser parser = new Parser();
        return  parser.parseRoot(context, Parser.def);
    }

    public static Leaf getDefTreeMin(Context context) throws Exception {
        Parser parser = new Parser();
        return  parser.parseRoot(context, Parser.defMin);
    }

    public static Leaf getDefWordTree(Context context) throws Exception {
        Parser parser = new Parser();
        return  parser.parseRoot(context, Parser.defWord);
    }
}
