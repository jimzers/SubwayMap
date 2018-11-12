import javax.swing.JComponent;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
public class EllipseComponent extends JComponent
{
    Ellipse2D r;
    private int x,y,width,height;
    String color;
    public EllipseComponent(int xPos, int yPos,int w, int h, String c)
    {
        x = xPos;
        y = yPos;
        width = w;
        height = h;
        color = c;
    }

    public void paintComponent(Graphics g)
    //overriding paintComponent method with my own drawing instructions
    {
        r = new Ellipse2D.Double(x,y,width,height);
        Graphics2D g2 = (Graphics2D) g;
        // todo: switch statement?
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
