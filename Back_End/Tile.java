import java.io.Serializable;
import java.lang.Math;
import java.util.ArrayList;


public class Tile implements Serializable {
    private boolean isMine;
    private boolean isRevealed;
    private int x_component;
    private int y_component;
    public ArrayList<Tile> neighbors;


    public Tile(boolean isMine,boolean isRevealed,int x_component,int y_component){
        this.isMine=isMine;
        this.isRevealed=isRevealed;
        this.x_component=x_component;
        this.y_component=y_component;
        this.neighbors= new ArrayList<>();
    }



    public boolean isMine() {
        return isMine;
    }
    public void setIsMine(boolean mine){
        this.isMine = mine;
    }

    public void setMine(boolean mine) { // Sets mine to on or off based on the passed boolean value (This method is used at times to overwrite the random setMine method below.)
        isMine = mine;
    }

    public boolean setMine(int board_row_length) { // Randomly sets mine to on or off // Returns True for mine or False for no mine
        int x, RandomMine,RandomMine2;
        boolean mine=true;
        //
        //
        int max=1,min=0;
        RandomMine = (int) (Math.random() * ((board_row_length - 1) + 1)) + 1;
        RandomMine2= (int) (Math.random() * ((board_row_length - 1) + 1)) + 1;
        //if(RandomMine==4){
        //x=(int)(Math.random() * ((max - min) + 1)) + min;
        //x is in {0,1} based off a Random function
        //if(x==1){
            if(RandomMine==RandomMine2){
            mine = true;}
        //}
        //if(x==0){
            else {
                mine = false;
            }
        //}
        isMine = mine;
        return mine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed; // Something based off mouse clicking events
    }

    public int getX_component() {
        return x_component;
    }

    public void setX_component(int x_component) {
        this.x_component = x_component;
    }

    public int getY_component() {
        return y_component;
    }

    public void setY_component(int y_component) {
        this.y_component = y_component;
    }


    public ArrayList<Tile> getNeighbors() {
        return neighbors;
    }

    /*public void setNeighbors(ArrayList neighbors) {
        this.neighbors = neighbors;
    }*/

    public void add_neighbor(Tile new_neighbor_tile){
        this.neighbors.add(new_neighbor_tile);
    }

    public boolean has_neighbor(Tile possible_neighbor){
        if (this.neighbors.contains(possible_neighbor)){
            return true;}
        else{
            return false;}

        }
    }

