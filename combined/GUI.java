import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

public class GUI extends JFrame implements ActionListener {

    int boardSize = 10;

    Board mineBoard;
    int height;
    int width;
    int boardX, boardY, tileID = 0;
    int mx, my;
    //0 for normal, 1 for color colorsweeper
    int gameMode = 0;
    int difficulty = 0;
    int tileSize;
    int spacer = 3;
    boolean gameOver = false;
    int topInset;
    int leftInset;
    int mineprob, mineRad;
    FBoard guiBoard;

    boolean restart = false;

    private int easySize = 10,
            mediumSize = 20,
            hardSize = 30,
            easyProbability = 5,
            mediumProbability = 4,
            hardProbability = 3;

    // Creating a window
    public GUI(int newSize, int mine_probability, int mineRadius, int gameMode) {
        // constructor when passing in probablility and size

        this.gameMode = gameMode;

        boardSize = newSize;

        this.difficulty = newSize;

        mineprob = mine_probability;

        mineRad = mineRadius;

        tileSize = (int) ((25.0 / boardSize * 20.0));
        spacer = (int) (2 * (20 / boardSize));
        if (spacer < 1) {
            spacer = 1;
        }
        System.out.println("tileSize: " + tileSize + " - spacer: " + spacer);
        mineBoard = new Board(boardSize);
        height = (tileSize) * boardSize + 2 * spacer + 100;
        width = (tileSize) * (boardSize) + 2 * spacer;
        System.out.println("height: " + height + " - Width: " + width);


        mineBoard.setSquareBoard(boardSize, mineprob, mineRad);
        this.setTitle("Minesweeper"); // sets title to window
        this.setSize(width, height); // sets size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes sure program is terminated when exited
        this.setResizable(false); // set it so no one can resize the window
        this.setLocationRelativeTo(null);

        guiBoard = new FBoard();
        this.setContentPane(guiBoard);// sets content panel
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

        this.setJMenuBar(menuBar);

        leftInset = this.getInsets().left;
        topInset = this.getInsets().top;
        topInset += menuBar.getHeight();
        topInset += 23;

    }

    public GUI(int difficulty, int gameMode, int mineRadius) {
        // constructor for passing in just difficulty, gamemode, and radius

        //private int easySize = 10, mediumSize = 20, hardSize = 30, easyProbability = 5, mediumProbability = 4, hardProbability = 3;
        switch (difficulty) {
            case 10:
                boardSize = easySize;
                mineprob = easyProbability;
                break;
            case 20:
                boardSize = mediumSize;
                mineprob = mediumProbability;
                break;
            case 30:
                boardSize = hardSize;
                mineprob = hardProbability;
                break;
            default:
                boardSize = mediumSize;
                mineprob = mediumProbability;
                break;
        }

        this.gameMode = gameMode;
        mineRad = mineRadius;


        tileSize = (int) ((25.0 / boardSize * 20.0));
        spacer = (int) (2 * (20 / boardSize));
        if (spacer < 1) {
            spacer = 1;
        }
        System.out.println("tileSize: " + tileSize + " - spacer: " + spacer);
		mineBoard = new Board(boardSize);
        height = (tileSize) * boardSize + 2 * spacer + 120;
        width = (tileSize) * (boardSize+1) + 2 * spacer + 2;
        System.out.println("height: " + height + " - Width: " + width);


        mineBoard.setSquareBoard(boardSize, mineprob, mineRad);
        this.setTitle("Minesweeper"); // sets title to window
        this.setSize(width, height); // sets size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes sure program is terminated when exited
        this.setResizable(false); // set it so no one can resize the window
        this.setLocationRelativeTo(null);

        guiBoard = new FBoard();
        this.setContentPane(guiBoard);// sets content panel
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

        this.setJMenuBar(menuBar);

        leftInset = this.getInsets().left;
        topInset = this.getInsets().top;
        topInset += menuBar.getHeight();
        topInset += 23;

    }

    public GUI(GUI copyGUI) {
        //copy cunstructor, note, does not copy over same board structure
        this(copyGUI.boardSize, copyGUI.mineprob, copyGUI.gameMode, copyGUI.mineRad);

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
            System.out.println(difficulty);
            if (this.difficulty == 10) {
                this.setVisible(false);
                new Thread(new Minesweeper(easySize, easyProbability, 1, 0)).start();
            } else if (this.difficulty == 20 && gameMode == 0) {
                this.setVisible(false);
                new Thread(new Minesweeper(mediumSize, mediumProbability, 1, 0)).start();
            } else if (this.difficulty == 30) {
                this.setVisible(false);
                new Thread(new Minesweeper(hardSize, hardProbability, 1, 0)).start();
            } else if (this.difficulty == 20 && gameMode == 1) {
                this.setVisible(false);
                new Thread(new Minesweeper(mediumSize, mediumProbability, 1, 1)).start();
            } else if (this.difficulty == 20 && gameMode == 2) {
                this.setVisible(false);
                new Thread(new Minesweeper(mediumSize, mediumProbability, 1, 2)).start();
            }else if(this.difficulty != 20 || this.difficulty != 30 || this.difficulty != 40){
                this.setVisible(false);
                CustomScreen cs = new CustomScreen();
                cs.showFrame();
            } else if (cmd.equals("Instructions")) {
                System.out.println("Help -> Instructions");
                try
                {
                    Desktop gamePlay=Desktop.getDesktop();
                    gamePlay.browse(new URI("https://web.cs.sunyit.edu/~schneieh/images/Game%20play%20instructions.pdf"));

                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            } else if (cmd.equals("Credits")) {
                System.out.println("About -> Credits");
                this.setVisible(false);
                Credits credit = new Credits();
                credit.showCredits();
            }
        }
    }



