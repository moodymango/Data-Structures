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
    //set the viewing perspective of the board depending on the side of the board
        board.setViewingPerspective(side);
    //loop by column, starting with the leftmost column which starts at columns 0
    for (int col = board.size() - 1; col >=0 ; col--) {
        //for each column, I want to call the tiltByColumn method
        //since tiltByColumn returns a local score, we add the evaluated result of the function invocation to the score variable
        score += tiltByColumn(col, board);
        changed = true;
    }
        checkGameOver();
        if (changed) {
            setChanged();
        }
        board.setViewingPerspective(Side.NORTH);
        return changed;
    }
/* shifts the positions of the tiles by column*/
    public int tiltByColumn (int col, Board b) {
        //create a variable merged that keeps track of whether or not a merge is already capable and has already taken place
        Tile merged = null;
        //create local variable that keeps track of the score for this column
        int localScore = 0;
        //start loop at the second row
        int row = b.size() - 2;
        //loop while row is greater than or equal to zero
        while (row >= 0) {
            Tile currTile = b.tile(col, row);
            if(currTile == null) {
                row--;
                continue;
            }
          //maybe use another helper function to determine how many spaces to move each individual tile
            int jumpRows = moveTileBynSpaces(row, col, b);
            //if a merge tile exists, we want to check if the value of the current tile is equal to the saved Tile
            if(merged != null) {
                if (currTile.value() == merged.value()) {
                    //want to decrement the jump rows number by 1 because we dont want the tiles to merge
                    jumpRows--;
                }
            }
          //call b.move with the number of jumpRows, and will return out a boolean
           boolean didMerge =  b.move(col, jumpRows + row, currTile);
           //if the boolean didMerge is true, then we take the value of the current tile, double it, and add it to local score
            if (didMerge) {
                //need to grab the tile's value;
                int tileVal = currTile.value() * 2;
                localScore += tileVal;
                //assign local merged the value of the merged tile
                merged = b.tile(col, jumpRows + row);
            }
            //decrement row to keep the loop going
            row--;
        }
        return localScore;
    }
/* returns the number of rows an individual tile must move*/
public int moveTileBynSpaces(int row, int col, Board b) {
    //board should be behaving as if East is north but is giving the wrong row?
    //want to make two checks
    //1 if the tiles above are equal to the currTile
    //2 if there are null tiles above the current tile
    //create a variable spaces that represents the number of rows that the tile should move
    int spacesJumped = 0;
    //use current row as index to traverse all tiles above the curr tile
    int localRow = row;
    int localCol = col;
    //orientation of the top row should ideally change but for some reason it's not, current tile should be (2, 2) but it is (2,1 instead);
    //create loop that iterates from the current row + 1 to the b.size()
    for (int i = localRow + 1; i < b.size(); i++) {
        //check the value of the curr tile compared to all the tiles above it
        Tile t = b.tile(col, row);
        Tile aboveTile = b.tile(col, i);
        //if the curr tile does not exist, we can move the tile up one, so we increment the spaces jumped variable by 1
        if (aboveTile == null) {
            //increment spaces jumped
            spacesJumped++;
        } else {
            //if the current tile has an equal value to the one above, increment spaces jumped
            if (aboveTile.value() == t.value()) {
                spacesJumped++;
            }
        }
    }
    return spacesJumped;
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
            //if indices are within bound, return the neighboring tile
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
