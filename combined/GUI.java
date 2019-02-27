import javax.swing.*;
import java.awt.*;
<<<<<<< HEAD
import java.awt.event.*;
import java.lang.Integer;
=======
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
>>>>>>> b66fb0d383b45805e53e0b8870801cb72a42656f


public class GUI extends JFrame {

<<<<<<< HEAD
    int boardSize = 5;

    Board mineBoard = new Board();
    int height = (532/5)*boardSize;
    int width = (450/5)*boardSize;
    int boardX, boardY, tileID = 0;
    boolean gameOver;
=======
    public int mx = -100;
    public int my = -100;
    Board mineBoard = new Board();
    int height = 532;
    int width = 450;
    int boardX, boardY = -99;
>>>>>>> b66fb0d383b45805e53e0b8870801cb72a42656f

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
<<<<<<< HEAD
        public void paintComponent(Graphics graphics){
            int fontsize = 48;
            Font mineFont = new Font("TimesRoman", Font.PLAIN, fontsize);
            FontMetrics fontData = graphics.getFontMetrics(mineFont);
            int fontW, fontH;
            String mineNums;
            graphics.setFont(mineFont);
=======
        public void paintComponent(Graphics graphics) {
>>>>>>> b66fb0d383b45805e53e0b8870801cb72a42656f
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

                    //sets screen black if mine is clicked
                    if (gameOver == true) {
                        graphics.setColor(Color.black);
                        graphics.fillRect(0, 0, width, height);
                    }
<<<<<<< HEAD
                    if(mx>=(spacing+i * 90) && mx< (spacing+i * 90+90-spacing) && my>=(j * 90+90 + spacing+26) && my< (j * 90+90  +26 +spacing +90-spacing) )
                    {
=======


                    if (mx >= spacing + i * 86 && mx < spacing + i * 86 + 86 - spacing && my >= j * 86 + 86 + spacing + 26 && my < j * 86 + 86 + 26 + spacing + 86 - spacing) {

                        //  graphics.setColor(Color.blue);
>>>>>>> b66fb0d383b45805e53e0b8870801cb72a42656f
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
<<<<<<< HEAD

                    }
                    if (mineBoard.getBoard().get(i+(boardSize*j)).isRevealed())
                    {
                      graphics.setColor(Color.white);
                      graphics.fillRect(i * 90, j * 90 + (12 * spacing),90-spacing,90-spacing );
                      if(mineBoard.getBoard().get(i+(boardSize*j)).getNumMineNeighbors()>0)
                      {
                        graphics.setColor(Color.black);
                        mineNums=Integer.toString(mineBoard.getBoard().get(i+(boardSize*j)).getNumMineNeighbors());
                        fontW=(int)(fontData.stringWidth(mineNums)/2);
                        fontH=(int)(fontData.getHeight()/8);
                        graphics.drawString(mineNums, i* 90+45-fontW, j* 90 + (12 * spacing)+45+fontH);
                      }
                    }
                    else
                    {
                      graphics.fillRect(i * 90, j * 90 + (12 * spacing),90-spacing,90-spacing );
                    }
=======
                    }
                    graphics.fillRect(i * 90, j * 90 + (12 * spacing), 90 - spacing, 90 - spacing);
>>>>>>> b66fb0d383b45805e53e0b8870801cb72a42656f
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
<<<<<<< HEAD
                gameOver=false;
              }
              //checks if clicked tile has mine neighbors
              else if (mineBoard.getBoard().get(tileID).getNumMineNeighbors()>0)
              {
                System.out.println("there are " + mineBoard.getBoard().get(tileID).getNumMineNeighbors() + " mine neighbors");
                mineBoard.getBoard().get(tileID).setRevealed(true);
              }
              else
              {
                System.out.println("there are no mine neighbors!");
                mineBoard.getBoard().get(tileID).setRevealed(true);
              }
            }
            catch(Exception e)
            {
              System.out.println("");
=======
                gameOver = true;

>>>>>>> b66fb0d383b45805e53e0b8870801cb72a42656f
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
