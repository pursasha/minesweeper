import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {


    int boardSize = 10;

    Board mineBoard;
    int height;
    int width;
    int boardX, boardY, tileID = 0;
    int mx, my;
    //0 for normal, 1 for color colorsweeper
    int gameMode = 0;
    int tileSize;
    int spacer = 3;
    boolean gameOver = false;
	int topInset;
	int leftInset;


    // Creating a window
    public GUI(int newSize, int mine_probability, int mineRadius, int gameMode) {

        this.gameMode = gameMode;

        boardSize = newSize;

        tileSize = (int) ((30.0 / boardSize * 20.0));
        spacer = (int) (2 * (20 / boardSize));
        if (spacer < 1) {
            spacer = 1;
        }
        System.out.println("tileSize: " + tileSize + " - spacer: " + spacer);
        mineBoard = new Board(boardSize);
        height = (tileSize) * boardSize + 2 * spacer + 90;
        width = (tileSize) * boardSize + 2 * spacer + 3;
        System.out.println("height: " + height + " - Width: " + width);


        mineBoard.setSquareBoard(boardSize, mine_probability, mineRadius);
        this.setTitle("Minesweeper"); // sets title to window
        this.setSize(width, height); // sets size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes sure program is terminated when exited
        this.setResizable(false); // set it so no one can resize the window
        this.setLocationRelativeTo(null);

        FBoard GUIboard = new FBoard();
        this.setContentPane(GUIboard);// sets content panel
        this.setVisible(true); // makes window visible

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);

        //add the menuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        menuBar.add(menu);
        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener(this);
        menu.add(restart);
        JMenuItem menuReturn = new JMenuItem("Return");
        menuReturn.addActionListener(this);
        menu.add(menuReturn);
        JMenuItem menuExit = new JMenuItem("Exit");
        menuExit.addActionListener(this);
        menu.add(menuExit);

        JMenu help = new JMenu("Help");
        menuBar.add(help);
        JMenuItem instructions = new JMenuItem("Instructions");
        instructions.addActionListener(this);
        help.add(instructions);

        JMenu about = new JMenu("About");
        menuBar.add(about);
        JMenuItem credits = new JMenuItem("Credits");
        credits.addActionListener(this);
        about.add(credits);

		leftInset = this.getInsets().left;
		topInset = this.getInsets().top;

        this.setJMenuBar(menuBar);
    }

    public GUI() {

        boardSize = 10;

        mineBoard = new Board(boardSize);
        height = (tileSize + 3) * boardSize + 30 + 55;
        width = (tileSize + 3) * boardSize;

        //mineBoard.setSquareBoard();
        this.setTitle("Minesweeper"); // sets title to window
        this.setSize(width, height); // sets size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes sure program is terminated when exited
        this.setResizable(false); // set it so no one can resize the window
        setLocationRelativeTo(null);

        FBoard GUIboard = new FBoard();
        this.setContentPane(GUIboard);// sets content panel
        this.setVisible(true); // makes window visible

        Move move = new Move();
        GUIboard.addMouseMotionListener(move);

        Click click = new Click();
        GUIboard.addMouseListener(click);

    }

    // for buttons and menus
    public void actionPerformed(ActionEvent e) {
        Object cmd = e.getActionCommand();
        if (cmd.equals("Exit")) {
            System.out.println("Game -> Exit");
            System.exit(0);
        } else if (cmd.equals("Return")) {
            System.out.println("Game -> Menu");
            this.setVisible(false);
            Menu menu = new Menu();
            menu.showFrame();
        } else if (cmd.equals("Restart")) {
            System.out.println("Game -> Restart");
            //restart function here
        } else if (cmd.equals("Instructions")) {
            System.out.println("Help -> Instructions");
            //instructions here

        } else if (cmd.equals("Credits")) {
            System.out.println("About -> Credits");
            this.setVisible(false);
            Credits credit = new Credits();
            credit.showCredits();

        }
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
            Font mineFont = new Font("Courier", Font.PLAIN, fontsize);
            FontMetrics fontData = graphics.getFontMetrics(mineFont);
            graphics.setFont(mineFont);

            topDisp(graphics, fontData);

            // sets grid
            int spacing = spacer;
            int i, j;
            int blockX, blockY;
            for (i = 0; i < boardSize; i++) {
                for (j = 0; j < boardSize; j++) {

                    blockX = i * tileSize + spacing;
                    blockY = j * tileSize + spacing + 55;

                    graphics.setColor(Color.lightGray);
                    isMouseHere(i, j, spacing, graphics);
                    if (gameOver == true || mineBoard.getRemainingTiles() == true) {
                        dispTileBox(graphics, blockX, blockY, spacing, i, j);
                        displayTileText(graphics, fontData, i, j, blockX, blockY, spacing);
                    } else if (mineBoard.getBoard().get((i * boardSize) + j).isRevealed()) {
                        dispTileBox(graphics, blockX, blockY, spacing, i, j);
                        displayTileText(graphics, fontData, i, j, blockX, blockY, spacing);
                    } else if (mineBoard.getBoard().get((i * boardSize) + j).isFlag()) {
                        dispTileBox(graphics, blockX, blockY, spacing, i, j);
                        displayTileText(graphics, fontData, i, j, blockX, blockY, spacing);
                    } else {

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
                graphics.setColor(new Color(114, 159, 180));
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(Color.black);
                fontW = (int) (fontData.stringWidth("Minesweeper") / 2);
                graphics.drawString("Minesweeper", width / 2 - fontW, 42);
            }
        }

        private void isMouseHere(int i, int j, int spacing, Graphics graphics)
        // functionized version of the mouse location test
        {
            if (mx >= (i * tileSize) + 2*spacing + leftInset &&
			    mx <  ((i + 1) * tileSize) + 2*spacing + leftInset &&
				my >= ((j * tileSize) + 2*spacing + 52 + topInset ) &&
				my <  ((j + 1) * tileSize) + 2*spacing + 52 + topInset ) {
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
            if (mineBoard.getBoard().get((i * boardSize) + j).getMine()) {
                graphics.drawImage(imageSet.getImage(9), blockX, blockY, tileSize - spacing, tileSize - spacing, this);
            }
            // else if tile is not mine, displays number or color if on color mode
            else if (mineBoard.getBoard().get((i * boardSize) + j).getNumMineNeighbors() > 0) {
                if (gameMode == 1) {
                    graphics.setColor(getTileColor(i, j));
                    graphics.fillRect(blockX, blockY, tileSize - spacing, tileSize - spacing);
                } else if (gameMode == 0) {
                    int mineInt = mineBoard.getBoard().get((i * boardSize) + j).getNumMineNeighbors();
                    graphics.drawImage(imageSet.getImage(mineInt), blockX, blockY, tileSize - spacing, tileSize - spacing, this);
                }
            }
            // if the tile is a flag and the game is not over, the flag is displayed
            if (mineBoard.getBoard().get((i * boardSize) + j).isFlag() && !(gameOver == true || mineBoard.getRemainingTiles() == true)) {
                int mineInt = mineBoard.getBoard().get((i * boardSize) + j).getNumMineNeighbors();
                graphics.drawImage(imageSet.getImage(10), blockX, blockY, tileSize - spacing, tileSize - spacing, this);
            }
        }

        private Color getTileColor(int x, int y)
        // gets the color of a non-mine tile
        {
            Tile tileContext = mineBoard.getBoard().get((x * boardSize) + y);
            //store for the created colors
            int num = 0, bnum = 0, gnum = 0, rnum = 0;
            int r = 0;
            int g = 0;
            int b = 0;
            // get the colors of the nearby mines
            for (Tile neighbor : tileContext.neighbors) {
                if (neighbor.getMine()) {
					if (neighbor.getColor().getRed() > 0)
					{
						r += neighbor.getColor().getRed();
						rnum++;
					}
					if (neighbor.getColor().getGreen() > 0)
					{
                    	g += neighbor.getColor().getGreen();
						gnum++;
					}
					if (neighbor.getColor().getBlue() > 0)
					{
                    	b += neighbor.getColor().getBlue();
						bnum++;
					}
                    num++;
                }
            }

			//fixes issue of possibly dividing by zero
			if (rnum == 0) {rnum = 1;}
			if (gnum == 0) {gnum = 1;}
			if (bnum == 0) {bnum = 1;}

			r = ((int) (r / rnum));
			g = ((int) (g / gnum));
			b = ((int) (b / bnum));

			if ((r == 255) && (g == 255) && (b == 255))
			{
				r = (int)(r/(8*num));
				g = (int)(g/(8*num));
				b = (int)(b/(8*num));
			}
			//Color thisColor = new Color(r, g, b);
			if (r == 255 && b == 0 && g == 0 && tileContext.getNumMineNeighbors() > 1)
			{
				b = 32*(tileContext.getNumMineNeighbors() - 1);
				g = 32*(tileContext.getNumMineNeighbors() - 1);
			}
			if (g == 255 && b == 0 && r == 0 && tileContext.getNumMineNeighbors() > 1)
			{
				b = 32*(tileContext.getNumMineNeighbors() - 1);
				r = 32*(tileContext.getNumMineNeighbors() - 1);
			}
			if (b == 255 && r == 0 && g == 0 && tileContext.getNumMineNeighbors() > 1)
			{
				r = 32*(tileContext.getNumMineNeighbors() - 1);
				g = 32*(tileContext.getNumMineNeighbors() - 1);
			}

            return new Color( 255 - r, 255 - g, 255 - b);
        }
    }

    // sets display for mouse movement and coordinates
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

    // sets display for mouse click and coordinates
    public class Click implements MouseListener {
        public void mouseClicked(MouseEvent a) {
            try {
                System.out.println("the mouse was clicked");
                System.out.println("X: " + boardX + " Y: " + boardY);
                System.out.println("x:" + mx + " y:" + my);
                if (SwingUtilities.isRightMouseButton(a)) {
                    System.out.println("Right Click");
                    if (mineBoard.getBoard().get(tileID).isFlag() && !mineBoard.getBoard().get(tileID).isRevealed()) {
                        mineBoard.getBoard().get(tileID).setFlag(false);

                    } else if (!mineBoard.getBoard().get(tileID).isRevealed()) {
                        mineBoard.getBoard().get(tileID).setFlag(true);
                    }
                } else {
                    if (!(mineBoard.getBoard().get(tileID).isFlag() || mineBoard.getBoard().get(tileID).isRevealed())) {
                        gameOver = mineBoard.reveal(tileID);
                    }
                }

            } catch (Exception e) {
                System.out.println("Error caught " + e);
            }
        }

        public void mouseEntered(MouseEvent a) {
            //System.out.println("Entered x:" + mx + " y:" + my);
        }

        public void mouseExited(MouseEvent a) {
            //System.out.println("Exited x:" + mx + " y:" + my);
        }

        public void mousePressed(MouseEvent a) {

        }

        public void mouseReleased(MouseEvent a) {

        }

    }
}
