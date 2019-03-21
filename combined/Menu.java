import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JWindow;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;


public class Menu extends JWindow implements ActionListener, MouseListener, MouseMotionListener  {

    private JFrame frame;

    public void showMenu() {

        //panel of buttons
        JPanel buttons = (JPanel) getContentPane();
        buttons.setPreferredSize(new Dimension(470, 554));
       //this.setSize(560, 600);

        //set background color
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        buttons.setBackground(Color.lightGray);

        //sets border
        Color Border = new Color(18, 33, 237, 130);
        buttons.setBorder(BorderFactory.createLineBorder(Border, 10));

        GridLayout layout = new GridLayout(8, 1, 5, 5);
        getContentPane().setLayout(layout);

        JLabel title = new JLabel(" Minesweeper!", JLabel.CENTER);
        title.setFont(new Font("Courier", Font.BOLD, 55));
        buttons.add(title, SwingConstants.CENTER);

        JLabel subTitle = new JLabel("Select a mode:", JLabel.CENTER);
        subTitle.setFont(new Font("Courier", Font.BOLD, 25));
        buttons.add(subTitle, BorderLayout.CENTER);


        JButton button1 = new JButton("Easy");
        JButton button2 = new JButton("Medium");
        JButton button3 = new JButton("Hard");
        JButton button4 = new JButton("Custom");
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);


        buttons.setVisible(true);

        // menuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuExit = new JMenuItem("Exit");
        menuExit.addActionListener(this);
        menu.add(menuExit);

        // setup the frame and add components
        frame = new JFrame();

        frame.setJMenuBar(menuBar);
        frame.add(buttons,BorderLayout.LINE_END);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.pack();

        frame.setVisible(true);
    }


    public void show() {

        frame.repaint();
    }


    // for buttons and menus
    public void actionPerformed(ActionEvent e) {
        Object cmd = e.getActionCommand();
        if      (cmd.equals("Exit")) {
            System.out.println("File -> Exit");
            System.exit(0);
        }
        else if (cmd.equals("Easy")) {
            System.out.println("Button 1 pressed");
            frame.setVisible(false);
            new Thread( new Minesweeper()).start();
        }
        else if (cmd.equals("Medium")) {
            System.out.println("Button 2 pressed");
        }
        else if (cmd.equals("Hard")){
            System.out.println("Button 3 pressed");
        }

        else {
            System.out.println("Unknown action");
        }
    }

    // for the mouse
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Mouse pressed at " + x + ", " + y);

        show();
    }

    public void mouseClicked (MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered (MouseEvent e) { }
    public void mouseExited  (MouseEvent e) { }
    public void mouseDragged (MouseEvent e) { }
    public void mouseMoved   (MouseEvent e) { }


   // public static void main(String[] args) {
      //  Menu menu = new Menu();

   // }

}

/*
    // for the keyboard

    implement KeyListener
    public void keyPressed (KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }

    public void keyTyped(KeyEvent e) {
        System.out.println("Key = '" + e.getKeyChar() + "'");
    }

    center.addKeyListener(this);*/