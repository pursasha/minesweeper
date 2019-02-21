import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame 
{

    int spacing = 5;
    public int mx= -100;
    public int my= -100;
    //Creating a window
    public GUI()
    {
        this.setTitle("Minesweeper"); //sets title to window
        this.setSize(450, 450); //sets size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes sure program is terminated when exited
        this.setResizable(false);

        Board board = new Board();
        this.setContentPane(board);//sets content panel
        this.setVisible(true); //makes window visible

        Move move= new Move();
        this.addMouseMotionListener(move);

        Click click= new Click();
        this.addMouseListener(click);


    }

    public class Board extends JPanel
    {
        //sets background color
        public void paintComponent(Graphics graphics)
        {
            graphics.setColor(Color.gray);
            graphics.fillRect(0, 0,480,480);

            //sets grid
            int i, j;
            for(i = 0; i < 5; i++) 
            {
                for (j = 0; j < 5; j++)
                {
                    graphics.setColor(Color.lightGray);
                    if(mx>=spacing+i * 86 && mx< spacing+i * 86+86-spacing && my>=j * 86+86 + spacing+26 && my< j * 86+86  +26 +spacing +86-spacing ) 
                    {
                       // mx>=spacing+ i * 86 && mx< i * 86+86-spacing && my>= j * 86+86 + spacing + 26 && my<j * 86+86 -spacing)
                        graphics.setColor(Color.blue);
                    }
                    graphics.fillRect(spacing+i * 86, j * 86+86 + spacing,86-spacing,86-spacing );

                }
            }
        }
    }

    public class Move implements MouseMotionListener
    {
        
        public void mouseDragged(MouseEvent a)
        {

        }
        public void mouseMoved(MouseEvent a)
        {
            System.out.println("the mouse was moved");
            mx=a.getX();
            my=a.getY();
            System.out.println("x:"+ mx +" y:"+my);
        }

    }
    public class Click implements MouseListener 
    {
        public void mouseClicked(MouseEvent a)
        {
            System.out.println("the mouse was clicked");
        

        }

        public void mouseEntered(MouseEvent a)
        {

        }

        public void mouseExited(MouseEvent a)
        {

        }

        public void mousePressed(MouseEvent a)
        {

        }

        public void mouseReleased(MouseEvent a)
        {

        }

    }

}
