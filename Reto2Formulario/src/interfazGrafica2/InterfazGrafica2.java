package interfazGrafica2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import interfazGrafica.grafica1.ImprimirTodo;
import logica.Estudiante;
import logica.Posgrado;
import logica.Registro;

public class InterfazGrafica2 extends JFrame{
    //ATRIBUTOS
    private JPanel MainPanel;
    private JTable tablePregrado;
    private JTable tablePosgrado;
    private JButton btnAgregar;
    private JButton btnImprimir;
    private JButton btnLimpiar;
    private JLabel lblPregrado;
    private JLabel lblPosgrado;
    private JScrollPane jscroll1;
    private JScrollPane jscroll2;

    private String[] HeaderPregrado;
    private String[] HeaderPosgrado; 
    private int colPregrado; 
    private int colPosgrado; 
    private String[][] DatosPregrado;
    private String[][] DatosPosgrado;
    private DefaultTableModel modelPregrado; // para inicializar y finalizar la tabla
    private DefaultTableModel modelPosgrado; // para inicializar y finalizar la tabla
    Registro registro;
    ImprimirTodo imprimir;
    AgregarDatos agregarDatos;


//CONSTRUCTOR
public InterfazGrafica2(){
    //inicializacion de atributos publicos
    HeaderPregrado = new String[]{ "Nombre", "Edad", "Carrera", "Etnia", "Creditos"};
    HeaderPosgrado = new String[]{ "Nombre", "Edad", "Carrera", "Etnia", "Modalidad"};
    colPregrado = HeaderPregrado.length;
    colPosgrado = HeaderPosgrado.length;
    registro = new Registro(); 
    agregarDatos = new AgregarDatos();

    //caracteristicas del panel principal
    setContentPane(MainPanel);
    setTitle("FORMULARIO INGRESO DE ESTUDIANTES");
    setSize(700,500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
    setResizable(true);
    agregarDatos.setVisible(false);
    actualizarTablas();

    //event listeners
    btnAgregar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            agregarDatos.setVisible(true);
            agregarDatos.valoresPorDefecto();
        }});

    btnLimpiar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {AccionesLimpiarDatos();}
    });

    btnImprimir.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {AccionesImprimir();}
    });

    agregarDatos.btnAgregar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {AccionesAgregarEstudiante();}
    });

    agregarDatos.btnRegresar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {agregarDatos.setVisible(false);}
    });
}

//METODOS
private void AccionesImprimir() {
    imprimir = new ImprimirTodo(registro);
}

private void AccionesAgregarEstudiante(){
    if(!agregarDatos.verificarDatosCorrectos()){
        JOptionPane.showMessageDialog(agregarDatos.JpanelAgregar, "Datos Incompletos o Incorrectos");
    } else {
        String dato = agregarDatos.datoEstudiante();
        registro.agregarEstudiante(dato);
        actualizarTablas();
        JOptionPane.showMessageDialog(agregarDatos.JpanelAgregar, "Estudiante agregado");
        agregarDatos.valoresPorDefecto();
    }
}

private void AccionesLimpiarDatos() {
    registro.limpiarDatos();
    actualizarTablas();
}

public void actualizarTablas() {
    TablaPosgrado();
    TablaPregrado();
}

private void TablaPosgrado() {
    if (registro.getListaPosgrado().size()==0) {
        DefaultTableModel modelPosgrado = new DefaultTableModel(DatosVacios(), HeaderPosgrado);
        tablePosgrado.setModel(modelPosgrado);
    } else {
        DatosPosgrado = toArrayString(registro.getListaPosgrado(), colPosgrado);
        modelPosgrado = new DefaultTableModel(DatosPosgrado, HeaderPosgrado);
        tablePosgrado.setModel(modelPosgrado);
    }
}

private void TablaPregrado() {
    if (registro.getListaPregrado().size()==0) {
        DefaultTableModel modelPregrado = new DefaultTableModel(DatosVacios(), HeaderPregrado);
        tablePregrado.setModel(modelPregrado);
    } else {
        DatosPregrado = toArrayString(registro.getListaPregrado(), colPregrado);
        modelPregrado = new DefaultTableModel(DatosPregrado, HeaderPregrado);
        tablePregrado.setModel(modelPregrado);
    }
}

private String[][] toArrayString(ArrayList<Estudiante> datosEstudiantes, int columnas) {//pasa de un ArrayList a un array
    String[][] salida = new String[datosEstudiantes.size()][columnas];
    int cont = salida.length-1;
    for (Estudiante estudiante: datosEstudiantes) {//iteracion por filas o estudiante
        for (int i = 0; i < salida.length; i++) {//iteracion por columna
            salida[cont][0] = estudiante.getNombre();
            salida[cont][1] = estudiante.getEdad()+"";
            salida[cont][2] = estudiante.getPrograma();
            salida[cont][3] = estudiante.getTipo_etnia();
            salida[cont][4] = (estudiante instanceof Posgrado)?estudiante.getModalidad():(String.valueOf(estudiante.getCantidad_creditos()));
        }
        cont--;
    }
    return salida;
}

public String[][] DatosVacios() {
    return new String[][]{{}};
}
}
