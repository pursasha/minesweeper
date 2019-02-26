import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class GUI extends JFrame {

  //  Tile tile = new Tile(true, true, 1, 1);
    int height = 532;
    int width = 450;
    int boardX, boardY = -99;

    public int mx= -100;
    public int my= -100;

    //Creating a window
    public GUI(){
        this.setTitle("Minesweeper"); //sets title to window
        this.setSize(width, height); //sets size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes sure program is terminated when exited
        this.setResizable(false); // set it so no one can resize the window

        Board board = new Board();
        this.setContentPane(board);//sets content panel
        this.setVisible(true); //makes window visible

        Move move= new Move();
        this.addMouseMotionListener(move);

        Click click= new Click();
        this.addMouseListener(click);


    //    tile.add_neighbor(new Tile(false, true, 2, 2));




    }

    public class Board extends JPanel{

        //sets background color
        public void paintComponent(Graphics graphics){
            graphics.setColor(Color.gray);
            graphics.fillRect(0, 0,width,height);

            //sets grid
            int spacing = 5;
            int i, j;

            for(i = 0; i < 5; i++) {
                for (j = 0; j < 5; j++){
                    graphics.setColor(Color.lightGray);

                    if(mx>=spacing+i * 86 && mx< spacing+i * 86+86-spacing && my>=j * 86+86 + spacing+26 && my< j * 86+86  +26 +spacing +86-spacing )
                    {
                        graphics.setColor(Color.blue);
                        //this gets the corresponding tile coordinates
                        boardX=i;
                        boardY=j;
                    }
                    graphics.fillRect(i * 90, j * 90 + (12 * spacing),90-spacing,90-spacing );

                //      if(tile.isMine() == true ){
                  //          if(mx>=spacing+i * 86 && mx< spacing+i * 86+86-spacing && my>=j * 86+86 + spacing+26 && my< j * 86+86  +26 +spacing +86-spacing )
                //            {
                //                graphics.setColor(Color.red);
              //              }
              //      }
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
