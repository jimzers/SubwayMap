import javax.swing.JComponent;
import java.awt.Graphics;
// import java.awt.Font;
public class TextComponent extends JComponent
{
    int x,y;
    String text;
    public TextComponent(int x, int y, String t)
    {
        this.x = x;
        this.y = y;
        this.text = t;
    }

    public void paintComponent(Graphics g)
    {
        // g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize=20));
        g.drawString(text, x, y);
    }

    public void setText(String text){
        this.text = text;
        repaint();
    }

}
