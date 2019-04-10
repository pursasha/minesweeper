import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Credits extends JFrame implements ActionListener {

    private JFrame frame;


    public JPanel cred() {

        JPanel credit = new JPanel();

        //sets panel layout
        // credit.setLayout(new GridLayout(19, 5));

        //sets background
        credit.setBackground(new Color(114, 159, 180));

        //adds elements to the panel
        JLabel title = new JLabel("Minesweeper\n", JLabel.CENTER);
        title.setFont(new Font("Courier", Font.BOLD, 35));
        credit.add(title, BorderLayout.CENTER);

        String info = "Minesweeper is a game created by yhasi therh thrlj ejwh," +
                "\n the game was created with a bese of the original " +
                "\n1990 Moienevffkjnjnfsdnflnf;nfs;dfn;fsldanfdl";
        JLabel text = new JLabel(info);
        text.setFont(new Font("Courier", Font.BOLD, 12));
        credit.add(text, BorderLayout.CENTER);


        return credit;
    }

    public void showCredits() {

        frame = new JFrame();

        //loads panels
        JPanel myCredits = cred();

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
        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener(this);
        menu.add(restart);
        JMenuItem menuReturn = new JMenuItem("Return");
        menuReturn.addActionListener(this);
        menu.add(menuReturn);
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
        frame.add(myCredits);
        frame.revalidate();
    }

    public void actionPerformed(ActionEvent e) {
        Object cmd = e.getActionCommand();
        if (cmd.equals("Exit")) {
            System.out.println("Game -> Exit");
            System.exit(0);
        } else if (cmd.equals("Return")) {
            System.out.println("Game -> Menu");
            Menu menu = new Menu();
            menu.showFrame();
        } else if (cmd.equals("Restart")) {
            System.out.println("Game -> Restart");
            //restart function here
        } else if (cmd.equals("Instructions")) {
            System.out.println("Help -> Instructions");
            //instructions here

        } else if (cmd.equals("Credits")) {
            System.out.println("About -> Credits");
            Credits credit = new Credits();
            credit.showCredits();

        }

    }
}
