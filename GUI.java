import javax.swing.*;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame {

    int spacing = 5;
    //Creating a window
    public GUI(){
        this.setTitle("Minesweeper"); //sets title to window
        this.setSize(1286, 800); //sets size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes sure program is terminated when exited
        this.setVisible(true); //makes window visible

        Board board = new Board();
        this.setContentPane(board);//sets content panel
    }

    public class Board extends JPanel{
        //sets background color
        public void paintComponent(Graphics graphics){
            graphics.setColor(Color.gray);
            graphics.fillRect(0, 0,1286,800);

            //sets grid
            graphics.setColor(Color.lightGray);
            int i, j;
            for(i = 0; i < 16; i++) {
                for (j = 0; j < 9; j++){
                    graphics.fillRect(i * 86, j * 86 + (16 * spacing),86-spacing,86-spacing );

                }
            }
        }
    }
}