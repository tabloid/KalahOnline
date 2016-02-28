package GVA.Kalah.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v.herasymenko on 2/25/2016.
 */
public class PlayerBoard {
    @JsonIgnore
    private final int stonesNumber = 1;
    @JsonIgnore
    private final int pitsNumber = 6;
    @JsonIgnore
    private int lastPitPosition = -1;
    @JsonProperty
    private int kalah = 0;
    @JsonProperty
    private final List<Integer> pitsList = new ArrayList<Integer>(pitsNumber);


    public PlayerBoard() {
        for (int i = 0; i < pitsNumber; i++)
            pitsList.add(i, stonesNumber);
    }


    public int pickStonesFromPit(int pit) {
        int stones = pitsList.get(pit);
        pitsList.set(pit, 0);
        return stones;
    }

    public boolean pitIsEmpty(int pit) {
        return pitsList.get(pit).equals(0);
    }

    public int putStonesInPits(int pit, int stones) {
        lastPitPosition = -1;
        for (int i = pit; i < pitsNumber; i++) {
            if (stones == 0)
                return 0;
            if (stones == 1) {
                if (pitIsEmpty(i))
                    lastPitPosition = i;
            }
            int newValue = pitsList.get(i) + 1;
            pitsList.set(i, newValue);
            stones--;
        }
        return stones;
    }

    public int pullStoneInKalah(int stones) {
        if (stones == 0)
            return 0;
        kalah++;
        stones--;
        return stones;
    }

    public void addStonesInKalah(int stones) {
        kalah += stones;
    }

    @JsonIgnore
    public boolean isLastStoneCameToEmptyPit() {
        return lastPitPosition != -1;
    }

    public int getLastPitPosition() {
        return lastPitPosition;
    }

    public boolean allPitsAreEmpty() {
        boolean flag = true;
        for (int i : pitsList)
            if (i != 0) {
                flag = false;
                break;
            }
        return flag;
    }

    @JsonIgnore
    public int getTotalStones() {
        int sum = 0;
        for (int i : pitsList)
            sum += i;
        sum += kalah;
        return sum;
    }

    public int getPitsNumber() {
        return pitsNumber;
    }

}
