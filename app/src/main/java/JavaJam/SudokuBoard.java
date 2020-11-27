package JavaJam;

import com.google.gson.Gson;

enum EnvironmentTypes {
    SQUARE, ROW, COLUMN,
}

class testBoard {

}

class SudokuBoard {
    static SudokuCell[][] board = new SudokuCell[9][9];
    private Environment[]
            squares = new Environment[9],
            rows = new Environment[9],
            columns = new Environment[9];

    SudokuBoard(String[] inputArray) {
        setBoard(inputArray);
        buildEnvironments();
        setEnvironments();
        String Breakpoint = "break";
    }
    SudokuBoard(String board) {
        Gson gson = new Gson();
        gson.fromJson(board, testBoard.class);
    }

    private void setBoard(String[] inputArray) {
        int iterator = 0;
        for(int row=0; row<9; row++){
            for (int col=0; col<9; col++) {
                if (inputArray[iterator] != "") {
                    board[row][col] = new SudokuCell(Integer.parseInt(inputArray[iterator]), row, col);
                }
                else {
                    board[row][col] = new SudokuCell(0, row, col);
                }
                iterator++;
            }
        }
    }

    private void buildEnvironments() {
        for (int x=0; x<9; x++) {
            Environment le = EnvironmentFactory.build(EnvironmentTypes.ROW, x);
            rows[x] = le;
        }
        for (int x=0; x<9; x++) {
            Environment le = EnvironmentFactory.build(EnvironmentTypes.COLUMN, x);
            columns[x] = le;
        }
        for (int x=0; x<9; x++) {
            Environment le = EnvironmentFactory.build(EnvironmentTypes.SQUARE, x);
            squares[x] = le;
        }
    }

    private void setEnvironments() {
        for (int row=0; row < SudokuBoard.board.length; row++) {
            for (int column=0; column < SudokuBoard.board[row].length; column++) {
                SudokuBoard.board[row][column].discoverCandidates();
            }
        }
    }
}

