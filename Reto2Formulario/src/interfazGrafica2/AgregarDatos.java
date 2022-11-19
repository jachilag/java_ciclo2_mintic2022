package interfazGrafica2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarDatos extends JFrame{
    public JPanel JpanelAgregar;
    public JTextField txtNombre;
    public JTextField txtEdad;
    private JRadioButton RbtnPregrado;
    private JRadioButton RbtnPosgrado;
    public JComboBox CboxCarrera;
    public JTextField txtCreditos;
    public JComboBox CBoxModalidad;
    private JLabel lblTipoEstudiante;
    private JLabel lblNombre;
    private JLabel lblEdad;
    private JLabel lblCarrera;
    private JLabel lblEtnia;
    private JLabel lblCreditos;
    private JLabel lblModalidad;
    public JComboBox CBoxEtnia;
    public JButton btnAgregar;
    public JButton btnRegresar;
    private JButton btnLimpiar;
    public ButtonGroup GbtntipoEstudiante;

    public String[] listaVacia;
    private String[] listaCarreraPregrado;
    private String[] listaCarreraPosgrado;
    private String[] listaEtnia;
    private String[] listaModalidad;
    private String salida;
    private String tipo;
    private String nombre;
    private String edad;
    private String carrera;
    private String etnia;
    private String creditos;
    private String modalidad;


    public AgregarDatos(){
        //inicializacion de atributos globales

        //set del panel principal
        setContentPane(JpanelAgregar);
        setTitle("DATOS DE ESTUDIANTE");
        setSize(800,400);
        setLocationRelativeTo(null);
        setResizable(true);

        //grupo de botones radioButton seleccion de progrado o posgrado
        GbtntipoEstudiante = new ButtonGroup();
        GbtntipoEstudiante.add(RbtnPosgrado);
        GbtntipoEstudiante.add(RbtnPregrado);
        RbtnPregrado.setActionCommand("Pregrado");
        RbtnPosgrado.setActionCommand("Posgrado");

        //comBox datos
        listaVacia = new String[]{};
        listaCarreraPregrado = new String[]{"Elija una carrera de Pregrado","Medicina","Derecho","Biologia","Ingenieria Electrica","Ingenieria Sistemas","Bellas Artes","Matematicas","Administracion Empresas"};
        listaCarreraPosgrado = new String[]{"Elija una carrera Posgrado","Maestria en Medicina","Maestria en Derecho","Maestria en Biologia","Maestria en Ingenieria","Maestria en Bellas Artes","Maestria en Matematicas","Maestria en Administracion Empresas"};
        listaEtnia = new String[]{"Ninguna", "Afrodescendiente", "Indigena", "Raizal", "Palenquero", "Gitano"};
        listaModalidad = new String[]{"Elija una modalidad de grado", "Investigacion", "Profundizacion", "Tesis", "Pasantia", "Publicacion"};

        //event listeners
        RbtnPregrado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CboxCarrera.removeAllItems();
                comboBoxOperations(CboxCarrera, listaCarreraPregrado);
                comboBoxOperations(CBoxModalidad, listaVacia);
                CBoxModalidad.setEnabled(false);
                txtCreditos.setEditable(true);
            }});
            
        RbtnPosgrado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CboxCarrera.removeAllItems();
                comboBoxOperations(CboxCarrera, listaCarreraPosgrado);
                CBoxModalidad.setEnabled(true);
                comboBoxOperations(CBoxModalidad, listaModalidad);
                txtCreditos.setEditable(false);
            }});
        
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {valoresPorDefecto();}
        });
    }

	public String datoEstudiante() {
        salida = "1&";
        salida += tipo + "&";
        salida += nombre + "&";
        salida += edad + "&";
        salida += carrera+ "&";
        salida += etnia+ "&";
        salida += (tipo.equals("Pregrado"))?creditos:modalidad;
        return salida;
	}

    public boolean verificarDatosCorrectos() {
        boolean bandera = true;
        
        try {
            tipo = GbtntipoEstudiante.getSelection().getActionCommand()+"";
            nombre = txtNombre.getText();
            edad = txtEdad.getText();
            carrera = (String) CboxCarrera.getSelectedItem();
            etnia = (String) CBoxEtnia.getSelectedItem();
            creditos = txtCreditos.getText();
            modalidad = (String) CBoxModalidad.getSelectedItem();

            bandera &= (tipo.equals("Pregrado") || tipo.equals("Posgrado"));
            bandera &= (tipo.length()!=0);
            bandera &= (nombre.length()!=0) ;
            bandera &= (edad.length()!=0) ;
            bandera &= edad.matches("[0-9]+");
            bandera &= (carrera.length()!=0);
            bandera &= (!carrera.equals(listaCarreraPosgrado[0])&&(!carrera.equals(listaCarreraPregrado[0])));
            bandera &= (etnia.length()!=0) ;
            bandera &= (tipo.equals("Pregrado") && creditos.matches("[0-9]+")) || 
                ((tipo.equals("Posgrado")) && (!modalidad.equals(listaModalidad[0])));
        } catch (Exception e) {
            bandera = false;
        }
        return bandera;
    }

    public void comboBoxOperations(JComboBox comboBox, String[] lista){
        for (String item: lista) comboBox.addItem(item);
    }

    public void valoresPorDefecto() {
        GbtntipoEstudiante.clearSelection(); //limpia seleccion del grupo de botones
        txtNombre.setText("");  //vacio el campo de texto nombre
        txtEdad.setText("");    //vacio el campo de edad
        txtCreditos.setText("");    //vacio el campo de creditos
        CboxCarrera.removeAllItems(); //quita todos los datos del combobox
        CBoxEtnia.removeAllItems(); //quita todos los datos del combobox
        CBoxModalidad.removeAllItems(); //quita todos los datos del combobox
        comboBoxOperations(CboxCarrera, listaVacia);    //sin opciones para combbox carrera
        comboBoxOperations(CBoxEtnia, listaEtnia);  //opciones por defecto para Combobox etnia
        comboBoxOperations(CBoxModalidad, listaVacia);  // sin opciones para combobox modalidad
        CBoxModalidad.setEnabled(false);  //desactivacion del combobox modalidad para no edicion
        txtCreditos.setEditable(false); //desactivacion del textbox de creditos para evitar edicion
    }
}
