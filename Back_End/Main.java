import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int board_row_length=10; // Sets the number of tiles in a side of the square board
        ArrayList<Tile> board = generate_square_board(board_row_length);

//        Tile tile = new Tile(true, true, 1, 1);
//        tile.add_neighbor(new Tile(false, true, 2, 2));
//
//        for(int countx=0;countx<=board_row_length-1;countx++){ // This for loop outputs the random mine distribution (based off every tile having a 1/n probability of being a mine).
//            System.out.println("");
//            for(int county=0;county<=board_row_length-1;county++){
//                if(board.get(countx*board_row_length+county).isMine()){
//                    System.out.print(" M");
//                }
//                else{
//                    System.out.print(" "+board.get(countx*board_row_length+county).getNumMineNeighbors()+"");}
//                }
//
//            }
        }


    public static ArrayList<Tile> generate_square_board(int board_row_length) {

        ArrayList<Tile> board = new ArrayList<>(); // Initialize board as an empty array list, to which nodes will be added.

        for (int x = 1; x <= board_row_length; x++) {   // This for loops adds all tiles to the list for a square board.
            for (int y = 1; y <= board_row_length; y++) {
                Tile newTile = new Tile(true, false, x, y);
                boolean bool=newTile.setMine(board_row_length); // Places mine on newTile or does not by setMine (with probability of a mine being 1/n)..

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
        return board;
    }
}






