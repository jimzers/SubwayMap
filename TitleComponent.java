import javax.swing.*;
import java.awt.*;

public class TitleComponent extends JComponent {
    int xPos, yPos;
    String text;
    public TitleComponent(int x, int y, String text) {
        this.xPos = x;
        this.yPos = y;
        this.text = text;
    }
    public void paintComponent(Graphics g)
    {
        // makes the text bold and bigger than default
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString(text, xPos, yPos);
    }
}
