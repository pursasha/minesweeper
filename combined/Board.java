import java.util.ArrayList;

public class Board {
    private int board_row_length;
    private int mine_probability=5; // Reciprocal of actual probability
    private boolean gameOver=false;
    private int numBoardMines = 0;
    private int numCoveredTiles = board_row_length * board_row_length;
    private ArrayList<Tile> board;
    private boolean first_click=true;
    private int numFlaggedMines=0;
    private int numRevealedTiles=0;

    //basic constructor for ease of making a board
    public Board()
    {
        //sets the board to an empty arraylist
        board = new ArrayList<>();
    }
	public Board(int newBoardLength)
    {
        //sets the board to an empty arraylist
        board = new ArrayList<>();
		board_row_length = newBoardLength;
    }

    public ArrayList<Tile> getBoard() {
        return board;
    }

    public void setSquareBoard() {


        for (int x = 0; x <= board_row_length-1; x++) {   // This for loops adds all tiles to the list for a square board.
            for (int y = 0; y <= board_row_length-1; y++) {
                Tile newTile = new Tile(true, false, x, y);
                boolean bool = newTile.setMine(mine_probability); // Places mine on newTile or does not by setMine (with probability of a mine being 1/n)..
                if (bool) {numBoardMines++;}

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
    public int flaggedMines(){
        int count = 0;
        for(Tile x : board){
            if(x.isFlag()&&x.getMine())
                count++;
            else if(x.isFlag()&&!x.getMine())
                count--;
        }
        return numFlaggedMines=count;
    }
    public int revealedTiles(){
        int count= 0;
        for(Tile x : board){
            if(x.isRevealed())
                count++;
        }
        return numRevealedTiles=count;
    }

 public boolean reveal(int tileID) {
        Tile Tile1 = board.get(tileID);
	if(first_click){
	if(Tile1.getMine())
	{
	for(Tile Tile1Neighbor : Tile1.neighbors)
	{
			if(Tile1Neighbor.getX_component()*board_row_length+Tile1Neighbor.getY_component()!=tileID){
			Tile1Neighbor.decrementNumMineNeighbors();
		}
	}
		Tile1.setMine(false);
	}
	first_click=false;
	}


        if (!(Tile1.isRevealed()))
        {
            if (Tile1.getMine()) {
                return true;
            }
            else {
                Tile1.setRevealed(true);
                if (Tile1.getNumMineNeighbors() == 0) {
                    for (Tile Tile2 : Tile1.neighbors) {
                        reveal(Tile2.getX_component()*board_row_length + Tile2.getY_component());
                    }
                }
            }
        }
        return false;
    }
     public boolean winner(){
        if(flaggedMines()==(numBoardMines))
            return true;
        else if(revealedTiles()==(board_row_length*board_row_length-numBoardMines))
            return true;
        return false;
     }
    public int getNumBoardMines() {return numBoardMines;}

    public boolean getRemainingTiles() {return numCoveredTiles == numBoardMines;}

    public void setRemainingTiles() {numCoveredTiles--;}

}
