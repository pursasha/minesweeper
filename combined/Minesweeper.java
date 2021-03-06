public class Minesweeper implements Runnable {
    public int boardSize;
    GUI gui;

    public static void main(String[] args){

        //loads splash and menu
        Menu menu = new Menu();
        menu.showSplash();
    }

    public Minesweeper(int boardSize, int mine_probability, int mineRadius,int gameMode) {
        this.boardSize = boardSize;
        gui = new GUI(boardSize, mine_probability, mineRadius, gameMode);
    }
    public Minesweeper(int difficulty, int mineRadius, int gameMode) {
        switch (difficulty)
        {
            case 0:
                boardSize = 10;
                break;
            case 1:
                boardSize = 20;
                break;
            case 2:
                boardSize = 30;
                break;
            default:
                boardSize = 20;
                break;
        }
        gui = new GUI(difficulty, gameMode, mineRadius);
    }

    @Override
    public void run(){
        while(!gui.restart){
            gui.repaint();
        }
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
        }
    }
}
