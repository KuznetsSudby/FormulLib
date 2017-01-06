package com.kusu.constructor.View;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.kusu.constructor.Example.Default;
import com.kusu.constructor.Formul;
import com.kusu.constructor.LeafType.Movable;
import com.kusu.constructor.Utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by KuSu on 31.12.2016.
 */

public class MovePart {
    Formul formul;
    ArrayList<Movable> blocks = Default.getDefBlock();

    public MovePart(Formul formul) {
        this.formul = formul;
    }

    public void update(ArrayList<Movable> blocks) {
        this.blocks = blocks;
    }

    public Movable get(int id) {
        return blocks.get(id);
    }

    public int getSize() {
        return blocks.size();
    }

    public void updateBlockReferences(Settings settings) {
        for (Movable movable : blocks)
            movable.setSettings(settings);
    }

    public ArrayList<Movable> getBlocks() {
        return blocks;
    }

    public void draw(Canvas canvas) {
        if (formul.getMovePart().getSize() == 0)
            return;

        int height = formul.getSettings().getFormulHeight(canvas.getHeight());
        int hField = canvas.getHeight() - height - 2 * formul.getSettings().getPadding();
        int wField = canvas.getWidth() - 2 * formul.getSettings().getPadding();

        Movable mov = blocks.get(0);
        int lineCount = getLineCount(
                hField,
                wField,
                1,
                mov.getNotMovableHeight(),
                mov.getNotMovableWidth());

        float factor = getResultFactor(
                hField,
                wField,
                formul.getSettings().getValue(mov.getNotMovableHeight()),
                formul.getSettings().getValue(mov.getNotMovableWidth()),
                lineCount);

        if (factor > 1)
            factor = 1;

        int countInLine = Utils.roundMore(getSize() * 1.0f / lineCount);
        int startIndex = 0;
        int endIndex;
        int lineNum = 0;
        while (startIndex <= getSize()) {
            endIndex = startIndex + countInLine;
            if (endIndex >= getSize())
                endIndex = getSize();
            drawLineBlocks(
                    canvas,
                    hField,
                    wField,
                    startIndex,
                    endIndex,
                    factor,
                    lineNum,
                    lineCount,
                    height + formul.getSettings().getPadding(),
                    formul.getSettings().getPadding());
            startIndex += countInLine;
            lineNum++;
        }
    }

    private void drawLineBlocks(Canvas canvas, int hField, int wField, int startIndex, int endIndex, float factor, int lineNum, int lineCount, int deltaH, int deltaW) {
        int divider = formul.getSettings().getValues().getMovableDivider();
        if (formul.getSettings().getValues().isGroupMovables()) {
            deltaH += (hField - blocks.get(0).getNotMovableHeight() * lineCount - divider * (lineCount - 1)) / 2 + lineNum * (blocks.get(0).getNotMovableHeight() + divider);
            deltaW += (wField - blocks.get(0).getNotMovableWidth() * (endIndex - startIndex) - divider * (endIndex - startIndex - 1)) / 2;
        } else {
            if (lineCount > 1)
                deltaH += lineNum * blocks.get(0).getNotMovableHeight() + (hField - lineCount * blocks.get(0).getNotMovableHeight()) / (lineCount - 1) * lineNum;
            else
                deltaH += (hField - blocks.get(0).getNotMovableHeight()) / 2;
            if (endIndex - startIndex > 1)
                divider = (wField - blocks.get(0).getNotMovableWidth() * (endIndex - startIndex)) / (endIndex - startIndex - 1);
            else {
                deltaW += (wField - blocks.get(0).getNotMovableWidth()) / 2;
            }
        }
        for (int i = startIndex; i < endIndex; i++) {
            if (blocks.get(i).isVisible()) {
                blocks.get(i).draw(canvas, deltaW + (i - startIndex) * (blocks.get(0).getNotMovableWidth() + divider), deltaH);
            }
        }
    }


    private int getLineCount(int hField, int wField, int line, int hBlock, int wBlock) {
        if ((howMuch(hField, hBlock) >= line)
                && (howMuch(wField, wBlock) >= getSize() * 1.0 / line))
            return line;
        else {
            if (line > 1) {
                float currFactor = getResultFactor(hField, hBlock, wField, wBlock, line);
                float moreFactor = getResultFactor(hField, hBlock, wField, wBlock, line + 1);
                float lessFactor = getResultFactor(hField, hBlock, wField, wBlock, line - 1);
                if (lessFactor > Math.max(currFactor, moreFactor))
                    return getLineCount(hField, wField, line - 1, hBlock, wBlock);
                if (moreFactor > Math.max(currFactor, lessFactor))
                    return getLineCount(hField, wField, line + 1, hBlock, wBlock);
                return line;
            } else {
                float currFactor = getResultFactor(hField, hBlock, wField, wBlock, line);
                float moreFactor = getResultFactor(hField, hBlock, wField, wBlock, line + 1);
                if (moreFactor > currFactor)
                    return getLineCount(hField, wField, line + 1, hBlock, wBlock);
                return line;
            }
        }
    }

    private float getResultFactor(int hField, int hBlock, int wField, int wBlock, int line) {
        return Math.min(getFactor(hField, hBlock, line), getFactor(wField, wBlock, Utils.roundMore(getSize() * 1.0f / line)));
    }

    private float getFactor(int field, int block, int count) {
        float size = block * count + formul.getSettings().getValues().getMovableDivider() * (count - 1);
        return field / size;
    }

    private int howMuch(int field, int block) {
        field += formul.getSettings().getValues().getMovableDivider();
        return field / (formul.getSettings().getValues().getMovableDivider() + block);
    }
}
