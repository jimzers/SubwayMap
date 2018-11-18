import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Starter
{
    // variables are static because they need to be referenced and changed within other class methods

    // either red, blue, yellow, green
    private static String current_color = "red";
    private static boolean autoconnect = true;

    // becomes true if the program is in the middle of connecting a line between stations, makes sure that
    // you can't change state to add in the middle.
    private static boolean connecting = false;
    private static int station_x, station_y;

    static ArrayList<EllipseComponent> station_array = new ArrayList<EllipseComponent>();

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
        RectangleComponent subway_rectangle = new RectangleComponent(600, 10, 200, 120);
        TitleComponent subway_title = new TitleComponent(610, 40, sub_title);
        TextComponent instructions = new TextComponent(610, 60, "click to add stations,");
        TextComponent instructions2 = new TextComponent(610, 72, "press 'c' to toggle autoconnect");
        TextComponent instructions3 = new TextComponent(610, 84, "'r, b, g, y' to toggle color");
        TextComponent mode_text = new TextComponent(610, 96, "autoconnect: on");
        TextComponent color_text = new TextComponent(610, 108, "current color: red");


        // add elements to frame
        f.add(instructions);
        f.setVisible(true);
        f.add(instructions2);
        f.setVisible(true);
        f.add(instructions3);
        f.setVisible(true);
        f.add(mode_text);
        f.setVisible(true);
        f.add(color_text);
        f.setVisible(true);
        f.add(subway_title);
        f.setVisible(true);
        f.add(subway_rectangle);
        f.setVisible(true);

        // keylistener for general frame
        class myKeyListener implements KeyListener {
            public void keyPressed(KeyEvent e) {
                //when a key is pressed I check to see if it is changing states
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    if (autoconnect){
                        // reset the dot connecting by setting connecting variable to false so previous connection
                        // doesn't continue
                        autoconnect = false;
                        connecting = false;
                    } else {
                        autoconnect = true;
                    }
                }
                // update autoconnect text
                if (autoconnect) {
                    mode_text.setText("autoconnect: on");
                } else{
                    mode_text.setText("autoconnect: off");
                }
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    current_color = "red";
                }
                if (e.getKeyCode() == KeyEvent.VK_Y) {
                    current_color = "yellow";
                }
                if (e.getKeyCode() == KeyEvent.VK_G) {
                    current_color = "green";
                }
                if (e.getKeyCode() == KeyEvent.VK_B) {
                    current_color = "blue";
                }
                color_text.setText("current color: " + current_color); // update color text
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }
        }


        // mouselistener for general frame
        class MickeyMouse implements MouseListener {

            private int event_y, event_x;
            private String text_label;

            public void mousePressed(MouseEvent e) {
                // get the coordinattes when mouse clicked
                event_x = e.getX();
                event_y = e.getY();
                // prompt user for station name
                text_label = JOptionPane.showInputDialog(null, "please gimme a label for this station");

                // initialize label and circle representing station - offsets are to make the center of the stations
                // appear right on the cursor
                TextComponent tC = new TextComponent(event_x - 20, event_y - 60, text_label);
                EllipseComponent eC = new EllipseComponent(event_x - 20, event_y - 55, 20, 20, current_color);
                if(autoconnect){
                    if (!connecting) {
                        System.out.println("created anchor pt");
                        station_x = event_x;
                        station_y = event_y;
                        connecting = true;
                    } else {
                        System.out.println("created line");
                        LineComponent connector = new LineComponent(station_x - 10, station_y - 45,
                                event_x - 10, event_y - 45, current_color);
                        f.add(connector);
                        f.setVisible(true);
                        station_x = event_x;
                        station_y = event_y;
                    }
                }
                // add ellipse to array of stations
                station_array.add(eC);

                // add elements to frame
                f.add(eC);
                f.setVisible(true);
                f.add(tC);
                f.setVisible(true);

            }
            // useless implements
            public void mouseReleased(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
            public void mouseClicked(MouseEvent e) { }
        }

        f.addKeyListener(new myKeyListener());//add keylistener to frame
        f.addMouseListener(new MickeyMouse()); // add mouselistner to frame
        f.setVisible(true); // adds the event handlers to jframe



    }
}
