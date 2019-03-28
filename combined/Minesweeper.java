public class Minesweeper implements Runnable {
    public int boardSize;
    GUI gui;

    public static void main(String[] args){

        //loads splash and menu
        Menu menu = new Menu();
        menu.showFrame();
    }

    public Minesweeper(int boardSize, int gameMode) {
        this.boardSize = boardSize;
        gui = new GUI(boardSize, gameMode);
    }

    @Override
    public void run(){
        while(true){
            gui.repaint();
        }
    }
}
