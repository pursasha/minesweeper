public class Minesweeper implements Runnable {
    public int boardSize;
    GUI gui;

    public static void main(String[] args){

        //loads splash and menu
        Menu menu = new Menu();
        menu.showFrame();
    }

   public Minesweeper(int boardSize) {
       this.boardSize = boardSize;
       gui = new GUI(boardSize);
    }

    @Override
    public void run(){
        while(true){
            gui.repaint();
        }
    }


}
