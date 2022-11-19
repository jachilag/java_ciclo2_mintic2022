package interfazGrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame{
    public JLabel lblTitle;
    public JPanel panelPrincipal;
    public JTextField textResultado;
    public JTextField textNumero1;
    public JTextField textNumero2;
    public JButton btn1;
    public Calculadora(){
        setContentPane(panelPrincipal);
        setTitle("Mi primera Calculadora");
        setSize(400,200);
        setLocation(300,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int salida;
                salida = Integer.parseInt(textNumero1.getText()) * Integer.parseInt(textNumero2.getText());
                textResultado.setText(String.valueOf(salida));
            }
        });
    }
}
