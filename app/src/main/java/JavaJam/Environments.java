package JavaJam;

class EnvironmentFactory {
    public static Environment build(EnvironmentTypes type, int ident) {
        Environment le = new Environment(ident);

        if (type == EnvironmentTypes.SQUARE) {
            le.setStrategy(new SquareEnvironment());
        }
        else if (type == EnvironmentTypes.COLUMN) {
            le.setStrategy(new ColumnEnvironment());
        }
        else if (type == EnvironmentTypes.ROW) {
            le.setStrategy(new RowEnvironment());
        }
        le.populate();

        return le;
    }
}

/**
 * Base environment class.
 *
 * Each environment will have individual bounded environments.
 */
class Environment {
    private int space;
    private BoundedEnvironment environmentType;

    public Environment(int space) {
        this.space = space;
    }

    public void discoverCandidates() {
        //TODO: Populate out the discover Candidates functionality.
    }

    public void setStrategy(BoundedEnvironment strategy) {
        this.environmentType = strategy;
        this.environmentType.setSpace(this.space);
    }

    public void populate() {
        this.environmentType.populate();
    }
}



/**
 * Bounded Environment interface
 *
 * A bounded environment is designed to be an indicator of the
 *  "boundaries" of an environment. The interface ensures that various
 *  types of environments can all be populated the same way. The practical
 *  case for this is allowing an object of type Environment to populate
 *  its members without inherently knowing how a configured BoundedEnvironment
 *  is accomplishing that.
 */
interface BoundedEnvironment extends EnvironmentSubscriberInterface {
    void populate();
    void setSpace(int space);
}

/**
 * Abstract environment class.
 *
 * An Amorphous Environment is an Environment that doesn't have any definition
 *  yet. It exists as the base case for an environment, and it's expected that
 *  it will be build upon. AmorphousBoundedEnvironment DOES NOT fully implement
 *  the BoundedEnvironment Interface.
 */
abstract class AmorphousBoundedEnvironment implements BoundedEnvironment {
    protected int space;
    protected SudokuCell[] elements;

    public void setSpace(int space) {
        this.space = space;
    }

    public AmorphousBoundedEnvironment() {
        this.elements = new SudokuCell[9];
    }
}

/**
 * @see BoundedEnvironment
 *
 * BoardSqaure represents a 3x3 square on a Sudoku board, and not an individual
 *  square.
 */
class SquareEnvironment extends AmorphousBoundedEnvironment {
    private int squareSize;
    private int x, y;

    public SquareEnvironment() {
        this.squareSize = 3;
    }

    public void setSpace(int space) {
        this.space = space;

        this.x = (this.space % this.squareSize);
        this.y = (this.space / this.squareSize);
    }

    public boolean possesses(int value) {
        return false;
    }

    public void populate() {
        for (int i=0; i < 9; i++) {
            int posX = (i % this.squareSize) + ((this.x * this.squareSize));
            int posY = (i / this.squareSize) + ((this.y * this.squareSize));
            this.elements[i] = SudokuBoard.board[posX][posY];
            SudokuBoard.board[posX][posY].subscribe(this);
        }
    }

    public boolean contains(int value){
        for (int i = 0; i < elements.length; i++) {
            if (this.elements[i].value == value) {
                return true;
            }
        }
        return false;
    }
}

/**
 * @see BoundedEnvironment
 *
 * An individual column on a given sudoku board.
 */
class ColumnEnvironment extends AmorphousBoundedEnvironment {
    public boolean possesses(int value) {
        return false;
    }

    public void populate() {
        for (int i=0; i < 9; i++) {
            this.elements[i] = SudokuBoard.board[i][this.space];
            SudokuBoard.board[i][this.space].subscribe(this);
        }
    }

    public boolean contains(int value){
        for (int i = 0; i < elements.length; i++) {
            if (this.elements[i].value == value) {
                return true;
            }
        }
        return false;
    }
}

/**
 * @see BoundedEnvironment
 *
 * An individual row on a given sudoku board.
 */
class RowEnvironment extends AmorphousBoundedEnvironment {
    public static boolean possesses(int space) {
        return false;
    }

    public void populate() {
        for (int i = 0; i < 9; i++) {
            this.elements[i] = SudokuBoard.board[this.space][i];
            SudokuBoard.board[this.space][i].subscribe(this);
        }
    }

    public boolean contains(int value) {
        for (int i = 0; i < elements.length; i++) {
            if (this.elements[i].value == value) {
                return true;
            }
        }
        return false;
    }
}
