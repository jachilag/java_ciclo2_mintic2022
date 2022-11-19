package interfazGrafica;

import primos.hija.PruebaPrimos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class InterfazGrafica extends JFrame{
    public JTextField textField1;
    public JButton btnCalcular;
    public JButton btnClear;
    public JTable table1;
    public JLabel lblTitle;
    public JLabel lblNumero;
    public JPanel panelPrincipal;
    public JLabel copyright;

    protected String[] Header;
    protected String[] data;
    protected String[][] vectorData;
    private Vector<String[]> vector;
    private PruebaPrimos pruebaPrimos;
    private DefaultTableModel model;

    public InterfazGrafica(){
        Header = new String[]{"Número", "Primo", "lista de primos", "último primo","Total de primos"};
        data = new String[]{};
        vector = new Vector<>();
        vectorData = new String[][]{};
        setContentPane(panelPrincipal);
        setTitle("Programa para calcular números primos");
        setSize(750, 340);
        setLocation(350,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        Tabla();
        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AccionesBotonCalcular();
            }
        });
    }

    public void AccionesBotonCalcular(){
        if(!textField1.equals("")){
            PruebaPrimos pruebaPrimos = new PruebaPrimos(Integer.parseInt(textField1.getText()));
            data = new String[]{String.valueOf(pruebaPrimos.getNumero()),
                    String.valueOf(pruebaPrimos.isPrimo()),
                    String.valueOf(pruebaPrimos.listaPrimos()),
                    String.valueOf(pruebaPrimos.MasCercano()),
                    String.valueOf(pruebaPrimos.ListaSize())
            };
            vector.add(data);
            Tabla();
        }
    }

    public void Tabla(){
        if(vector.size()==0){
            model = new DefaultTableModel(Data(), Header);
            table1.setModel(model);
        }else{
            vectorData = new String[vector.size()][Header.length];
            /*Este for saca los valores del vectorData*/
            for(int i =0; i< vector.size(); i++){
                for(int j=0; j<Header.length; j++){
                    vectorData[i][j] = String.valueOf(vector.get(i)[j]);
                }
            }

            for(int i =0; i<vector.size();i++){
                for(int j=0; j<Header.length; j++){
                    System.out.println(vector.get(i)[j]);
                }
            }
            model = new DefaultTableModel(vectorData, Header);
            table1.setModel(model);
        }
    }

    public String[][] Data(){
        String[][] data = new String[][]{{},{}};
        return data;
    }


}
