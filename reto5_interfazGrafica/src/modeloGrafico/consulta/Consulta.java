package modeloGrafico.consulta;

import static app.funProp.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Consulta  extends JFrame{
    private JRadioButton rbMoto;
    private JRadioButton rbBicicleta;
    private JComboBox cboxFabricante;
    private JComboBox cBoxAlias;
    private JTextArea txtAreaConsulta;
    private JButton btnLimpiar;
    private JButton btnConsultar;
    private JButton btnSalir;
    private JPanel JpaneConsulta;
    private JLabel lblTitulo;
    private String alias;
    public boolean desplegar;
    public ButtonGroup grupoBtnFabricantes;
    private String[] listaVacia; 
    private String[] listaBicicletas;
    private String[] listaMotos;
    private String[] listaClientes;
    private String fabricante;
    private ENTIDAD entidad;


    public Consulta() {
        listaVacia = new String[]{};
        desplegar = false;
        alias = null;

        //acciones al presionar el boton salir
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {setVisible(false);setDefault();}
        });

        //acciones del boton limpiar
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDefault();
            }
        });
    
        //acciones del boton consultar
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarConsulta();
            }
        });
        
        //acciones boton moto
        rbMoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionesBotonMoto();
            }
        });

        //acciones boton bicicleta
        rbBicicleta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionesBotonBicicleta();
            }
        });
    
    }

    protected void accionesBotonBicicleta() {
        cboxFabricante.removeAllItems();
        entidad = ENTIDAD.BICICLETA;
        listaBicicletas = arraylistToList(menuConsultarGeneral(entidad, "bicicletas", "fabricante"), 0);
        comboBoxOperations(cboxFabricante, listaBicicletas);
    }
    
    protected void accionesBotonMoto() {
        entidad = ENTIDAD.MOTO;
        listaMotos = arraylistToList(menuConsultarGeneral(entidad, "motocicletas_electricas", "fabricante"), 0);
        cboxFabricante.removeAllItems();
        comboBoxOperations(cboxFabricante, listaMotos);
    }

    protected void realizarConsulta() {
        fabricante = (String) cboxFabricante.getSelectedItem();
        String fecha = TimeNow();
        alias = (alias == null)?(String) cBoxAlias.getSelectedItem():alias;
        myJDBC.CREATE("INSERT INTO intencion_compra(cliente,fabricante,tipo,fecha) VALUES ('"+alias+"','"+fabricante+"','"+entidad+"','"+fecha+"')");
        String textoConsulta = "CONSULTA REALIZADA POR: " + alias + " \n\n";
        textoConsulta += "FABRICANTE: " + fabricante + "\n";
        textoConsulta += "TIPO: " + entidad + "\n";
        textoConsulta += "FECHA: " + fecha + "\n";
        textoConsulta += repetNtimes("*", 33) + "\n";
        txtAreaConsulta.setText(textoConsulta);
    }

    protected void setDefault() {
        alias = null;
        grupoBtnFabricantes.clearSelection();
        cboxFabricante.removeAllItems();
        comboBoxOperations(cboxFabricante, listaVacia);
        cBoxAlias.removeAllItems();
        comboBoxOperations(cBoxAlias, listaVacia);
    }

    public void desplegarForm() {
        desplegar = true;
        setContentPane(JpaneConsulta);
        setTitle("VENTANA CLIENTE " + alias);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(true);

        grupoBtnFabricantes = new ButtonGroup();
        grupoBtnFabricantes.add(rbBicicleta);
        grupoBtnFabricantes.add(rbMoto);
    }

    public void comboBoxAlias(boolean activar){
        if(activar){
            cBoxAlias.setEnabled(true);
            listaClientes = arraylistToList(menuConsultarGeneral(ENTIDAD.CLIENTE, "clientes", "alias"), 0);
            cBoxAlias.removeAllItems();
            comboBoxOperations(cBoxAlias, listaClientes);
        }
        else{cBoxAlias.setEnabled(false);}
    }

    public void comboBoxOperations(JComboBox comboBox, String[] lista){
        for (String item: lista) comboBox.addItem(item);
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
