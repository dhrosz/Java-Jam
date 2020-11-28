package JavaJam;

enum EnvironmentTypes {
    SQUARE, ROW, COLUMN,
}

class SudokuBoard {
    private SudokuCells cells;
    private Environment[]
            squares = new Environment[9],
            rows = new Environment[9],
            columns = new Environment[9];

    SudokuBoard(SudokuCells cells) {
        this.cells = cells;

        buildEnvironments();
        setEnvironments();
    }

    private void buildEnvironments() {
        // TODO: Use something other than row.
        // this logic assumes that the board is a perfect square and certainly may be,
        // but I'd like to see if I can make this more abstract than making that assumption
        // The "square" environment will be paricularly tricky to udpate for that though.
        for (int row=0; row<this.cells.getHeight(); row++) {
            Environment lexicalRow = EnvironmentFactory.build(EnvironmentTypes.ROW, row, this.cells);
            rows[row] = lexicalRow;
            Environment lexicalColumn = EnvironmentFactory.build(EnvironmentTypes.COLUMN, row, this.cells);
            columns[row] = lexicalColumn;
            Environment lexicalSquare = EnvironmentFactory.build(EnvironmentTypes.SQUARE, row, this.cells);
            squares[row] = lexicalSquare;
        }
    }

    private void setEnvironments() {
        // TODO: Use something other than row.
        // this logic assumes that the board is a perfect square and certainly may be,
        // but I'd like to see if I can make this more abstract than making that assumption
        for (int row=0; row < this.cells.getHeight(); row++) {
            for (int column=0; column < this.cells.getWidth(); column++) {
                this.cells.forceDiscovery(row, column);
            }
        }
    }
}

