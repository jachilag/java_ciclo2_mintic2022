package interfazGrafica.grafica1;

import javax.swing.*;

import logica.Registro;

public class ImprimirTodo extends JFrame{
    private JLabel lblTitulo;
    private JPanel JpanelImpresion;
    private JTextArea JTlistaEstudiantes;
    private Registro registro;

    public ImprimirTodo(Registro registro){
        //inicializacion de variables globales
        this.registro = registro;

        //set de caracteristicas del frame
        setContentPane(JpanelImpresion);
        setTitle("LISTA DE ESTUDIANTES DE LA UNIVERSIDAD");
        setSize(600,700);
        setLocation(200,0);
        obtenerDatosenPantalla();
        setVisible(true);
        setResizable(true);
    }

    private void obtenerDatosenPantalla() {
        JTlistaEstudiantes.setText(registro.listarEstudiantes());
    }
}
