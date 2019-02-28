import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Integer;


public class GUI extends JFrame {

    int boardSize = 5;

    Board mineBoard = new Board();
    int height = (532/5)*boardSize;
    int width = (450/5)*boardSize;
    int boardX, boardY, tileID = 0;
    int mx, my;

    boolean gameOver=false;

    //Creating a window
    public GUI() {
        mineBoard.setSquareBoard();
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
        public void paintComponent(Graphics graphics){
            int fontsize = 48;
            Font mineFont = new Font("TimesRoman", Font.PLAIN, fontsize);
            FontMetrics fontData = graphics.getFontMetrics(mineFont);
            int fontW, fontH;
            String mineNums;
            graphics.setFont(mineFont);
              //sets screen black if mine is clicked
            if (gameOver) {
                graphics.setColor(Color.black);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(Color.white);
                fontW=(int)(fontData.stringWidth("GAME OVER")/2);
                graphics.drawString("GAME OVER", width/2 - fontW, height/2);
              }
            else
            {
              graphics.setColor(Color.gray);
              graphics.fillRect(0, 0, width, height);
              //sets grid
              int spacing = 5;
              int i, j;

              for (i = 0; i < 5; i++) {
                  for (j = 0; j < 5; j++) {

                      graphics.setColor(Color.lightGray);
                      if(mx>=(spacing+(i * 90)) && mx< (spacing+(i * 90)+90-spacing) && my>=((j*90)+90 + spacing+26) && my< ((j*90)+90  +26 +spacing +90-spacing) )
                      {
                        //this gets the corresponding tile coordinates
                        boardX = i;
                        boardY = j;
                        tileID=boardX+5*boardY;
                        //colors tile red it it is a mine
                        graphics.setColor(Color.blue);
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
                  }
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

            try{
            //checks if the clicked tile is a mine
            if (mineBoard.getBoard().get(tileID).getMine()) {
                System.out.println("the tile was a mine!");
                gameOver=true;
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
              System.out.println("error caughts:  " + e);
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
