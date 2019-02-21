import javax.swing.*;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame {

    int height = 532;
    int width = 450;

    //Creating a window
    public GUI(){
        this.setTitle("Minesweeper"); //sets title to window
        this.setSize(width, height); //sets size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes sure program is terminated when exited
        this.setResizable(false); // set it so no one can resize the window

        Board board = new Board();
        this.setContentPane(board);//sets content panel
        this.setVisible(true); //makes window visible
    }

    public class Board extends JPanel{

        //sets background color
        public void paintComponent(Graphics graphics){
            graphics.setColor(Color.gray);
            graphics.fillRect(0, 0,width,height);

            //sets grid
            int spacing = 5;
            graphics.setColor(Color.lightGray);
            int i, j;
            for(i = 0; i < 10; i++) {
                for (j = 0; j < 9; j++){
                    graphics.fillRect(i * 90, j * 90 + (12 * spacing),90-spacing,90-spacing );
                }

            }
        }
    }
}
