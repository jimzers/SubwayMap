import javax.swing.JComponent;
import java.awt.geom.Line2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
public class LineComponent extends JComponent
{
    Line2D r;
    int x,y,x2, y2;
    public LineComponent(int xPos1, int yPos1,int xPos2, int yPos2)
    {
        x = xPos1;
        y = yPos1;
        x2 = xPos2;
        y2 = yPos2;
    }

    public void paintComponent(Graphics g)
    //overriding paintComponent method with my own drawing instructions
    {
        r = new Line2D.Double(x,y,x2,y2);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
        g2.fill(r);
    }
    /*
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
    */
}
