package modeloGrafico.tablasConsulta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IngresoPK extends JFrame{
    private JPanel JpanelPK;
    public JTextField txtPK;
    private JLabel lblPK;
    public JButton btnAgregar;
    private JButton btnRegresar;
    private boolean desplegar;
    private String textPK;
    

    private String valuePK;

    public IngresoPK(){

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                valuePK = txtPK.getText();
                setVisible(false);
                setDefault();
            }
        });

        txtPK.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    valuePK = txtPK.getText();
                    setVisible(false);
                    setDefault();
                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                setDefault();
            }
        });

        
    }

    protected void setDefault() {
        txtPK.setText(null);
    }

    
    public void desplegarForm() {
        desplegar = true;
        setContentPane(JpanelPK);
        setTitle("INGRESO DATO DE LA PRIMARY KEY");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lblPK.setText("INGRESE " + textPK);
        setVisible(true);
        setResizable(true);
    }

    public String getValuePK() {
        return valuePK;
    }

    public void setTextPK(String textPK) {
        this.textPK = textPK;
    }
}
