import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Canva extends JFrame implements ActionListener, KeyListener, ChangeListener, MouseListener {

    private JButton paint_button, color_button;
    private JLabel paint_label, color_label, width_label, height_label, xlabel, ylabel;
    private JTextField paint_tfield;
    private JSlider width_slider, height_slider;
    private JPanel canva_panel;
    private JScrollPane scroll_canva;
    private JComboBox fill_cBox;
    private Color currentColor = Color.BLACK,      //przypisujemy poczatkowe barwy
                  buttonColor = Color.PINK;
    private Font buttonFont = new Font("Dialog", Font.ITALIC, 14);      //czcionka do przycisków
    private double width = 250, height = 100, x = 450, y = 200;

    public Canva(){

        setSize(1200, 800);
        setTitle("Let's paint!");
        setLayout(null);
        getContentPane().setBackground(Color.GRAY);

        canva_panel = new JPanel();             //tworzenie kanwy
        scroll_canva = new JScrollPane(canva_panel);        //tworzenie kanwy z suwakami
        scroll_canva.setBounds(50, 50, 1100, 500);
        scroll_canva.setBackground(Color.white);
        add(scroll_canva);
        scroll_canva.addMouseListener(this);

        paint_button = new JButton("Paint Info");   //tworzenie przycisku ktory wyswietli instrukcje
        paint_button.setBounds(50, 575, 100, 50);
        paint_button.setFont(buttonFont);
        paint_button.setBackground(buttonColor);
        add(paint_button);
        paint_button.addActionListener(this);

        paint_label = new JLabel("Chosen Shape");           //tworzenie etykiety wybranej figury
        paint_label.setBounds(50, 675, 200, 50);
        paint_label.setForeground(currentColor);
        paint_label.setFont(buttonFont);
        add(paint_label);

        paint_tfield = new JTextField();            //tworzenie pola tekstowego potrzebnego do keyListenera
        paint_tfield.setBounds(50, 650, 50, 25);
        paint_tfield.setToolTipText("Type the chosen letter here!");    //podpowiedz dla uzytkownika
        add(paint_tfield);
        paint_tfield.addKeyListener(this);

        color_button = new JButton("Color");        //tworzenie przycisku do wyboru koloru
        color_button.setBounds(250, 575, 100, 50);
        color_button.setFont(buttonFont);
        color_button.setBackground(buttonColor);
        color_button.setToolTipText("Choose color!");           //podpowiedz dla uzytkownika
        add(color_button);
        color_button.addActionListener(this);

        color_label = new JLabel("Chosen Color Demostration");  //tworzenie etykiety demonstrującej wybrany kolor
        color_label.setBounds(250, 650, 200, 25);
        color_label.setForeground(currentColor);
        color_label.setFont(buttonFont);
        add(color_label);

        fill_cBox = new JComboBox();            //tworzenie comboBoxu z dwiema możliwościami
        fill_cBox.setBounds(450, 575, 75, 50);
        fill_cBox.setFont(buttonFont);
        fill_cBox.setBackground(buttonColor);
        fill_cBox.addItem("Not fill");              //pierwsza zakładka będzie aktywna od początku działania programu
        fill_cBox.addItem("Fill");                  //dodanie zakładki/możliwości
        add(fill_cBox);

        width_label = new JLabel("Shape Width");
        width_label.setBounds(750, 550, 100, 50);
        width_label.setFont(buttonFont);
        add(width_label);

        width_slider = new JSlider(0, 500, 250);    //(min, max, start) value
        width_slider.setBounds(640, 600, 300, 50);
        width_slider.setBackground(buttonColor);
        width_slider.setMajorTickSpacing(50);        //wstawienie duzych kresek
        width_slider.setMinorTickSpacing(10);        //wstawienie malych kresek
        width_slider.setPaintTicks(true);            //wyswietlenie kresek
        width_slider.setPaintLabels(true);           //wyswietlenie wartosci
        add(width_slider);
        width_slider.addChangeListener(this);

        height_label = new JLabel("Shape Height");
        height_label.setBounds(750, 650, 100, 50);
        height_label.setFont(buttonFont);
        add(height_label);

        height_slider = new JSlider(0, 200, 100);
        height_slider.setBounds(640, 700, 300, 50);
        height_slider.setBackground(buttonColor);
        height_slider.setMajorTickSpacing(20);
        height_slider.setMinorTickSpacing(5);
        height_slider.setPaintTicks(true);
        height_slider.setPaintLabels(true);
        add(height_slider);
        height_slider.addChangeListener(this);

        xlabel = new JLabel("X coordinate: " + x);
        xlabel.setBounds(1000, 600, 200, 50);
        xlabel.setFont(buttonFont);
        add(xlabel);

        ylabel = new JLabel("Y coordinate: " + y);
        ylabel.setBounds(1000, 700, 200, 50);
        ylabel.setFont(buttonFont);
        add(ylabel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if(source == paint_button) {
            JOptionPane.showMessageDialog(null,
                                                "You're choosing a shape to draw: \n" +
                                                        "-> type 'R' below to draw a rectangle \n" +
                                                        "-> type 'E' below to draw an ellipse",
                                                   "Paint Info",
                                                        JOptionPane.INFORMATION_MESSAGE);

        }

        else if(source == color_button) {
            //zmienna kolor przyjmuje barwę wybraną przez uzytkownika poprzez colorChooser
           Color color = JColorChooser.showDialog(null, "Choose a color!", Color.BLACK);
           currentColor = color;       //przypisujemy wybrana barwe do zmiennej current_color
           color_label.setForeground(color);    //zmieniamy barwe etykiety na wybrana przez uzytkownika
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

        if(e.getKeyChar() == 'R' || e.getKeyChar() == 'r') {
            if(fill_cBox.getSelectedItem().toString().equals("Not fill")) {
 //przypisanie graficznego kontekstu komponentu scroll_canva do zmiennej graphics poprzez rzutuowanie na typ Graphics2D
                Graphics2D graphics = (Graphics2D) scroll_canva.getGraphics();
                //prostokąt(wspolrzedna x, wspolrzedna y, szerokosc prostokata, wysokosc prostokata)
                Rectangle2D rectangle = new Rectangle2D.Double(x, y, width, height);
                graphics.setColor(currentColor);    //kolor grafiki
                graphics.draw(rectangle);           //rysujemy grafike podaną w argumencie
                paint_label.setText("Rectangle");
            }

            if(fill_cBox.getSelectedItem().toString().equals("Fill")) {
                Graphics2D graphics = (Graphics2D) scroll_canva.getGraphics();
                Rectangle2D rectangle = new Rectangle2D.Double(x, y, width, height);
                graphics.setColor(currentColor);
                graphics.fill(rectangle);
                paint_label.setText("Rectangle");
            }
        }

        if(e.getKeyChar() == 'E' || e.getKeyChar() == 'e') {
            if(fill_cBox.getSelectedItem().toString().equals("Not fill")) {
                Graphics2D graphics = (Graphics2D) scroll_canva.getGraphics();
                Ellipse2D ellipse = new Ellipse2D.Double(x, y, width, height);
                graphics.setColor(currentColor);
                graphics.draw(ellipse);
                paint_label.setText("Ellipse");
            }

            if(fill_cBox.getSelectedItem().toString().equals("Fill")) {
                Graphics2D graphics = (Graphics2D) scroll_canva.getGraphics();
                Ellipse2D ellipse = new Ellipse2D.Double(x, y, width, height);
                graphics.setColor(currentColor);
                graphics.fill(ellipse);
                paint_label.setText("Ellipse");
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        width =  width_slider.getValue();
        height = height_slider.getValue();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        xlabel.setText("X coordinate: " + x);
        ylabel.setText("Y coordinate: " + y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
