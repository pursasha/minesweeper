public class Minesweeper implements Runnable {
    GUI gui = new GUI();

    public static void main(String[] args){

        //loads splash and menu
        Menu menu = new Menu();
        menu.showFrame();
    }
    @Override
    public void run(){
        while(true){
            gui.repaint();
        }
    }

}
