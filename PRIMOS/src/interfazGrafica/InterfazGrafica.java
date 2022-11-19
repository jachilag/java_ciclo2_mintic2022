package interfazGrafica;

import primos.hija.testPrimes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class InterfazGrafica extends JFrame{
    private JTextField textField1;
    private JButton btnLimpiar;
    private JButton btnCalcular;
    private JTable table1;
    private JPanel panelPrincipal;
    private JLabel lblNumero;
    private JLabel lblTitulo;
    private JLabel Copyright;

    protected String[] Header;//nombres de los encabezados de las columnas de la tabla
    protected String[] data; //
    protected String[][] vectorData; //contiene los datos del vector
    private Vector<String[]> vector;
    private testPrimes pruebaPrimos; //instancia de objeto de la parte logica
    private DefaultTableModel model; // para inicializar y finalizar la ventana

    //CONSTRUCTOR
    public InterfazGrafica(){
        Header = new String[]{"Numero", "Primo","Lista de Primos", "Ultimo Primo","Total de Primos"};
        vector = new Vector<>();

        //caracteristicas del panel o ventana principal
        setContentPane(panelPrincipal);
        setTitle("programa para numeros primos".toUpperCase());
        setSize(900,340);
        setLocation(350,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //cierra la ventana cuando se da clic en cerrar
        setVisible(true);   //hace visible la ventana en la pantalla
        setResizable(false);    //evita que se pueda modificar el tamaño de la ventana
        Tabla();    //llamado de la funcion que proyecta la tabla en la ventana

        textField1.addActionListener(new ActionListener() {//al dar enter realiza las mismas funcionalidades del boton calcular
            public void actionPerformed(ActionEvent e) {AccionesBotonCalcular();}
        });
        btnCalcular.addActionListener(new ActionListener() {//acciones del boton calcular
            public void actionPerformed(ActionEvent e) {AccionesBotonCalcular();}
        });
        btnLimpiar.addActionListener(new ActionListener() {//acciones del boton limpiar
            public void actionPerformed(ActionEvent e) {AccionesBotonLimpiar();}
        });
    }

    private void AccionesBotonLimpiar() {
        vector.clear();
        textField1.setText("");
        Tabla();
    }

    private void AccionesBotonCalcular() {
        if(!(textField1.equals(""))) {
            pruebaPrimos = new testPrimes(Integer.valueOf(textField1.getText()));//lee el valor en el textfield
            data = new String[]{""+pruebaPrimos.getNum(),
                                ""+pruebaPrimos.itIsPrime(),
                                ""+pruebaPrimos.listPrimes(),
                                ""+pruebaPrimos.lastPrime(),
                                ""+pruebaPrimos.listSize()
                                };
            vector.add(data);
            Tabla();
        }

    }

    //proyecta los datos en la tabla de la ventana
    public void Tabla(){
        if(vector.size()==0){
            DefaultTableModel model = new DefaultTableModel(Data(), Header);
            table1.setModel(model);
        } else {
            vectorData = new String[vector.size()][Header.length];

            //se usa el for para almacenar los datos en el vectorData
            for (int i = 0; i < vector.size(); i++) {
                for (int j = 0; j < Header.length; j++) {
                    vectorData[i][j] = String.valueOf(vector.get(i)[j]); //almacena los datos
                }
            }

            model = new DefaultTableModel(vectorData, Header);  //genera el objeto tabla
            table1.setModel(model);
        }
    }

    //crea matriz de datos vacia, con dos filas de datos vacia
    public String[][] Data(){
        return new String[][]{{},{}};
    }

}
