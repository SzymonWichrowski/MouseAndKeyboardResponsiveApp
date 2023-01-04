import javax.swing.*;

public class Main {

    public static void main(String[] args){

        SwingUtilities.invokeLater( () -> {     //metoda invokeLater wersja za pomocą wyrażenia lambda
            FirstWindow firstWindow = new FirstWindow();
            firstWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            firstWindow.setVisible(true);
        });
    }
}
