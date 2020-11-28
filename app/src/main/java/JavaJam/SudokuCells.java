package JavaJam;

import java.util.ArrayList;

public class SudokuCells {
    private ArrayList<ArrayList<SudokuCell>> cells;

    public SudokuCells() {
        this.cells = new ArrayList<ArrayList<SudokuCell>>();
    }

    public void add(SudokuCell cell) {
        if (this.cells.size() < cell.getRow() || this.cells.size() == 0) {
            this.cells.add(new ArrayList<SudokuCell>());
        }
        this.cells.get(cell.getRow()).add(cell.getColumn(), cell);
    }

    public void add(ArrayList<SudokuCell> cells) {
        this.cells.add(cells);
    }

    public int getWidth() {
        return this.cells.get(0).size();
    }

    public int getHeight() {
        return this.cells.size();
    }

    public void forceDiscovery(int row, int col) {
        this.cells.get(row).get(col).discoverCandidates();
    }

    public SudokuCell getCell(int row, int col) {
        return this.cells.get(row).get(col);
    }
}
