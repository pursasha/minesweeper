import javax.swing.*;

public class Main implements Runnable {
    GUI gui = new GUI();

    public static void main(String[] args){

        new Thread( new Main()).start();
    }
    @Override
    public void run(){
        while(true){
            gui.repaint();
        }
    }

}