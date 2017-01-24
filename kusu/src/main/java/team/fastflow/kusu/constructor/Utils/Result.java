package team.fastflow.kusu.constructor.Utils;

/**
 * Created by KuSu on 07.01.2017.
 */

public class Result {
    int count = 0;
    int bad_count = 0;
    int good_count = 0;
    int unselected_count = 0;

    public void addBad() {
        count++;
        bad_count++;
    }

    public void addGood() {
        count++;
        good_count++;
    }

    public void addUnselected() {
        count++;
        unselected_count++;
    }

    public int getCount() {
        return count;
    }

    public int getBad_count() {
        return bad_count;
    }

    public int getGood_count() {
        return good_count;
    }

    public int getUnselected_count() {
        return unselected_count;
    }
}
