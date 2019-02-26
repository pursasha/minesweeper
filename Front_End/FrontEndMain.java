public class FrontEndMain implements Runnable {
    GUI gui = new GUI();

    public static void main(String[] args){

        new Thread( new FrontEndMain()).start();
    }
    @Override
    public void run(){
        while(true){
            gui.repaint();
        }
    }

}
