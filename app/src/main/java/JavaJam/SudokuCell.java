package JavaJam;

import java.util.ArrayList;

public class SudokuCell implements EnvironmentPublisherInterface {
    public int column, row, value;
    private ArrayList<Integer> candidates;
    private ArrayList<EnvironmentSubscriberInterface> environmentSubscriptions;

    public SudokuCell() {
        super();
    }

    public SudokuCell(int value, int row, int column) {
        this();
        this.value = value;
        this.row = row;
        this.column = column;
        this.candidates = new ArrayList<Integer>();
        this.environmentSubscriptions = new ArrayList<>();
        initCandidates();
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void initCandidates() {
        this.candidates.clear();
        for (int index=1; index < 10; index++) {
            this.candidates.add(index);
        }
    }

    public void removeCandidate(Integer candidate) {
        candidates.remove(candidate);
        String breakString = "";
    }

    public void subscribe(EnvironmentSubscriberInterface es) {
        this.environmentSubscriptions.add(es);
    }

    public void discoverCandidates() {
        for (int i=0; i < candidates.size(); i++ ) {
            for (int eSIterator = 0; eSIterator < this.environmentSubscriptions.size(); eSIterator++) {
                if (this.environmentSubscriptions.get(eSIterator).contains(i + 1)) {
                }
                else {
                    //this.candidate
                }
            }
        }
    }


    /**
     * Return whether or not the cell believes it has a value.
     * @return
     */
    public boolean hasValue() {
        if (this.value != 0) {
            return true;
        }
        else
            return false;
    }

    /**
     * Empty a given cell's candidates.
     */
    public void clean() {
        this.candidates.clear();
    }

    /**
     * Return a gives cells possible Candidates.
     * @return
     */
    public ArrayList<Integer> getCandidates() {
        return this.candidates;
    }


    /**
     * Return the current value of a cell.SQUARE
     * po
     * @return
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Return a given cell's column.
     * @return
     */
    public int getColumn() {
        return column;
    }

    /**
     * Return a given cell's row.
     * @return
     */
    public int getRow() {
        return row;
    }

}
