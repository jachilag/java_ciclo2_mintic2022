package modeloGrafico.crearCliente;

//importaciones clase entidad
import myJDBC.MyJDBC;

import static app.funProp.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import modeloGrafico.login.formLogin;
// import modeloGrafico.login.formLogin.DATO;
// import modeloGrafico.login.formLogin.ENTIDAD;
// import modeloGrafico.login.formLogin.NEED;
// import modeloGrafico.login.formLogin.NEED_PSWD;

//importaciones Jswing
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CrearCliente extends JFrame{
    private JTextField tfAlias;
    private JTextField tfNombre;
    private JTextField tfTelefono;
    private JPasswordField pfPassword;
    private JCheckBox chBoxPswrd;
    private JTextField tfApellido;
    public JButton btnRegresar;
    private JButton btnCrear;
    private JLabel lblTitulo;
    private JLabel lblAlias;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblTelefono;
    private JLabel lblPassword;
    private JPanel JpanelCrear;
    private JTextArea taRequisitosPswrd;
    private JButton btnLimpiar;

    //atributos clase entidad
    protected MyJDBC myJDBC;
    protected DATO dato;
    protected String tabla;
    protected ArrayList<String[]> consulta;
    protected String alias;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String contrasena;
    protected String queryCreate;
    protected String valuePK;
    protected String queryRequest;
    protected String forString;
    protected ENTIDAD entidad;
    public boolean desplegar;


    //CONSTRUCTOR
    public CrearCliente() {
        myJDBC = new MyJDBC("grupo53", "grupo53", "Grupo53Ciclo2*");
        myJDBC.ConnectionMyDB();
        desplegar = false;

        // ocultar o mostrar la contraseña
        chBoxPswrd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {ShowPassword();}
        });

        //crea usuario con los datos ingresados
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {createEntity();}
        });

        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDefault();
            }
        });

        pfPassword.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    createEntity();
                    setDefault();
                    setVisible(false);}
            }
        });
   
        //regresa el control a la ventana del login a partir de la ventana crear cliente
        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDefault();
                setVisible(false);}
        });
    }


    /*========================================================================================================= */
    //METODOS DE LA CLASE
    public void desplegarForm(){
        desplegar = true;
        setContentPane(JpanelCrear);
        setTitle("CREAR USUARIO");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setUndecorated(false);   // elimina los botones de cerrar y minimizar de la pantalla
        setResizable(true);
        setVisible(true);
        taRequisitosPswrd.setText("Para crear su contrasena debe cumplir las siguientes condiciones:"+ "\n" +
                    "1. longitud minima de 6 y maximo de 20 caracteres" + "\n" +
                    "2. debe contener letras mayusculas y minusculas" + "\n" +
                    "3. debe contener numeros" + "\n" +
                    "4. puede contener unicamente los siguientes simbolos: /*+#$%&?¡¿" + "\n" +
                    "5. SU CONTRASENA NO SERA VISIBLE EN CONSOLA; CUANDO LA TERMINE DE DIGITAR PULSE INTRO" 
                    );
    }

    public boolean createEntity() {
        //VALIDA NO EXISTENCIA DE USUARIO EN LA BD
        formLogin login = new formLogin();
        String alias = login.requestEntity(NEED.NOT_EXIST, tfAlias.getText(), "'");
        if(alias != null){
            String nombre = tfNombre.getText();;
            String apellido = tfApellido.getText();
            String telefono = tfTelefono.getText();

            //VALIDAR CONTRASEÑA
            String contrasena = login.requestContrasena(NEED_PSWD.CREATE, pfPassword.getPassword());
            if(contrasena != null){
                queryCreate = "INSERT INTO clientes(alias, nombre, apellido, telefono, contrasena) VALUES ('"+ 
                alias +"','" +nombre +"','" + apellido +"','" + telefono +"','" + contrasena +"')";
                if(myJDBC.CREATE(queryCreate)){
                    JOptionPane.showMessageDialog(null,"\n\nREGISTRO CREADO");} 
            }
        }
        return true;
    }

      //ocultar contraseña
    public void ShowPassword() {
        if (chBoxPswrd.isSelected()) {pfPassword.setEchoChar((char) 0);} 
        else {pfPassword.setEchoChar('*');}
    }

    //deja vacio los campos de llenado del formulario
    public void setDefault() {
        tfAlias.setText(null);
        tfApellido.setText(null);
        tfNombre.setText(null);
        tfTelefono.setText(null);
        chBoxPswrd.setSelected(false);
        pfPassword.setText(null);
    }

}
