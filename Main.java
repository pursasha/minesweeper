import java.lang.Math;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {


        ArrayList<Tile> board = generate_square_board();
        Tile tile = new Tile(true,true,1,1);
        tile.add_neighbor(new Tile(false,true,2,2));
        System.out.println(tile.getNeighbors());
        System.out.println(board.get(20).getNeighbors());

        }

    public static ArrayList<Tile> generate_square_board() {

        int numMinesTotal, possible_mines_max, possible_mines_min, board_row_length,numMinesCurrent = 0;

        board_row_length = 4; // Number of tiles in a given row // For a square board, this is square root of number of tiles.

        possible_mines_max = 7; // Number of mines on board is a random number between min and max, inclusive.
        possible_mines_min = 2;
        numMinesTotal = (int) (Math.random() * ((possible_mines_max - possible_mines_min) + 1)) + possible_mines_min;

        ArrayList<Tile> board = new ArrayList<>(); // Initialize board as an empty array list, to which nodes will be added.

        for (int x = 0; x <= board_row_length; x++) {   // This for loops adds all tiles to the list for a square board.
            for (int y = 0; y <= board_row_length; y++) {
               Tile newTile = new Tile(true, false, x, y);
                boolean bool=newTile.setMine(); // Randomly places mine on newTile or does not (total mines will be the randomly generated numMines above).
                if(bool==true){
                    numMinesCurrent++;
                }
                if(numMinesCurrent>numMinesTotal){
                    newTile.setIsMine(false);
                }
                board.add(newTile);
            }
        }
                 //Iterator Tile1=board.iterator();
                 int x1,y1;
                 //while(Tile1.hasNext()){
                 for(Tile Tile1:board){      // Adds neighbors for every tile in a square board
                     x1=Tile1.getX_component();
                     y1=Tile1.getY_component();
                     for(Tile Tile2:board){
                         //Iterator Tile2=board.iterator();
                         int x2,y2;
                         //while(Tile2.hasNext()){
                         x2=Tile2.getX_component();
                         y2=Tile2.getY_component();
                         if(x1!=0&&x1!=9&&y1!=0&&y1!=9&&x2!=0&&x2!=9&&y2!=0&&y2!=9){
                             if((x1==x2&&((y1==y2-1)||(y1==y2+1))||(y1==y2&&((x1==x2-1)||(x1==x2+1))||((x1==x2+1)&&(y1==y2+1))||((x1==x2+1)&&(y1==y2-1))||((x1==x2-1)&&(y1==y2+1))||((x1==x2-1)&&(y1==y2-1))))){
                                 Tile1.add_neighbor(Tile2);
                                 if(Tile2.has_neighbor(Tile1)==false){
                                     Tile2.add_neighbor(Tile1);
                                 }
                             }

                             }
                     }
                     }
                 return board;}
            }




