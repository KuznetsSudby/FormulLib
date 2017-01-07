package com.kusu.constructor.Listeners;

import com.kusu.constructor.LeafType.Movable;

/**
 * Created by KuSu on 07.01.2017.
 */

public interface EndMoveBlockListener {
    public void endMove(Movable movable);
    public void startMove(Movable movable);
}
