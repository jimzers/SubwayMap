import javax.swing.JComponent;
import java.awt.geom.Line2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
public class LineComponent extends JComponent
{
    Line2D r;
    String color;
    int x,y,x2, y2;
    public LineComponent(int xPos1, int yPos1,int xPos2, int yPos2, String c)
    {
        x = xPos1;
        y = yPos1;
        x2 = xPos2;
        y2 = yPos2;
        color = c;
    }

    public void paintComponent(Graphics g)
    //overriding paintComponent method with my own drawing instructions
    {
        r = new Line2D.Double(x,y,x2,y2);
        Graphics2D g2 = (Graphics2D) g;
        // todo: test if line color is same
        if(color.equals("red")){
            g2.setColor(Color.RED);
        } else if(color.equals("yellow")){
            g2.setColor(Color.YELLOW);
        } else if(color.equals("green")){
            g2.setColor(Color.GREEN);
        } else if(color.equals("blue")){
            g2.setColor(Color.BLUE);
        }
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
