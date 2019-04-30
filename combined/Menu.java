import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Menu extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    private JFrame frame;
    private int easySize = 10, 
        mediumSize = 20, 
        hardSize = 30, 
        easyProbability = 5, 
        mediumProbability = 6, 
        hardProbability = 6;

    public JPanel splash() {

        JPanel content = new JPanel();

        //sets panel layout
        content.setLayout(new GridLayout(3, 1));

        //sets background
        content.setBackground(Color.decode("#F6F6F6"));

        //adds elements to the panel
        JLabel title = new JLabel("Minesweeper", JLabel.CENTER);
        title.setFont(new Font("Courier", Font.BOLD, 55));
        content.add(title, BorderLayout.CENTER);

        try {
            BufferedImage image = ImageIO.read(new URL("https://web.cs.sunyit.edu/~schneieh/images//mine.jpeg"));
            Image mine = image.getScaledInstance(150, 180, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(mine));
            content.add(label, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("No image");
        }

        JLabel names = new JLabel("By Alex, Chris, Dylan, Ethan, Richard, and Thais", JLabel.CENTER);
        names.setFont(new Font("Courier", Font.BOLD, 15));
        content.add(names, BorderLayout.SOUTH);

        return content;
    }


    public JPanel menu() {

        JPanel buttons = new JPanel();

        //sets panel layout
        buttons.setLayout(new GridLayout(8, 1));

        //sets background
        buttons.setBackground(new Color(114, 159, 180));

        //adds elements to the panel
        JLabel title = new JLabel("Minesweeper", JLabel.CENTER);
        title.setFont(new Font("Courier", Font.BOLD, 55));
        buttons.add(title, BorderLayout.CENTER);

        JLabel subTitle = new JLabel("Select a mode", JLabel.CENTER);
        subTitle.setFont(new Font("Courier", Font.BOLD, 20));
        buttons.add(subTitle, BorderLayout.CENTER);

        JButton button1 = new JButton("Easy");
        JButton button2 = new JButton("Medium");
        JButton button3 = new JButton("Hard");
        JButton button4 = new JButton("Color Mode");
        JButton button5 = new JButton("Memory Mode");
        JButton button6 = new JButton("Custom Game");
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);

        return buttons;
    }

    // for buttons and menus
    public void actionPerformed(ActionEvent e) {
        Object cmd = e.getActionCommand();
        if (cmd.equals("Exit")) {
            System.out.println("Game -> Exit");
            System.exit(0);
        }else if (cmd.equals("Instructions")) {
            System.out.println("Help -> Instructions");
            //instructions here

        }else if (cmd.equals("Credits")) {
            System.out.println("About -> Credits");
            frame.setVisible(false);
            Credits credit = new Credits();
            credit.showCredits();

        }else if (cmd.equals("Easy")) {
            System.out.println("Button 1 pressed");
            frame.setVisible(false);
            new Thread(new Minesweeper(easySize, easyProbability, 1, 0)).start();
        } else if (cmd.equals("Medium")) {
            System.out.println("Button 2 pressed");
            frame.setVisible(false);
            new Thread(new Minesweeper(mediumSize, mediumProbability, 1, 0)).start();
        } else if (cmd.equals("Hard")) {
            System.out.println("Button 3 pressed");
            frame.setVisible(false);
            new Thread(new Minesweeper(hardSize, hardProbability, 1, 0)).start();
        } else if (cmd.equals("Color Mode")) {
            System.out.println("Button 4 pressed");
            frame.setVisible(false);
            new Thread(new Minesweeper(mediumSize, mediumProbability, 1, 1)).start();
        } else if (cmd.equals("Memory Mode")) {
            System.out.println("Button 5 pressed");
            frame.setVisible(false);
            new Thread(new Minesweeper(mediumSize, mediumProbability, 1, 2)).start();
        } else if (cmd.equals("Custom Game")) {
            frame.setVisible(false);
            CustomScreen cs = new CustomScreen();
            cs.showFrame();
        } else {
            System.out.println("Unknown action");
        }
    }

    // for the mouse
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Mouse pressed at " + x + ", " + y);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void showSplash(){

        JPanel mySplash = splash();
        frame = new JFrame();

        //sets frame size and title
        frame.setBounds(200, 200, 470, 554);
        frame.setTitle("Minesweeper");

        frame.getContentPane().add(mySplash, BorderLayout.CENTER);

        //sets properties of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);


        try {
            Thread.sleep(3500);
        } catch (Exception e) {
        }

        frame.remove(mySplash);
        frame.setVisible(false);

        Menu menu = new Menu();
        menu.showFrame();

    }
    public void showFrame() {

        frame = new JFrame();

        //loads panels
        JPanel myMenu = menu();

        //sets frame size and title
        frame.setBounds(200, 200, 470, 554);
        frame.setTitle("Minesweeper");

        //sets properties of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        //add the menuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        menuBar.add(menu);
        JMenuItem menuExit = new JMenuItem("Exit");
        menuExit.addActionListener(this);
        menu.add(menuExit);


        JMenu help = new JMenu("Help");
        menuBar.add(help);
        JMenuItem instructions = new JMenuItem("Instructions");
        instructions.addActionListener(this);
        help.add(instructions);

        JMenu about = new JMenu("About");
        menuBar.add(about);
        JMenuItem credits = new JMenuItem("Credits");
        credits.addActionListener(this);
        about.add(credits);

        frame.setJMenuBar(menuBar);

        //add the menu to the frame
        frame.add(myMenu);
        frame.revalidate();
    }

}
