import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Integer;


public class GUI extends JFrame {

    int boardSize = 5;

    Board mineBoard = new Board();
    int height = (90)*boardSize+30+12 + 62;
    int width = (90)*boardSize+20;
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

		boolean testBoard = true;

		int fontW, fontH;
		String mineNums;
		int fontsize = 48;

        //sets background color
        public void paintComponent(Graphics graphics){
			// sets up the font
            Font mineFont = new Font("TimesRoman", Font.PLAIN, fontsize);
            FontMetrics fontData = graphics.getFontMetrics(mineFont);
            graphics.setFont(mineFont);

			topDisp(graphics, fontData);

			//sets grid
			int spacing = boardSize;
			int i, j;
			int blockX, blockY;
			for (i = 0; i < boardSize; i++) {
				for (j = 0; j < boardSize; j++) {

					blockX = i * 90 + spacing;
					blockY = j * 90 + spacing + 62;


					isMouseHere(i, j, spacing, graphics);
					if (gameOver == true || mineBoard.getRemainingTiles() == true)
					{
						dispTileBox(graphics, blockX, blockY, spacing, i, j);
						displayTileText(graphics, fontData, i, j, blockX, blockY);
					}
					else if (mineBoard.getBoard().get((i*boardSize)+j).isRevealed())
					{
						dispTileBox(graphics, blockX, blockY, spacing, i, j);
						displayTileText(graphics, fontData, i, j, blockX, blockY);
					}
					else
					{
						graphics.setColor(Color.lightGray);
						graphics.fillRect(blockX, blockY, 90-spacing,90-spacing );
						if (testBoard)
						{
							displayTileText(graphics, fontData, i, j, blockX, blockY);
						}
					}
				}
			}
        }
		public void topDisp(Graphics graphics, FontMetrics fontData)
		//sets the top display to the desired message
		{
			if (gameOver) {
                graphics.setColor(Color.black);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(Color.white);
                fontW=(int)(fontData.stringWidth("GAME OVER")/2);
                graphics.drawString("GAME OVER", width/2 - fontW, 54);
              }
            else if (mineBoard.getRemainingTiles())
            {
                graphics.setColor(Color.GREEN);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(Color.black);
                fontW=(int)(fontData.stringWidth("YOU WIN!")/2);
                graphics.drawString("YOU WIN!", width/2 - fontW, 54);
            }
            else
            {
              	graphics.setColor(Color.gray);
              	graphics.fillRect(0, 0, width, height);
				graphics.setColor(Color.black);
                fontW=(int)(fontData.stringWidth("Minesweeper")/2);
                graphics.drawString("Minesweeper!", width/2 - fontW, 54);
            }
		}
		public void isMouseHere(int i, int j, int spacing, Graphics graphics)
		// functionized version of the mouse location test
		{
			if(mx>=(i * 90)+spacing+8 && mx< ((i+1)*90)+spacing+8 && my>=((j*90)+spacing+31+62) && my< ((j+1)*90)+spacing+31+62)
			{
			  //this gets the corresponding tile coordinates
			  boardX = i;
			  boardY = j;
			  tileID=boardSize*boardX+boardY;
			  //colors tile red it it is a mine
			  graphics.setColor(Color.blue);
			}
		}
		public void dispTileBox(Graphics graphics, int blockX, int blockY, int spacing, int i, int j)
		// use to display revealed tiles
		{
			if (mineBoard.getBoard().get((i*boardSize)+j).getMine())
			{
				graphics.setColor(Color.red);
			}
			else
			{
				graphics.setColor(Color.white);
			}
			graphics.fillRect(blockX, blockY, 90-spacing,90-spacing );
		}
		public void displayTileText(Graphics graphics, FontMetrics fontData, int i, int j, int blockX, int blockY)
		// displays the text for the tile
		{
			graphics.setColor(Color.black);
			if (mineBoard.getBoard().get((i*boardSize)+j).getMine())
			{
				mineNums="M";
			}
			else if (mineBoard.getBoard().get((i*boardSize)+j).getNumMineNeighbors() > 0)
			{
				mineNums=Integer.toString(mineBoard.getBoard().get((i*boardSize)+j).getNumMineNeighbors());
			}
			else
			{
				mineNums=" ";
			}
			fontW=(int)(fontData.stringWidth(mineNums)/2);
			fontH=(int)(fontData.getHeight()/8);
			graphics.drawString(mineNums, blockX + 45 - fontW, blockY + 45 + fontH);
		}
    }

    //sets display for mouse movement and coordinates
    public class Move implements MouseMotionListener {

        public void mouseDragged(MouseEvent a) {

        }

        public void mouseMoved(MouseEvent a) {
            //System.out.println("the mouse was moved");
            mx = a.getX();
            my = a.getY();
            //System.out.println("x:" + mx + " y:" + my);

            //System.out.println("board X:" + boardX + " board y:" + boardY);
        }

    }

    //sets display for mouse click and coordinates
        public class Click implements MouseListener {
            public void mouseClicked(MouseEvent a) {
		try{
                System.out.println("the mouse was clicked");
		System.out.println("X: " + boardX + " Y: "+ boardY);
		gameOver = mineBoard.reveal(tileID);
		}
		catch(Exception e){
		System.out.println("Error caught " +e);
		}               
 /*try{
                //checks if the clicked tile is a mine
                if (mineBoard.getBoard().get(tileID).getMine()) {
                    System.out.println("the tile was a mine!");
                    gameOver=true;
                }
                //checks if clicked tile has mine neighbors
                else if (mineBoard.getBoard().get(tileID).getNumMineNeighbors()>0 && !mineBoard.getBoard().get(tileID).isRevealed())
                {
                    System.out.println("there are " + mineBoard.getBoard().get(tileID).getNumMineNeighbors() + " mine neighbors");
                    mineBoard.getBoard().get(tileID).setRevealed(true);
                    mineBoard.setRemainingTiles();
                }
                else if  (!mineBoard.getBoard().get(tileID).isRevealed())
                {
                    System.out.println("there are no mine neighbors!");
                    mineBoard.getBoard().get(tileID).setRevealed(true);
                    mineBoard.setRemainingTiles();
                }
                else {
                  System.out.println("the tile is already revelaed!");
                }
            }
            catch(Exception e)
            {
              System.out.println("error caughts:  " + e);
            }*/
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
