package modeloGrafico.tablasConsulta;

import static app.funProp.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImprimirEntidad extends JFrame{
    private JTextArea txtImprimir;
    public JButton btnRegresar;
    private JLabel lblTitulo;
    private JPanel JpanelImprimir;

    private String[] encabezados;
    private String[][] datos;
    


    //CONSTRUCTOR
    public ImprimirEntidad() {
        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                setDefault();
            }
        });
    }

    protected void setDefault() {
        txtImprimir.setText(null);
    }

    public void desplegarForm(String[] encabezados, String[][] datos) {
        this.encabezados = encabezados;
        this.datos = datos;
        setContentPane(JpanelImprimir);
        setTitle("VENTANA IMPRESION DE VALORES");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        ImprimirTexto();
    }

    private void ImprimirTexto() {
        txtImprimir.setText("**** LISTA DE REGISTROS DE LA ENTIDAD ****\n");
        for (int i = 0; i < datos.length; i++) {
            txtImprimir.append(repetNtimes("-", 50)+"\n");
            for (int j = 0; j < encabezados.length; j++) {
                txtImprimir.append(encabezados[j]+": "+ datos[i][j] + "\n");
            }
            txtImprimir.append(repetNtimes("-", 50)+"\n");
        }
    }
}
