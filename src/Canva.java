import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Canva extends JFrame implements ActionListener {

    private JButton paint_button, color_button;
    private JLabel paint_label;
    private JPanel canva_panel;
    private JScrollPane scroll_canva;

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

        paint_button = new JButton("Paint Info");   //tworzenie przycisku ktory wyswietli instrukcje
        paint_button.setBounds(50, 600, 100, 50);
        add(paint_button);
        paint_button.addActionListener(this);

        paint_label = new JLabel("");           //tworzenie etykiety wybranej figury
        paint_label.setBounds(50, 700, 200, 50);
        add(paint_label);

        color_button = new JButton("Color");        //tworzenie przycisku do wyboru koloru
        color_button.setBounds(200, 600, 100, 50);
        add(color_button);
        color_button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if(source == paint_button) {
            JOptionPane.showMessageDialog(null,
                                                "You're choosing a shape to draw: \n" +
                                                        "-> click the R key to draw a rectangle \n" +
                                                        "-> click the E key to draw an ellipse",
                                                   "Paint Info",
                                                        JOptionPane.INFORMATION_MESSAGE);

        }

        else if(source == color_button) {
           Color color = JColorChooser.showDialog(null, "Choose a color!", Color.BLACK);

        }

    }

}
