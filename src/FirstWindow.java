import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindow extends JFrame implements ActionListener {

    private JButton catch_button, canva_button;
    public FirstWindow() {

        setSize(1000, 600);
        setTitle("Your choice");
        setLayout(null);

        catch_button = new JButton("Catch the Button!");
        catch_button.setBounds(0, 0, 500, 600);
        catch_button.setBackground(new Color(182, 127, 71));
        catch_button.setForeground(new Color(97, 210, 153));
        catch_button.setFont(new Font("Dialog", Font.ITALIC, 28));
        add(catch_button);
        catch_button.addActionListener(this);

        canva_button = new JButton("Let's paint!");
        canva_button.setBounds(500, 0, 500, 600);
        canva_button.setBackground(new Color(59, 105, 148));
        canva_button.setForeground(new Color(204, 105, 167));
        canva_button.setFont(new Font("Dialog", Font.ITALIC, 28));
        add(canva_button);
        canva_button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if(source == catch_button){
            CatchTheButton catchButton = new CatchTheButton();
            catchButton.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            catchButton.setVisible(true);
            dispose();
        }
        else if(source == canva_button){
            Canva canva = new Canva();
            canva.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            canva.setVisible(true);
            dispose();
        }
    }
}
