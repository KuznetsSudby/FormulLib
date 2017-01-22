package team.fastflow.kusu.constructor.Moduls;

import team.fastflow.kusu.constructor.Views.Formul;
import team.fastflow.kusu.constructor.LeafType.Movable;
import team.fastflow.kusu.constructor.Listeners.EndMoveBlockListener;
import team.fastflow.kusu.constructor.Listeners.OccupancyListener;
import team.fastflow.kusu.constructor.Utils.Result;

/**
 * Created by KuSu on 07.01.2017.
 */

public class Listeners {
    private Formul formul;
    private EndMoveBlockListener endMoveBlockListener;
    private OccupancyListener occupancyListener;

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

    public void endMoveBlock(Movable movable) {
        if (endMoveBlockListener != null)
            endMoveBlockListener.endMove(movable);
        changeOccupancy();
    }

    public void startMoveBlock(Movable movable) {
        if (endMoveBlockListener != null)
            endMoveBlockListener.startMove(movable);
    }

    public void changeOccupancy() {
        if (occupancyListener != null) {
            Result result = formul.getTree().getResult();
            occupancyListener.changeOccupancy(result.getCount(), result.getCount() - result.getUnselected_count());
            occupancyListener.fullOccupancy(result.getUnselected_count() == 0);
        }
    }
}
