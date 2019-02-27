import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class GUI extends JFrame {

    public int mx = -100;
    public int my = -100;
    Board mineBoard = new Board();
    int height = 532;
    int width = 450;
    int boardX, boardY = -99;

    boolean gameOver, tile1;

    //Creating a window
    public GUI() {
        mineBoard.setSquareBoard(5);
        this.setTitle("Minesweeper"); //sets title to window
        this.setSize(width, height); //sets size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes sure program is terminated when exited
        this.setResizable(false); // set it so no one can resize the window

        FBoard GUIboard = new FBoard();
        this.setContentPane(GUIboard);//sets content panel
        this.setVisible(true); //makes window visible

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);


    }

    public class FBoard extends JPanel {

        //sets background color
        public void paintComponent(Graphics graphics) {
            graphics.setColor(Color.gray);
            graphics.fillRect(0, 0, width, height);


            //sets grid
            int spacing = 5;
            int i, j;

            for (i = 0; i < 5; i++) {
                for (j = 0; j < 5; j++) {

                    graphics.setColor(Color.lightGray);

                    //sets screen black if mine is clicked
                    if (gameOver == true) {
                        graphics.setColor(Color.black);
                        graphics.fillRect(0, 0, width, height);
                    }


                    if (mx >= spacing + i * 86 && mx < spacing + i * 86 + 86 - spacing && my >= j * 86 + 86 + spacing + 26 && my < j * 86 + 86 + 26 + spacing + 86 - spacing) {

                        //  graphics.setColor(Color.blue);
                        //this gets the corresponding tile coordinates
                        boardX = i;
                        boardY = j;
                        //colors tile red it it is a mine
                        if (mineBoard.getBoard().get(boardX + boardY).getMine()) {
                            graphics.setColor(Color.red);
                        } else {
                            if (mineBoard.getBoard().get(boardX + boardY).isRevealed()) {
                                graphics.setColor(Color.white);
                            } else graphics.setColor(Color.blue);
                        }
                    }
                    graphics.fillRect(i * 90, j * 90 + (12 * spacing), 90 - spacing, 90 - spacing);
                }
            }
        }
    }

    //sets display for mouse movement and coordinates
    public class Move implements MouseMotionListener {

        public void mouseDragged(MouseEvent a) {

        }

        public void mouseMoved(MouseEvent a) {
            System.out.println("the mouse was moved");
            mx = a.getX();
            my = a.getY();
            System.out.println("x:" + mx + " y:" + my);

            System.out.println("board X:" + boardX + " board y:" + boardY);
        }

    }

    //sets display for mouse click and coordinates
    public class Click implements MouseListener {
        public void mouseClicked(MouseEvent a) {
            System.out.println("the mouse was clicked");
            mineBoard.getBoard().get(boardX + boardY).setRevealed(true);


            //checks if the clicked tile is a mine
            if (mineBoard.getBoard().get(boardX + boardY).getMine()) {
                System.out.println("the tile was a mine!");
                gameOver = true;

            }
            //checks if clicked tile has mine neighbors
            else if (mineBoard.getBoard().get(boardX + boardY).getNumMineNeighbors() > 0) {
                System.out.println("there are " + mineBoard.getBoard().get(boardX + boardY).getNumMineNeighbors() + " mine neighbors");
                
            } else {
                System.out.println("there are no mine neighbors!");

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