    public class FBoard extends JPanel {

        boolean testBoard = true;

        int fontW, fontH;
        String mineNums;
        int fontsize = 48;
        ImageLibrary imageSet = new ImageLibrary();
        Font mineFont = new Font("Courier", Font.PLAIN, fontsize);

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

                restart = true;


            } else if (mineBoard.winner()) {
                graphics.setColor(Color.lightGray);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(Color.black);
                fontW = (int) (fontData.stringWidth("YOU WIN!") / 2);
                graphics.drawString("YOU WIN!", width / 2 - fontW, 42);

                restart = true;
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
            if (mx >= (i * tileSize) + 2 * spacing + leftInset &&
                    mx < ((i + 1) * tileSize) + 2 * spacing + leftInset &&
                    my >= ((j * tileSize) + 2 * spacing + 52 + topInset) &&
                    my < ((j + 1) * tileSize) + 2 * spacing + 52 + topInset) {
                // this gets the corresponding tile coordinates
                boardX = i;
                boardY = j;
                tileID = boardSize * boardX + boardY;
                // colors tile red it it is a mine
                graphics.setColor(new Color(118, 147, 193));
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
                } else {
                    int mineInt = mineBoard.getBoard().get((i * boardSize) + j).getNumMineNeighbors();
                    if (mineInt > imageSet.getMaxMineNum()) {
                        graphics.setColor(Color.black);
                        graphics.setFont(mineFont.deriveFont((float) (440 / boardSize)));
                        graphics.drawString(mineInt + "", blockX, blockY + tileSize - spacing);
                        graphics.setFont(mineFont);
                        graphics.setColor(Color.white);
                    } else {
                        graphics.drawImage(imageSet.getImage(mineInt), blockX, blockY, tileSize - spacing, tileSize - spacing, this);
                    }

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
                    if (neighbor.getColor().getRed() > 0) {
                        r += neighbor.getColor().getRed();
                        rnum++;
                    }
                    if (neighbor.getColor().getGreen() > 0) {
                        g += neighbor.getColor().getGreen();
                        gnum++;
                    }
                    if (neighbor.getColor().getBlue() > 0) {
                        b += neighbor.getColor().getBlue();
                        bnum++;
                    }
                    num++;
                }
            }

            //fixes issue of possibly dividing by zero
            if (rnum == 0) {
                rnum = 1;
            }
            if (gnum == 0) {
                gnum = 1;
            }
            if (bnum == 0) {
                bnum = 1;
            }

            r = ((int) (r / rnum));
            g = ((int) (g / gnum));
            b = ((int) (b / bnum));

            if ((r == 255) && (g == 255) && (b == 255)) {
                r = (int) (r / (rnum));
                g = (int) (g / (gnum));
                b = (int) (b / (bnum));
            }
            //Color thisColor = new Color(r, g, b);
            if (r == 255 && b == 0 && g == 0 && tileContext.getNumMineNeighbors() > 1) {
                b = (int) (256 / ((mineRad * 2 + 1) * (mineRad * 2 + 1) - 1) * (tileContext.getNumMineNeighbors() - 1));
                g = (int) (256 / ((mineRad * 2 + 1) * (mineRad * 2 + 1) - 1) * (tileContext.getNumMineNeighbors() - 1));
            }
            if (g == 255 && b == 0 && r == 0 && tileContext.getNumMineNeighbors() > 1) {
                b = (int) (256 / ((mineRad * 2 + 1) * (mineRad * 2 + 1) - 1) * (tileContext.getNumMineNeighbors() - 1));
                r = (int) (256 / ((mineRad * 2 + 1) * (mineRad * 2 + 1) - 1) * (tileContext.getNumMineNeighbors() - 1));
            }
            if (b == 255 && r == 0 && g == 0 && tileContext.getNumMineNeighbors() > 1) {
                r = (int) (256 / ((mineRad * 2 + 1) * (mineRad * 2 + 1) - 1) * (tileContext.getNumMineNeighbors() - 1));
                g = (int) (256 / ((mineRad * 2 + 1) * (mineRad * 2 + 1) - 1) * (tileContext.getNumMineNeighbors() - 1));
            }

            return new Color(255 - r, 255 - g, 255 - b);
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
                        if (gameMode == 2) {
                            gameOver = mineBoard.memReveal(tileID);
                        } else {
                            gameOver = mineBoard.reveal(tileID);
                        }
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
