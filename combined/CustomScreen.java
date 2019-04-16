import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomScreen implements ActionListener, ChangeListener {
    private int customSize=20, customProb=4, customRad=1, gameMode = 0;
    private JFrame frame;

    public JPanel CustomScreen() {
        JPanel buttons = new JPanel();

        //sets panel layout
        buttons.setLayout(new GridLayout(6,2));
        //sets background
        buttons.setBackground(new Color(114, 159, 180));

        //adds elements to the panel
        JLabel title = new JLabel("Mine", JLabel.CENTER);
        title.setFont(new Font("Courier", Font.BOLD, 30));
        buttons.add(title, BorderLayout.CENTER);

        JLabel subTitle = new JLabel("Sweeper", JLabel.CENTER);
        subTitle.setFont(new Font("Courier", Font.BOLD, 30));
        buttons.add(subTitle, BorderLayout.CENTER);
        //Radio Buttons for game modes
        JRadioButton mineMode = new JRadioButton("Classic");
        JRadioButton colorMode = new JRadioButton("Color");
        mineMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMode = 0;
                colorMode.setSelected(false);
            }
        });

        colorMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMode = 1;
                mineMode.setSelected(false);
            }
        });
        //adds them to the panel
        buttons.add(mineMode);
        buttons.add(colorMode);
        //Labels for sliders
        JLabel Plabel = new JLabel("1 over "+customProb,JLabel.CENTER);
        Plabel.setBackground(new Color(114, 159, 180));
        Plabel.setOpaque(true);
        JLabel Slabel = new JLabel(customSize+" x "+customSize,JLabel.CENTER);
        JLabel Rlabel = new JLabel("Radius: "+customRad,JLabel.CENTER);
        //Sliders for options
        JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, 2, 100, 20);
        sizeSlider.setBorder(BorderFactory.createTitledBorder("Board Size"));
        sizeSlider.setMajorTickSpacing(20);
        sizeSlider.setMinorTickSpacing(10);
        sizeSlider.setPaintLabels(true);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setBackground(new Color(79, 137, 170));
        sizeSlider.setOpaque(true);
        sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    customSize = source.getValue();
                    Slabel.setText(customSize+" x "+customSize);
                }
            }
        });

        JSlider probSlider = new JSlider(JSlider.HORIZONTAL, 2, 10, 4);
        probSlider.setBorder(BorderFactory.createTitledBorder("Probability 1/n"));
        probSlider.setMajorTickSpacing(2);
        probSlider.setMinorTickSpacing(1);
        probSlider.setPaintTicks(true);
        probSlider.setPaintLabels(true);
        probSlider.setInverted(true);
        probSlider.setOpaque(true);
        probSlider.setBackground(new Color(79, 137, 170));
        probSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    customProb = source.getValue();
                    Plabel.setText("1 Over "+String.valueOf(customProb));
                }
            }
        });

        JSlider radiusSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 1);
        radiusSlider.setBorder(BorderFactory.createTitledBorder("Radius"));
        radiusSlider.setMajorTickSpacing(2);
        radiusSlider.setMinorTickSpacing(0);
        radiusSlider.setPaintTicks(true);
        radiusSlider.setPaintLabels(true);
        radiusSlider.setOpaque(true);
        radiusSlider.setBackground(new Color(79, 137, 170));
        radiusSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    customRad = source.getValue();
                    Rlabel.setText("Radius: "+String.valueOf(customRad));
                }
            }
        });
        //add them to the panel
        buttons.add(sizeSlider);
        buttons.add(Slabel);
        buttons.add(probSlider);
        buttons.add(Plabel);
        buttons.add(radiusSlider);
        buttons.add(Rlabel);
        JButton startButton = new JButton("Start Game");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Thread(new Minesweeper(customSize, customProb, customRad, gameMode)).start();
            }
        });
        buttons.add(startButton);
        //ImageIcon filler = new ImageIcon(new ImageLibrary().getImage(10));
        //JLabel fillImage = new JLabel(filler);
        //fillImage.setBackground(Color.LIGHT_GRAY);
        //fillImage.setOpaque(true);
        //buttons.add(fillImage);

        JButton returnButton = new JButton("Return to Menu");
        returnButton.addActionListener(this);
        buttons.add(returnButton);

        return buttons;
    }

    public void actionPerformed(ActionEvent e) {
        Object cmd = e.getActionCommand();
        if (cmd.equals("Return to Menu")) {
            System.out.println("Return to Menu");
            Menu menu = new Menu();
            menu.showFrame();
        }
    }

    public void stateChanged(ChangeEvent e) {

    }

    public void showFrame() {

        frame = new JFrame();

        //loads panels
        JPanel myMenu = CustomScreen();

        //sets frame size and title
        frame.setBounds(200, 200, 470, 554);
        frame.setTitle("Custom");

        //sets properties of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);


        //add the menu to the frame
        frame.add(myMenu);
        frame.revalidate();
    }
}
