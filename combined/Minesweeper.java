public class Minesweeper implements Runnable {
    GUI gui = new GUI();

    public static void main(String[] args){
        TitleScreen screen = new TitleScreen(10000);
        screen.showScreen();

        Menu menu = new Menu();
        menu.showMenu();
    }
    @Override
    public void run(){
        while(true){
            gui.repaint();
        }
    }

}
