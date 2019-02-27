import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class GUI extends JFrame {

    int boardSize = 8;

    Board mineBoard = new Board();
    int height = (532/5)*boardSize;
    int width = (450/5)*boardSize;
    int boardX, boardY, tileID = 0;

    public int mx= -100;
    public int my= -100;

    //Creating a window
    public GUI(){
        mineBoard.setSquareBoard(boardSize);
        this.setTitle("Minesweeper"); //sets title to window
        this.setSize(width, height); //sets size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes sure program is terminated when exited
        this.setResizable(false); // set it so no one can resize the window

        FBoard GUIboard = new FBoard();
        this.setContentPane(GUIboard);//sets content panel
        this.setVisible(true); //makes window visible

        Move move= new Move();
        this.addMouseMotionListener(move);

        Click click= new Click();
        this.addMouseListener(click);

    }

    public class FBoard extends JPanel{

        //sets background color
        public void paintComponent(Graphics graphics){
            graphics.setColor(Color.gray);
            graphics.fillRect(0, 0,width,height);

            //sets grid
            int spacing = 5;
            int i, j;

            for(i = 0; i < boardSize; i++) {
                for (j = 0; j < boardSize; j++){
                    graphics.setColor(Color.lightGray);

                    //testing the mine layout
                    if (mineBoard.getBoard().get(i+(boardSize*j)).getMine())
                    {
                      graphics.setColor(Color.pink);
                    }

                    if(mx>=spacing+i * 86 && mx< spacing+i * 86+86-spacing && my>=j * 86+86 + spacing+26 && my< j * 86+86  +26 +spacing +86-spacing )
                    {
                        //this gets the corresponding tile coordinates
                        boardX=i;
                        boardY=j;
                        //grabs specific tile ID for the tile
                        tileID= boardX + (boardSize*boardY);
                        //colors tile red it it is a mine
                        if (mineBoard.getBoard().get(tileID).getMine())
                        {
                          graphics.setColor(Color.red);
                        }
                        else
                        {
                          graphics.setColor(Color.blue);
                        }
                    }
                    graphics.fillRect(i * 90, j * 90 + (12 * spacing),90-spacing,90-spacing );

                }
            }
        }
    }
    //sets display for mouse movement and coordinates
    public class Move implements MouseMotionListener
    {

        public void mouseDragged(MouseEvent a)
        {

        }
        public void mouseMoved(MouseEvent a)
        {
            System.out.println("the mouse was moved");
            mx=a.getX();
            my=a.getY();
            System.out.println("x:"+ mx +" y:"+my);

            System.out.println("board X:"+boardX+" board y:"+boardY);
        }

    }
    //sets display for mouse click and coordinates
    public class Click implements MouseListener {
        public void mouseClicked(MouseEvent a) {
            System.out.println("the mouse was clicked");
            try {
              //checks if the clicked tile is a mine
              if (mineBoard.getBoard().get(tileID).getMine())
              {
                System.out.println("the tile was a mine!");
              }
              //checks if clicked tile has mine neighbors
              else if (mineBoard.getBoard().get(tileID).getNumMineNeighbors()>0)
              {
                System.out.println("there are " + mineBoard.getBoard().get(tileID).getNumMineNeighbors() + " mine neighbors");
              }
              else
              {
                System.out.println("there are no mine neighbors!");
              }
            }
            catch(Exception e)
            {
              System.out.println("");
            }



        }

        public void mouseEntered(MouseEvent a) {

        }

        public void mouseExited(MouseEvent a) {

        }

        public void mousePressed(MouseEvent a) {

        }

        public void mouseReleased(MouseEvent a) {

        }

    }

}
