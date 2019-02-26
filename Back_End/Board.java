import java.util.ArrayList;

public class Board {

    private ArrayList<Tile> board;

    public ArrayList<Tile> getBoard() {
        return board;
    }

    public void setSquareBoard(int board_row_length) {


        for (int x = 1; x <= board_row_length; x++) {   // This for loops adds all tiles to the list for a square board.
            for (int y = 1; y <= board_row_length; y++) {
                Tile newTile = new Tile(true, false, x, y);
                boolean bool = newTile.setMine(board_row_length); // Places mine on newTile or does not by setMine (with probability of a mine being 1/n)..

                board.add(newTile);
            }
        }

        int x1, y1;
        for (Tile Tile1 : board) {      // Adds neighbors for every tile in a square board
            x1 = Tile1.getX_component();
            y1 = Tile1.getY_component();
            for (Tile Tile2 : board) {
                int x2, y2;
                x2 = Tile2.getX_component();
                y2 = Tile2.getY_component();

                if (((y1 - y2 >= -1) && (y1 - y2 <= 1) && (x1 - x2 >= -1) && (x1 - x2 <= 1)) && (x1 != x2 || y1 != y2)) {
                    if (!(Tile1.has_neighbor(Tile2))) {
                        Tile1.add_neighbor(Tile2);
                        if (!(Tile2.has_neighbor(Tile1))) {
                            Tile2.add_neighbor(Tile1);
                        }
                    }
                }
            }

            Tile1.setNumMineNeighbors();
        }
    }
}
