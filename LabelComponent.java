import javax.swing.JComponent;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
public class LabelComponent extends JComponent
{
    Rectangle2D r;
    int x,y,width,height;
    public LabelComponent(int xPos, int yPos,int w, int h)
    {
        x = xPos;
        y = yPos;
        width = w;
        height = h;
    }
    /*
    public void paintComponent(Graphics g)
    //overriding paintComponent method with my own drawing instructions
    {
        r = new Text2D.Double(x,y,width,height);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fill(r);
    }
    */
    public void moveX(int dx)
    {
        x = x + dx;
        repaint();
    }
    
    public void moveY(int dy)
    {
        y = y + dy;
        repaint();
    }
}
