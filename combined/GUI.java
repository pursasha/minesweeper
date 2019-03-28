
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class GUI extends JFrame {

    int boardSize = 10;

    Board mineBoard;
    int height;
    int width;
    int boardX, boardY, tileID = 0;
    int mx, my;
    //0 for normal, 1 for color colorsweeper
    int gameMode = 0;
    int tileSize = 40;

    boolean gameOver = false;

    // Creating a window
    public GUI(int newSize, int gameMode) {

        this.gameMode = gameMode;

        boardSize = newSize;

        tileSize = 20;
        mineBoard = new Board(boardSize);
        height = (tileSize) * boardSize + 30 + 12 + 62;
        width = (tileSize) * boardSize + 20;


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
    public GUI() {

        boardSize = 10;

        mineBoard = new Board(boardSize);
        height = (tileSize) * boardSize + 30 + 12 + 55;
        width = (tileSize) * boardSize + 20;

        mineBoard.setSquareBoard();
        this.setTitle("Minesweeper"); // sets title to window
        this.setSize(width, height); // sets size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes sure program is terminated when exited
        this.setResizable(false); // set it so no one can resize the window
        setLocationRelativeTo(null);

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
        int fontsize = 48*boardSize/25;
        ImageLibrary imageSet = new ImageLibrary();

        // sets background color
        public void paintComponent(Graphics graphics) {
            // sets up the font
            Font mineFont = new Font("TimesRoman", Font.PLAIN, fontsize);
            FontMetrics fontData = graphics.getFontMetrics(mineFont);
            graphics.setFont(mineFont);

            topDisp(graphics, fontData);

            // sets grid
            int spacing = 3;
            int i, j;
            int blockX, blockY;
            for (i = 0; i < boardSize; i++) {
                for (j = 0; j < boardSize; j++) {

                    blockX = i * tileSize + spacing;
                    blockY = j * tileSize + spacing + 55;

                    graphics.setColor(Color.lightGray);
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

                        graphics.fillRect(blockX, blockY, tileSize - spacing, tileSize - spacing);
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
                graphics.drawString("GAME OVER", width / 2 - fontW, 42);
            } else if (mineBoard.winner()) {
                graphics.setColor(Color.lightGray);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(Color.black);
                fontW = (int) (fontData.stringWidth("YOU WIN!") / 2);
                graphics.drawString("YOU WIN!", width / 2 - fontW, 42);
            } else {
                graphics.setColor(Color.gray);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(Color.black);
                fontW = (int) (fontData.stringWidth("Minesweeper") / 2);
                graphics.drawString("Minesweeper!", width / 2 - fontW, 42);
            }
        }

        private void isMouseHere(int i, int j, int spacing, Graphics graphics)
        // functionized version of the mouse location test
        {
            if (mx >= (i * tileSize) + spacing + 3 && mx < ((i + 1) * tileSize) + spacing + 3
                    && my >= ((j * tileSize) + spacing + 30 + 55) && my < ((j + 1) * tileSize) + spacing + 30 + 55) {
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
            graphics.fillRect(blockX, blockY, tileSize - spacing, tileSize - spacing);
        }

        private void displayTileText(Graphics graphics, FontMetrics fontData, int i, int j, int blockX, int blockY, int spacing)
        // displays the text for the tile
        {

            graphics.setColor(Color.white);
            // if tile is mine, displays the mine image
            if (mineBoard.getBoard().get((i*boardSize)+j).getMine())
            {
                graphics.drawImage(imageSet.getImage(9), blockX, blockY, tileSize-spacing, tileSize-spacing, this);
            }
            // else if tile is not mine, displays number or color if on color mode
            else if (mineBoard.getBoard().get((i*boardSize)+j).getNumMineNeighbors() > 0)
            {
                if(gameMode == 1)
                {
                    graphics.setColor(getTileColor(i, j, graphics));
                    graphics.fillRect(blockX, blockY, tileSize - spacing, tileSize - spacing);
                }
                else if (gameMode == 0)
                {
                    int mineInt = mineBoard.getBoard().get((i*boardSize)+j).getNumMineNeighbors();
                    graphics.drawImage(imageSet.getImage(mineInt), blockX, blockY, tileSize-spacing, tileSize-spacing, this);
                }
            }
            // if the tile is a flag and the game is not over, the flag is displayed
            if (mineBoard.getBoard().get((i*boardSize)+j).isFlag() && !(gameOver == true || mineBoard.getRemainingTiles() == true))
            {
                int mineInt = mineBoard.getBoard().get((i*boardSize)+j).getNumMineNeighbors();
                graphics.drawImage(imageSet.getImage(10), blockX, blockY, tileSize-spacing, tileSize-spacing, this);
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
                    r += neighbor.getColor().getRed();
                    g += neighbor.getColor().getGreen();
                    b += neighbor.getColor().getBlue();
                    num++;
                }
            }

            return new Color((int)(r/num), (int)(g/num), (int)(b/num));
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
            //System.out.println("x:" + mx + " y:" + my);

            //System.out.println("board X:" + boardX + " board y:" + boardY);
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

    // for buttons and menus
    public void actionPerformed(ActionEvent e) {
        Object cmd = e.getActionCommand();
        if (cmd.equals("Exit")) {
            System.out.println("File -> Exit");
            System.exit(0);
        }
        else {
            System.out.println("Unknown action");
        }
    }

}
