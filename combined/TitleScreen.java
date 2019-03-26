import javax.swing.*;
import java.awt.*;

public class TitleScreen extends JWindow {
    private int duration;

    public TitleScreen(int duration) {
        this.duration = duration;
    }


    public void showScreen() {

        JPanel content = (JPanel) getContentPane();

        //sets background
        content.setBackground(Color.gray);

        // Set the window size
        this.setSize(470, 554);

        // JLabel label = new JLabel(new ImageIcon("mine.jpeg"));
        //Icon icon = new ImageIcon("mine.jpeg");
        //content.add(label, BorderLayout.CENTER);

        JLabel Title = new JLabel("Minesweeper", JLabel.CENTER);
        Title.setFont(new Font("Courier", Font.BOLD, 55));
        content.add(Title, BorderLayout.CENTER);

        JLabel names = new JLabel("By Alex, Ethan, Chris, Dylan, Richard, Thais", JLabel.CENTER);
        names.setFont(new Font("Sans-Serif", Font.BOLD, 18));
        content.add(names, BorderLayout.SOUTH);

        Color Border = new Color(18, 33, 237, 130);
        content.setBorder(BorderFactory.createLineBorder(Border, 10));

        setVisible(true);

        //waits then makes the title screen disappear
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            setVisible(false);
        }
    }
}


