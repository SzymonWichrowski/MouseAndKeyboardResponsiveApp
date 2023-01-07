import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CatchTheButton extends JFrame implements ActionListener {

    static int frameWidth = 1200, frameHeight = 800; //wymiary całego okna
    static int xPanel = 50, yPanel = 50;       //wspolrzedne okna Catch The Button
    static int panelWidth = frameWidth - 2 * xPanel, panelHeight = frameHeight * 3/4;   //wymiary okna Catch The Button
    private JPanel catch_panel;     //panel na którym będzie się poruszał przycisk
    private JLabel title_label, moves_label, xlabel, ylabel;        //etykiety
    private JButton catchInfo_button, back_button, start_button, theButton;     //przyciski
    private Color buttonsColor = new Color(50, 125, 50);        //kolor przycisków
    private Font font = new Font("Dialog", Font.ITALIC, 14);    //czcionka przycisków
    private int xStart = 550, yStart = 300,     //współrzędne początkowe przycisku 'Catch me'
            widthButton = 100, heightButton = 50,       //rozmiary przycisku
            xButton, yButton,   //zmienne przechowujące wartości współrzędnych przycisku 'Catch me' po jego relokacji
            moves_count = 0;    //ilość relokacji

    public CatchTheButton() {

        setSize(frameWidth, frameHeight);
        setTitle("Catch the button!");
        setLayout(null);
        getContentPane().setBackground(Color.GRAY);

        catch_panel = new JPanel();
        catch_panel.setBounds(xPanel, yPanel, panelWidth, panelHeight);
        catch_panel.setBackground(new Color(182, 127, 71, 140));
        add(catch_panel);

        title_label = new JLabel("Catch The Button");
        title_label.setBounds(550, 10, 200, 30);
        title_label.setFont(font);
        add(title_label);

        back_button = new JButton("Back to menu");  //przycisk wywołujący 'FirstWindow'
        back_button.setBounds(950, 10, 200, 30);
        back_button.setBackground(buttonsColor);
        back_button.setFont(font);
        add(back_button);
        back_button.addActionListener(this);

        catchInfo_button = new JButton("Catch Info");       //informacje dotyczące działania programu
        catchInfo_button.setBounds(50, 675, 100, 50);
        catchInfo_button.setBackground(buttonsColor);
        catchInfo_button.setFont(font);
        catchInfo_button.setToolTipText("Everything you need to know");
        add(catchInfo_button);
        catchInfo_button.addActionListener(this);

        start_button = new JButton("Start/Restart");    //przycisk aktywujący 'Catch me' i zerujący zmienne
        start_button.setBounds(200, 675, 150, 50);
        start_button.setToolTipText("Click to start!");
        start_button.setBackground(buttonsColor);
        start_button.setFont(font);
        add(start_button);
        start_button.addActionListener(this);

        theButton = new JButton("Catch me");    //uciekający przycisk
        theButton.setBackground(buttonsColor);
        theButton.setFont(font);
        theButton.setBounds(xStart, yStart, widthButton, heightButton);
        theButton.setToolTipText("if u can ;-)");
        theButton.setEnabled(false);    //przycisk nieaktywny
        add(theButton);
        theButton.addActionListener(this);

        xlabel = new JLabel("X coordinate: ");
        xlabel.setBounds(650, 675, 200, 50);
        xlabel.setFont(font);
        add(xlabel);

        ylabel = new JLabel("Y coordinate: ");
        ylabel.setBounds(800, 675, 200, 50);
        ylabel.setFont(font);
        add(ylabel);

        moves_label = new JLabel("Caught in attempt number: " + moves_count);      //etykieta ilości relokacji
        moves_label.setToolTipText("number of 'Catch me' button moves");
        moves_label.setFont(font);
        moves_label.setBounds(400, 675, 200, 50);
        add(moves_label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if(source == back_button) {
            FirstWindow firstWindow = new FirstWindow();  //wywołanie pierwszego okna
            firstWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            firstWindow.setVisible(true);
            dispose();      //zamkniecie okna Catch The Button
        }

        if(source == catchInfo_button) {
            JOptionPane.showMessageDialog(null,
                    "1) Click 'Start' button to activate 'Catch me' one \n" +
                            "2) Activated 'Catch me' button will change location with every click \n" +
                            "3) To actually catch the button it has to be located near the left or right field boundaries \n" +
                            "4) Try to catch it with the lowest number of attempts. Good luck!", "",
                            JOptionPane.INFORMATION_MESSAGE);
        }

        if(source == start_button) {
            theButton.setEnabled(true);     //przycisk aktywny
            movement();                     //poruszanie się przycisku
            moves_count = 1;
            updateMoves();
        }

        if(source == theButton) {
            if(xButton < 75 || xButton > 1025) {    //warunek złapania przycisku
                caught();
            }
            else movement();
        }
    }

    public void randomCoordinates() {
        xButton = (int) (Math.random() * (panelWidth - widthButton)) + xPanel;
        yButton = (int) (Math.random() * (panelHeight - heightButton)) + yPanel;
        xlabel.setText("Coordinate X: " + xButton);
        ylabel.setText("Coordinate Y: " + yButton);
    }

    public void movement() {
        randomCoordinates();
        theButton.setBounds(xButton, yButton, widthButton, heightButton);
        add(theButton);
        moves_count++;
        updateMoves();
    }

    public void caught() {
        JOptionPane.showMessageDialog(null, "You caught the button in " +
                                      moves_count + " attempt" +" \n" + "Congratulations!",
                                 "Button Caught!", JOptionPane.PLAIN_MESSAGE);
        int valueOfAnswer = JOptionPane.showConfirmDialog(null, "Do you want to leave?", "", JOptionPane.YES_NO_OPTION);
        switch(valueOfAnswer) {
            case 0: {
                dispose();
            }
            break;
            case 1: {
                theButton.setEnabled(false);    //przycisk nieaktywny
            }
            break;
        }
    }

    public void updateMoves() {
        moves_label.setText("Caught in attempt number: " + moves_count);
    }
}

