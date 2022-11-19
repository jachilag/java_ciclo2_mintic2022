package modeloGrafico.login;

import static app.funProp.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Creditos extends JFrame {
    private JTextArea txtCreditos;
    private JPanel JpanelCreditos;
    private JLabel lblTitulo;
    private JButton btnRegresar;

    public Creditos() {
        setContentPane(JpanelCreditos);
        setTitle("VENTANA CREDITOS ESTUDIANTE MINTIC 2022");
        setSize(600,400 );
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        txtCreditos.setText("\n" + repetNtimes("=", 50) + "\n");
        txtCreditos.append("\nTrabajo realizado por: ");
        txtCreditos.append("\nJonathan Alexander Chila Guevara");
        txtCreditos.append("\nRuta de aprendizaje MINTIC 2022\nDesarrollo de software, ciclo 2");
        txtCreditos.append("\n" + repetNtimes("=", 50));

        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}