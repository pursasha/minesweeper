import java.io.Serializable;
import java.lang.Math;
import java.util.ArrayList;
import java.awt.Color;

public class Tile implements Serializable {

    static final long serialVersionUID = 324324235L;

    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlag;
    private int x_component;
    private int y_component;
    public ArrayList<Tile> neighbors;
    int numMineNeighbors;
    // color of the tile, defaults to white, only set if the tile is a mine
    private Color tileColor = Color.white;

    public Tile(boolean isMine, boolean isRevealed, int x_component, int y_component) {

        this.isMine = isMine;
        this.isRevealed = isRevealed;
        this.x_component = x_component;
        this.y_component = y_component;
        this.neighbors = new ArrayList<>();
    }

    public boolean getMine() {
        return isMine;
    }

    public void setMine(boolean mine) { // Sets mine to on or off based on the passed boolean value. This method can be
        // used to overwrite the setMine method below.
        isMine = mine;
        //gives the mine a ramdom color of reg green or blue
        switch ((int)(Math.random() * 3))
        {
            case 0:
                tileColor = new Color (0, 0, 255);
                break;
            case 1:
                tileColor = new Color (0, 255, 0);
                break;
            case 2:
                tileColor = new Color (255, 0, 0);
                break;
        }
    }

    /*
    // Old Set Mine based off of probability (unnecessarily complex)
    public boolean setMine(int probability) { // Sets mine to on or off with probability on of 1/n. It returns true
        // for mine or false for no mine.

        int RandomMine, RandomMine2;
        boolean mine;
        RandomMine = (int) (Math.random() * ((probability - 1) + 1)) + 1;
        RandomMine2 = (int) (Math.random() * ((probability - 1) + 1)) + 1;

        if (RandomMine == RandomMine2) {
            mine = true;
            //gives the mine a ramdom color of reg green or blue
            switch ((int)(Math.random() * 3))
            {
                case 0:
                    tileColor = new Color (0, 0, 255);
                    break;
                case 1:
                    tileColor = new Color (0, 255, 0);
                    break;
                case 2:
                    tileColor = new Color (255, 0, 0);
                    break;
            }
        }

        else {
            mine = false;
        }

        isMine = mine;
        return mine;
    }
    */


    public boolean setMine(int probability) { // Sets mine to on or off with probability on of 1/n. It returns true
        // for mine or false for no mine.

        int RandomMine;
        boolean mine = false;
        RandomMine = (int) (Math.random() * probability + 1);

        if (RandomMine == 1) {
            mine = true;
            //gives the mine a ramdom color of reg green or blue
            switch ((int)(Math.random() * 3))
            {
                case 0:
                    tileColor = new Color (0, 0, 255);
                    break;
                case 1:
                    tileColor = new Color (0, 255, 0);
                    break;
                case 2:
                    tileColor = new Color (255, 0, 0);
                    break;
            }
        }
        else {
            mine = false;
        }

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

    public void add_neighbor(Tile new_neighbor_tile) {
        this.neighbors.add(new_neighbor_tile);
    }

    public boolean has_neighbor(Tile possible_neighbor) {

        if (this.neighbors.contains(possible_neighbor)) {
            return true;
        }

        else {
            return false;
        }
    }

    public int getNumMineNeighbors() {
        return numMineNeighbors;
    }

    public void setNumMineNeighbors() {
        int numMineNeighbors = 0;
        for (Tile neighbor : this.neighbors) {
            if (neighbor.getMine()) {
                numMineNeighbors++;
				if (neighbor.getColor().equals(tileColor))
				// if the mine neighbor has same color, swap to a new color
				{
					if (tileColor.equals(new Color (255, 0, 0)))
					{
						neighbor.setColor(new Color (0, 255, 0));
					}
					else if (tileColor.equals(new Color (0, 255, 0)))
					{
						neighbor.setColor(new Color (0, 0, 255));
					}
					else if( tileColor.equals(new Color (0, 0, 255)))
					{
						neighbor.setColor(new Color (255, 0, 0));
					}
				}
            }
        }
        this.numMineNeighbors = numMineNeighbors;
    }


    public void decrementNumMineNeighbors() {
        numMineNeighbors=numMineNeighbors-1;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean on_off) {
        isFlag = on_off;
    }

    public Color getColor()
    //get the color of the tile
    {
        return tileColor;
    }
    public void setColor(Color newColor)
    // set the color of the tile
    {
        tileColor = newColor;
    }

}
