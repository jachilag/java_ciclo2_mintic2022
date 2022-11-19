package modeloGrafico.login;

//importaciones clase entidad
import myJDBC.MyJDBC;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import modeloGrafico.tablasConsulta.TablasConsulta;
import modeloGrafico.consulta.Consulta;
import modeloGrafico.crearCliente.CrearCliente;

import static app.funProp.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class formLogin extends JFrame {
    //atributos del form
    private JPanel JpanelLogin;
    private JLabel JlabelLogin;
    private JPasswordField pswrdUsuario;
    private JTextField txtUsuario;
    private JButton btnCargar;
    private JButton btnCrearUsuario;
    private JLabel JlabelUsuario;
    private JLabel JlabelPswrd;
    private JCheckBox checkBoxPswrd;
    private JButton btnCreditos;

    //atributos de la logica
    protected MyJDBC myJDBC;
    private USUARIO perfil;
    protected DATO dato;
    protected ENTIDAD entidad;
    protected String tabla;
    protected ArrayList<String[]> consulta;
    protected String alias;
    

    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String contrasena;
    protected String queryCreate;
    protected String primaryKey;
    protected String valuePK;
    protected String queryRequest;
    protected String forString;


    //atributos de otros form
    public CrearCliente crearCliente;
    public Consulta intencion;
    public TablasConsulta administrador;

    //CONSTRUCTOR
    public formLogin() {
        myJDBC = new MyJDBC("grupo53", "grupo53", "Grupo53Ciclo2*");
        myJDBC.ConnectionMyDB();
        primaryKey = null;
        setDatos();
        tabla = "clientes";
        valuePK = "alias";
        entidad = ENTIDAD.CLIENTE;
        crearCliente = new CrearCliente();
        administrador = new TablasConsulta();
        intencion = new Consulta();


        // listener para ocultar o mostrar contraseña
        checkBoxPswrd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {ShowPassword();}
        });

        //abre la ventana para crear usuario
        btnCrearUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(crearCliente.desplegar){crearCliente.setVisible(true);}
                else{crearCliente.desplegarForm();}
            }
        });

        //muestra en pantalla quien realizo el proyecto
        btnCreditos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Creditos creditos = new Creditos();
            }
        });

        //carga el perfil creado o el del administrador.
        btnCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logUser();
            }
        });

        //retorna el control a la ventana login desde la ventana administrador
        administrador.btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                administrador.setVisible(false);
            }
        });

        // key listener para cuando se digite la contraseña dar enter, carga el perfil automaticamente
        pswrdUsuario.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){logUser();}
            }
        });
    }

    //METODOS DE LA CLASE
    public void desplegarForm(){
        setContentPane(JpanelLogin);
        setTitle("EcoDosRuedas Ltda");
        setSize(600, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
    }

    public void logUser() {
        String usuario;
        
        //VALIDAR USUARIO
        forString = "'";
        usuario = requestEntity(NEED.EXIST, txtUsuario.getText(), "'");
        if (usuario == null) {return;}
        primaryKey = usuario;
        setDatos();
  
        //VALIDAR CONTRASEÑA
        contrasena = requestContrasena(NEED_PSWD.VALIDATE, pswrdUsuario.getPassword());
        if(contrasena == null){ return;}

        //ENTRAR A PERFIL CORRESPONDIENTE
        alias = usuario;
        perfil = getPerfil();
        switch (perfil) {
            case ADMINISTRADOR:
                setVisible(false);
                if(administrador.desplegar){administrador.setVisible(true);}
                else{administrador.desplegarForm();}
                break;
            case CLIENTE:
                intencion.setAlias(primaryKey);
                if(intencion.desplegar){intencion.setVisible(true);}
                else{intencion.desplegarForm();}
                intencion.comboBoxAlias(false);
                break;
            default:
                break;
        }
        setDefault();
    }

    public String requestEntity(NEED need, String dato, String forString){
        boolean valPK;
            primaryKey = dato;
            String pk2 = forString + primaryKey + forString;
            ArrayList<String[]> consulta = myJDBC.READ_as_ARRAY("SELECT "+valuePK+" FROM "+tabla+" WHERE "+valuePK+" = BINARY "+ pk2 +";");
            valPK = (consulta.size()==0)?false:true;
            switch (need) {
                case EXIST://caso cuando necesito que nickname exista. por ejemplo para VALIDAR 
                    if (!valPK) {JOptionPane.showMessageDialog(null,"\nUsuario NO existe "+"\n"+"Intente con un usuario valido"); 
                    primaryKey = null;}
                    break;
                case NOT_EXIST://caso cuando nesecito que NO exista el nickname. por ejempo para CREAR
                    if (valPK) {JOptionPane.showMessageDialog(null,"\nUsuario YA existe"+ "\n"+ "Intente con otro nickName");
                    primaryKey = null;}
                    break;
            }
        return primaryKey;
    }

    public String requestContrasena(NEED_PSWD need, char[] cs){
        String contrasena = "";
        switch (need) {
            case CREATE://caso cuando necesito crear contraseña y que cumpla requisitos minimos
                contrasena = ChtoString(cs);
                if (!validarRestriccionesPswd(contrasena)) {
                    JOptionPane.showMessageDialog(null,"\nContrasena NO cumple los requisitos\n");
                    contrasena = null;}
                break;

            case VALIDATE://caso cuando nesecito validar si la contraseña 
                String digitedPassword = ChtoString(cs);
                if (!validarContrasena(alias, digitedPassword)) {
                    JOptionPane.showMessageDialog(null,"\nContrasena NO valida\n");
                    contrasena = null;  primaryKey = null; setDatos();}
                break;
        }
        return contrasena;
    }

    // convierte el arreglo de la contraseña en string para poder ser comparado con el de la BD
    private String ChtoString(char[] password) {
        String salida = "";
        for (char c : password) {salida += c;}
        return salida;
    }

    private boolean validarContrasena(String usuario, String contrasena) {//100%
        consulta = myJDBC.READ_as_ARRAY("SELECT contrasena FROM clientes WHERE alias = BINARY '"+ usuario +"';");
        boolean salida = (contrasena.equals(consulta.get(0)[0]))?true:false; 
        consulta.clear();
        return salida;
    }

    private boolean validarRestriccionesPswd(String contrasena) {//100%
        boolean salida = true;
        salida &= contrasena.length()>=6;
        salida &= contrasena.matches("[a-zA-Z0-9/*+#$%&?¡¿)]+");
        return salida;
    }


    //establece los valores en las variables globales
    protected boolean setDatos(){
        boolean salida = true;
            if(primaryKey == null){entidad = ENTIDAD.NULO;}
            else {entidad = ENTIDAD.CLIENTE;this.perfil = getPerfil();}
            switch (entidad) {
                case NULO:
                this.alias = null;
                this.nombre = null;
                this.apellido = null;
                this.telefono = null;
                this.contrasena = null;
                break;
                case CLIENTE:
                try {
                    consulta = myJDBC.READ_as_ARRAY("SELECT*FROM "+tabla+" WHERE "+valuePK+"='"+primaryKey+"';");
                    this.alias = primaryKey;
                    this.nombre = consulta.get(0)[1];
                    this.apellido = consulta.get(0)[2];
                    this.telefono = consulta.get(0)[3];
                    this.contrasena = consulta.get(0)[4];
                } catch (Exception e) {salida = false;}
                break;
                default:
                    break;
            }
        return salida;
    }

    public USUARIO getPerfil() {
        if (alias == null){perfil = USUARIO.NO_IDENTIFICADO;}
        else if (alias.equals("admin")) {perfil = USUARIO.ADMINISTRADOR;} 
        else{perfil = USUARIO.CLIENTE;}
        return perfil;
    }

    public void ShowPassword() {
        if (checkBoxPswrd.isSelected()) {pswrdUsuario.setEchoChar((char) 0);} 
        else {pswrdUsuario.setEchoChar('*');}
    }

    public String getAlias() {
        return alias;
    }

    public void setDefault(){
        txtUsuario.setText(null);
        pswrdUsuario.setText(null);
        checkBoxPswrd.setSelected(false);
    }
}
