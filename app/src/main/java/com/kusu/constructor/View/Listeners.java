package com.kusu.constructor.View;

import com.kusu.constructor.Formul;
import com.kusu.constructor.Listeners.EndMoveBlockListener;
import com.kusu.constructor.Listeners.OccupancyListener;

/**
 * Created by KuSu on 07.01.2017.
 */

public class Listeners {
    private Formul formul;
    private EndMoveBlockListener endMoveBlockListener;
    private OccupancyListener occupancyListener;

    //todo работа с оповещением

    public Listeners(Formul formul) {
        this.formul = formul;
    }

    public EndMoveBlockListener getEndMoveBlockListener() {
        return endMoveBlockListener;
    }

    public void setEndMoveBlockListener(EndMoveBlockListener endMoveBlockListener) {
        this.endMoveBlockListener = endMoveBlockListener;
    }

    public OccupancyListener getOccupancyListener() {
        return occupancyListener;
    }

    public void setOccupancyListener(OccupancyListener occupancyListener) {
        this.occupancyListener = occupancyListener;
    }
}
