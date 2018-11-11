import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.event.*;
import java.awt.geom.Line2D;

public class Starter
{
    // variables are static because they need to be referenced and changed within other class methods
    // can either be add or connect
    public static String mouse_state = "add";
    // becomes true if the program is in the middle of connecting a line between stations, makes sure that
    // you can't change state to add in the middle.
    public static boolean connecting = false;
    public static int station_x, station_y;
    public static void main(String[] args) {
        /*
         * create a Frame, where the graphic will go
         * set the size of the Frame
         * create an instance of my graphical component
         * add the component to the frame
         * set the frame to visible, which updates the frame with the component
         */

        // make frame, name the map, draw the title in a fancy box
        JFrame f = new JFrame("Main Frame");
        f.setSize(800, 600);
        f.setVisible(true);

        // prompt user for subway name
        String sub_title = JOptionPane.showInputDialog(null, "please title this subway map");

        // init components
        RectangleComponent subway_rectangle = new RectangleComponent(600, 10, 200, 90);
        TitleComponent subway_title = new TitleComponent(610, 40, sub_title);
        TextComponent instructions = new TextComponent(610, 60, "press 'a' to add stations,");
        TextComponent instructions2 = new TextComponent(610, 72, "press 'b' to connect stations");
        TextComponent mode_text = new TextComponent(610, 84, "current mode: add");


        // add elements to frame
        f.add(instructions);
        f.setVisible(true);
        f.add(instructions2);
        f.setVisible(true);
        f.add(mode_text);
        f.setVisible(true);
        f.add(subway_title);
        f.setVisible(true);
        f.add(subway_rectangle);
        f.setVisible(true);

        // keylistener for general frame
        class myKeyListener implements KeyListener {
            public void keyPressed(KeyEvent e) {
                //when a key is pressed I check to see if it is changing states
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    if (!connecting) {
                        mouse_state = "add";
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_B) {
                    if (!connecting) {
                        mouse_state = "connect";
                    }
                }
                mode_text.setText("current mode: " + mouse_state);
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }
        }

        // mouselistener for each specific ellipse - helps to connect them
        class StationConnect implements MouseListener {
            int event_x,event_y;
            @Override
            public void mousePressed(MouseEvent e) {
                event_x = e.getX();
                event_y = e.getY();
                if (mouse_state.equals("connect")){
                    if (!connecting){
                        // if not in midst of connecting, get x and y pos of the clicked object
                        // get the ellipse that was clicked
                        Object source = e.getSource();
                        // double check to make sure the circle clicked is ellipsecomponent
                        if(source instanceof EllipseComponent){
                            // type casting to be able to use getX and getY
                            EllipseComponent station1 = (EllipseComponent) source;
                            station_x = station1.getX();
                            station_y = station1.getY();
                            connecting = true;
                        }
                    } else {
                        // get ellipse clicked
                        Object source2 = e.getSource();
                        if(source2 instanceof EllipseComponent){
                            // type casting to be able to use getX and getY
                            EllipseComponent station2 = (EllipseComponent) source2;

                            // make line component between the two selected ellipses
                            // the plus 10 part is the increment to make the line go between the circles' center
                            LineComponent connector = new LineComponent(station_x + 10, station_y + 10,
                                    station2.getX() + 10, station2.getY() + 10);
                            f.add(connector);
                            f.setVisible(true);
                            connecting = false;
                        }

                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
        }

        // mouselistener for general frame
        class MickeyMouse implements MouseListener {

            int event_y, event_x;
            String text_label;

            public void mousePressed(MouseEvent e) {
                if (mouse_state.equals("add")) {
                    // get the coordinattes when mouse clicked
                    event_x = e.getX();
                    event_y = e.getY();

                    // prompt user for station name
                    text_label = JOptionPane.showInputDialog(null, "please gimme a label for this station");

                    // initialize label and circle representing station
                    TextComponent tC = new TextComponent(event_x, event_y - 5, text_label);
                    EllipseComponent eC = new EllipseComponent(event_x, event_y, 20, 20);

                    // add mouselistener for connecting to the ellipse
                    MouseListener station_listener = new StationConnect();
                    eC.addMouseListener(station_listener);

                    // add elements to frame
                    f.add(eC);
                    f.setVisible(true);
                    f.add(tC);
                    f.setVisible(true);


                }


            }

            public void mouseReleased(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
            public void mouseClicked(MouseEvent e) { }
        }

        f.addKeyListener(new myKeyListener());//add keylistener to frame
        f.addMouseListener(new MickeyMouse()); // add mouselistner to frame
        f.setVisible(true);



    }
}
