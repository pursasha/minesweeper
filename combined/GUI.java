import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class GUI extends JFrame {

    int boardSize = 5;

    Board mineBoard = new Board();
    int height = (90) * boardSize + 30 + 12 + 62;
    int width = (90) * boardSize + 20;
    int boardX, boardY, tileID = 0;
    int mx, my;
	int gameMode = 0;
	//0 for normal, 1 for color colorsweeper

    boolean gameOver = false;

    // Creating a window
    public GUI() {
        mineBoard.setSquareBoard();
        this.setTitle("Minesweeper"); // sets title to window
        this.setSize(width, height); // sets size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes sure program is terminated when exited
        this.setResizable(false); // set it so no one can resize the window

        FBoard GUIboard = new FBoard();
        this.setContentPane(GUIboard);// sets content panel
        this.setVisible(true); // makes window visible

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
		ImageLibrary imageSet = new ImageLibrary();

        // sets background color
        public void paintComponent(Graphics graphics) {
            // sets up the font
            Font mineFont = new Font("TimesRoman", Font.PLAIN, fontsize);
            FontMetrics fontData = graphics.getFontMetrics(mineFont);
            graphics.setFont(mineFont);

            topDisp(graphics, fontData);

            // sets grid
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
                        displayTileText(graphics, fontData, i, j, blockX, blockY, spacing);
                    }
					else if (mineBoard.getBoard().get((i * boardSize) + j).isRevealed())
					{
                        dispTileBox(graphics, blockX, blockY, spacing, i, j);
                        displayTileText(graphics, fontData, i, j, blockX, blockY, spacing);
                    }
					else if (mineBoard.getBoard().get((i * boardSize) + j).isFlag())
					{
						dispTileBox(graphics, blockX, blockY, spacing, i, j);
                        displayTileText(graphics, fontData, i, j, blockX, blockY, spacing);
                    }
					else
					{
                        graphics.setColor(Color.lightGray);
						graphics.fillRect(blockX, blockY, 90 - spacing, 90 - spacing);
                    }
                //    if (testBoard) {
                //    	displayTileText(graphics, fontData, i, j, blockX, blockY);
                //    }
                }
            }
        }

        private void topDisp(Graphics graphics, FontMetrics fontData)
        // sets the top display to the desired message
        {
            if (gameOver) {
                graphics.setColor(Color.black);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(Color.white);
                fontW = (int) (fontData.stringWidth("GAME OVER") / 2);
                graphics.drawString("GAME OVER", width / 2 - fontW, 54);
            } else if (mineBoard.getRemainingTiles()) {
                graphics.setColor(Color.lightGray);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(Color.black);
                fontW = (int) (fontData.stringWidth("YOU WIN!") / 2);
                graphics.drawString("YOU WIN!", width / 2 - fontW, 54);
            } else {
                graphics.setColor(Color.gray);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(Color.black);
                fontW = (int) (fontData.stringWidth("Minesweeper") / 2);
                graphics.drawString("Minesweeper!", width / 2 - fontW, 54);
            }
        }

        private void isMouseHere(int i, int j, int spacing, Graphics graphics)
        // functionized version of the mouse location test
        {
            if (mx >= (i * 90) + spacing + 8 && mx < ((i + 1) * 90) + spacing + 8
                    && my >= ((j * 90) + spacing + 31 + 62) && my < ((j + 1) * 90) + spacing + 31 + 62) {
                // this gets the corresponding tile coordinates
                boardX = i;
                boardY = j;
                tileID = boardSize * boardX + boardY;
                // colors tile red it it is a mine
                graphics.setColor(Color.blue);
            }
        }

        private void dispTileBox(Graphics graphics, int blockX, int blockY, int spacing, int i, int j)
        // use to display revealed tiles
        {
            if (mineBoard.getBoard().get((i * boardSize) + j).getMine()) {
                graphics.setColor(Color.red);
            } else {
                graphics.setColor(Color.white);
            }
            graphics.fillRect(blockX, blockY, 90 - spacing, 90 - spacing);
        }

		private void displayTileText(Graphics graphics, FontMetrics fontData, int i, int j, int blockX, int blockY, int spacing)
		// displays the text for the tile
		{

			graphics.setColor(Color.white);
			// if tile is mine, displays the mine image
			if (mineBoard.getBoard().get((i*boardSize)+j).getMine())
			{
				graphics.drawImage(imageSet.getImage(9), blockX, blockY, 85, 85, this);
			}
			// else if tile is not mine, displays number or color if on color mode
			else if (mineBoard.getBoard().get((i*boardSize)+j).getNumMineNeighbors() > 0)
			{
				if(gameMode == 1)
				{
					graphics.setColor(getTileColor(i, j, graphics));
					graphics.fillRect(blockX, blockY, 90 - spacing, 90 - spacing);
				}
				else if (gameMode == 0)
				{
					int mineInt = mineBoard.getBoard().get((i*boardSize)+j).getNumMineNeighbors();
					graphics.drawImage(imageSet.getImage(mineInt), blockX, blockY, 85, 85, this);
				}
			}
			// if the tile is a flag and the game is not over, the flag is displayed
			if (mineBoard.getBoard().get((i*boardSize)+j).isFlag() && !(gameOver == true || mineBoard.getRemainingTiles() == true))
			{
				int mineInt = mineBoard.getBoard().get((i*boardSize)+j).getNumMineNeighbors();
				graphics.drawImage(imageSet.getImage(10), blockX, blockY, 85, 85, this);
			}
		}
		private Color getTileColor(int x, int y, Graphics graphics)
		// gets the color of a non-mine tile
		{
			Tile tileContext =mineBoard.getBoard().get((x*boardSize)+y);
			//store for the created colors
			int num = 0;
			int r = 0;
			int g = 0;
			int b = 0;
			// get the colors of the nearby mines
			for(Tile neighbor : tileContext.neighbors)
			{
				if( neighbor.getMine() )
				{
					r += neighbor.getColor().getRed()*neighbor.getColor().getRed();
					g += neighbor.getColor().getGreen()*neighbor.getColor().getGreen();
					b += neighbor.getColor().getBlue()*neighbor.getColor().getBlue();
					num++;
				}
			}

			return new Color((int)(Math.sqrt(r/num)), (int)(Math.sqrt(g/num)), (int)(Math.sqrt(b/num)));
		}
    }

    // sets display for mouse movement and coordinates
    public class Move implements MouseMotionListener {

        public void mouseDragged(MouseEvent a) {

        }

        public void mouseMoved(MouseEvent a) {
            // System.out.println("the mouse was moved");
            mx = a.getX();
            my = a.getY();
            // System.out.println("x:" + mx + " y:" + my);

            // System.out.println("board X:" + boardX + " board y:" + boardY);
        }

    }

    // sets display for mouse click and coordinates
    public class Click implements MouseListener {
        public void mouseClicked(MouseEvent a) {
            try {
                System.out.println("the mouse was clicked");
                System.out.println("X: " + boardX + " Y: " + boardY);
                if (SwingUtilities.isRightMouseButton(a)) {
                    System.out.println("Right Click");
                    if (mineBoard.getBoard().get(tileID).isFlag() && !mineBoard.getBoard().get(tileID).isRevealed()) {
                        mineBoard.getBoard().get(tileID).setFlag(false);
                    } else if (!mineBoard.getBoard().get(tileID).isRevealed()) {
                        mineBoard.getBoard().get(tileID).setFlag(true);
                    }
                } else {
					if (!(mineBoard.getBoard().get(tileID).isFlag() || mineBoard.getBoard().get(tileID).isRevealed()))
					{
                    	gameOver = mineBoard.reveal(tileID);
					}
                }
            } catch (Exception e) {
                System.out.println("Error caught " + e);
            }
            /*
             * try{ //checks if the clicked tile is a mine if
             * (mineBoard.getBoard().get(tileID).getMine()) {
             * System.out.println("the tile was a mine!"); gameOver=true; } //checks if
             * clicked tile has mine neighbors else if
             * (mineBoard.getBoard().get(tileID).getNumMineNeighbors()>0 &&
             * !mineBoard.getBoard().get(tileID).isRevealed()) {
             * System.out.println("there are " +
             * mineBoard.getBoard().get(tileID).getNumMineNeighbors() + " mine neighbors");
             * mineBoard.getBoard().get(tileID).setRevealed(true);
             * mineBoard.setRemainingTiles(); } else if
             * (!mineBoard.getBoard().get(tileID).isRevealed()) {
             * System.out.println("there are no mine neighbors!");
             * mineBoard.getBoard().get(tileID).setRevealed(true);
             * mineBoard.setRemainingTiles(); } else {
             * System.out.println("the tile is already revelaed!"); } } catch(Exception e) {
             * System.out.println("error caughts:  " + e); }
             */
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
