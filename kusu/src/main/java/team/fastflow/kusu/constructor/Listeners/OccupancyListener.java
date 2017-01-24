package team.fastflow.kusu.constructor.Listeners;

/**
 * Created by KuSu on 07.01.2017.
 */

public interface OccupancyListener {
    public void fullOccupancy(boolean full);
    public void changeOccupancy(int fullCount, int nowCount);
}
