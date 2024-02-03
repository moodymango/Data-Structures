package game2048;

import java.util.*;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.

        //tilting Up
        //pieces below top row can move up if the space above has the same value or is empty
            //iterate from row 3 down
        //can only move on a given tile once
        //create helper function that processes each column and moves the tile up by column


        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }
//    shifts the positions of the tiles by column
    public void tiltByColumn (int col, Board b) {
        //create a local variable merged to check if a merge has been completed in this column already
        boolean merged = false;
        //create a local object to keep track of the moved tiles in the column, starting with the initial order of the tiles
        Tile [] columnOrder = new Tile [4];
        int size = b.size() - 1;
        while (size >= 0 ) {
            columnOrder[size] = b.tile(col, size);
        }
        //save the value of the tile in the uppermost row (row 3)
        Tile upperMost = b.tile(col, b.size() - 1);
        //start loop at the row beneath uppermost row (row 2)
        int row = b.size() - 2;
        //loop while row is greater than or equal to zero
        while (row >= 0) {
            Tile currTile = b.tile(col, row);
          //maybe use another helper function to determine how many spaces to move each individual tile

        }
    }
//   returns the number of rows an individual tile must move
    public int moveTileBynSpaces(Tile t, Tile[] tileOrder, Board b) {
        //determines how each individual tile will move across the board
        //want to make two checks
        //1 if the tiles above are equal to the currTile

        //2 if there are null tiles above the current tile
    }
    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // return true if any of the tiles in the given board are null
        //utilize tile(int col, int row) and size() methods of board class
        //want to iterate through the multidimentional array
        //use nested for loop to iterate through all the rows and columns
        for (int row = b.size() - 1; row >= 0; row-- ) {
            for (int col = 0; col < b.size(); col++) {
                //remember to start from the bottom right of the board
//                [[0, 0, 0],
//                [0, 0, 0],
//                [0, 0, 0],
                //want to access the current tile starting from the bottom
                //tile method returns null if there is no tile there
                Tile tile = b.tile(col, row);
                System.out.println(tile);
                if(tile == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        for (int row = b.size() - 1; row >= 0; row-- ) {
            for (int col = 0; col < b.size(); col++) {
                //remember to start from the bottom right of the board
//                [[0, 0, 0],
//                [0, 0, 0],
//                [0, 0, 0],
                //want to access the current tile starting from the bottom
                //tile method returns null if there is no tile there
                Tile tile = b.tile(col, row);
                if(tile == null) {
                    continue;
                }
                if(tile.value() == MAX_PIECE ) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
       //two ways for there to be a valid move;
        //1. there is at least one empty space on the board
        //can simply call emptySpace Exists on Board b for the first condition
        boolean emptySpace = emptySpaceExists(b);
        //if the boolean empty space returns true, then return true
        if (emptySpace) {
            return true;
        }
        //2. there are two adjacent tiles with the same value
        //as we iterate through the multidimesional array, we have to check the neighboring values of the current tile
        //start loop from the bottom left corner
        for(int row = b.size() - 1; row >= 0; row--) {
            for (int col = 0; col < b.size(); col++) {
                //for each of the values in the array, we must check the neighboring elements
                //upper element will be same column, row - 1;
                //right el will be same row, column + 1;
                //bottom el will be same column, row + 1
                //left e1 will be same row, column - 1
                Tile currTile = b.tile(col, row);
                //if the current tile is not null,
                if (currTile != null) {
                    //start checking the index of the neigboring tiles to ensure they are within board bounds
                    Tile upperTile = tileIdxWithinBound(col,row - 1, b);
                    Tile rightTile = tileIdxWithinBound(col + 1, row, b);
                    Tile lowerTile = tileIdxWithinBound(col, row + 1, b);
                    Tile leftTile = tileIdxWithinBound(col - 1, row, b);
                    // now we check the values of each tile to see if the value of the current tile is equal to value of the neighboring tiles
                    Tile [] neighborTiles = {upperTile, rightTile, lowerTile, leftTile};
                    //pass in tile array to helper function to check tile values
                    boolean equalNeighbors = checkNeighborTileVal(neighborTiles, currTile);
                    if (equalNeighbors) {
                        return true;
                    }

                }

            }
        }
        return false;
    }
 /*   checks if neighboring elements are within the elements of the board and returns the neighboring tile */
    public static Tile tileIdxWithinBound (int col, int row, Board b) {
        //return null if the tile is out of bounds;
        if ((col >=0 && col < b.size()) && (row >=0 && row < b.size())) {
            //if indices are within bound, return the neighboring tiles
           Tile neighbor = b.tile(col, row);
           return neighbor;
        }
        return null;
    }
    public static boolean checkNeighborTileVal (Tile[] neighbors, Tile mainTile) {
        //iterate through the array of tiles, comparing the values of the neighboring tiles to the mainTile
        int i = 0;
        while (i < neighbors.length) {
            Tile neighbor = neighbors[i];
            if (neighbor != null) {
                if (mainTile.value() == neighbor.value()) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }



    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
