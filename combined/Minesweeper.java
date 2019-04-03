public class Minesweeper implements Runnable {
    public int boardSize;
    GUI gui;

    public static void main(String[] args){

        //loads splash and menu
        Menu menu = new Menu();
        menu.showFrame();
    }

    public Minesweeper(int boardSize, int mine_probability, int mineRadius,int gameMode) {
        this.boardSize = boardSize;
        gui = new GUI(boardSize, mine_probability,mineRadius,gameMode);
    }

    @Override
    public void run(){
        while(true){
            gui.repaint();
        }
    }
}
