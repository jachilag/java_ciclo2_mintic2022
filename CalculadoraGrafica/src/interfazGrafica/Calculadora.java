package interfazGrafica;

import operaciones.Operaciones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame{
//atributos
    private JTextField textFieldValue2;
    private JLabel lbl1;
    private JButton CALCULARButton;
    private JComboBox comboBox1;
    private JPanel panelPrincipal;
    private JLabel lblValor1;
    private JLabel lblValor2;
    private JTextField textFieldValue1;
    private JLabel lblResultado;
    private String[] listOperations;

//constructor
    public Calculadora() {
        listOperations = new String[]{"Elija una opcion","Sumar", "Restar", "Multiplicar", "Dividir"};

        //caracteristicas del panel principal
        setContentPane(panelPrincipal);
        setTitle("calculadora".toUpperCase());
        setSize(500,400);
        setLocation(350,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        comboBoxOperations();
        setVisible(true);
        setResizable(false);

        CALCULARButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AccionesElejirOperacion();}
        });

        comboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AccionesElejirOperacion();}
        });

    }

    //metodos
    public void AccionesElejirOperacion(){
        String operacion = (String) comboBox1.getSelectedItem();
        double valor1 = Double.valueOf(textFieldValue1.getText());
        double valor2 = Double.valueOf(textFieldValue2.getText());
        Operaciones operaciones = new Operaciones(valor1,valor2);
        switch (operacion){
            case "Sumar":
                lblResultado.setText(operaciones.sumar()+"");
                break;

            case "Restar":
                lblResultado.setText(operaciones.restar()+"");
                break;

            case "Multiplicar":
                lblResultado.setText(operaciones.multiplicar()+"");
                break;

            case "Dividir":
                lblResultado.setText(operaciones.dividir()+"");
                break;

            default:
                break;
        }
    }



    public void comboBoxOperations(){
        for (String item: listOperations) comboBox1.addItem(item);
    }




}
