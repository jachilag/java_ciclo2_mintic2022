package user;

import static app.funProp.*;
import java.util.ArrayList;
import app.Entidad;
import java.io.Console;
import user.Administrador.ENTIDAD;

public class User extends Entidad{
    //ATRIBUTOS
    public enum NEED_PSWD{CREATE,VALIDATE};
    public enum USUARIO{ADMINISTRADOR,CLIENTE,NO_IDENTIFICADO};
    private USUARIO perfil;

    
    //CONSTRUCTOR
    public User() {
        super();
        primaryKey = null;
        setDatos();
        tabla = "clientes";
        valuePK = "alias";
        entidad = ENTIDAD.CLIENTE;
    }

    //METODOS

    /**
     * metodo que crea un nuevo cliente y lo sube a la BD.
     * este se usa desde el login y desde el perfil del administrador
     * @return 
     */
    @Override
    public boolean createEntity() { //100%
        //VALIDA NO EXISTENCIA DE USUARIO EN LA BD
        String alias = requestEntity(NEED.NOT_EXIST);
        primaryKey = alias;
        if(alias != null){
            String nombre = Input("Nombre: ");
            String apellido = Input("Apellido: ");
            String telefono = Input("Telefono: ");

            //VALIDAR CONTRASEÑA
            String contrasena = requestContrasena(NEED_PSWD.CREATE);
            if(contrasena != null){
                queryCreate = "INSERT INTO clientes(alias, nombre, apellido, telefono, contrasena) VALUES ('"+ 
                alias +"','" + nombre +"','" + apellido +"','" + telefono +"','" + contrasena +"')";
                primaryKey = (super.createEntity())?primaryKey:null;
                setDatos(); //establece los atributos de la llave primaria
            }
        }
        return true;
    }
    
    /**
     * metodo que valida la existencia de un usuario en la BD.
     * si se valida la existencia de usuario, entonces los atributos toman el valor de dicho usuario;
     * si no encuentra dicho usuario, los atributos quedan seteados como nulos.
     * Se usa desde el login
     */
    public void logUser() {//100%
        String usuario;
        
        //VALIDAR USUARIO
        usuario = requestEntity(NEED.EXIST);
        if (usuario == null) {return;}
        primaryKey = usuario;
        setDatos();
  
        //VALIDAR CONTRASEÑA
        contrasena = requestContrasena(NEED_PSWD.VALIDATE);
        if(contrasena == null){System.out.println("\nUSUARIO NO VALIDADO"); return;}
        System.out.println("\nUSUARIO VALIDADO");
    }

    /**
     * submenu que solicita ingreso de nickname y valida si este existe o no en la BD.
     * aplica para los casos en que necesite la existencia del nickname, por ejemplo para realizar querys, y 
     * tambien para los casos en que No necesecito que exista, por ejemplo para creacion de usuario nuevo.
     * @return en caso que no cumpla con lo necesitado en el parametro de entrada, retornara nulo
     */
    @Override
    public String requestEntity(NEED need){//100%
        forString = "'";
        textRequest = "Ingrese NickName: ";
        return super.requestEntity(need);
    }

    /**
     * metodo que solicita el ingreso de dato para crear o validar una contraseña
     * @param need CREATE, VALIDATE
     * @return devuelve la contraseña si cumple las condiciones. si no cumple regresa nulo.
     */
    public String requestContrasena(NEED_PSWD need){//100%
        String contrasena = "";
        boolean continuar = true;
        do {
            switch (need) {
                case CREATE://caso cuando necesito crear contraseña y que cumpla requisitos minimos
                    System.out.println(repetNtimes("-", 50));//imprime linea divisoria en consola
                    System.out.println("Para crear su contrasena debe cumplir las siguientes condiciones:"+
                    "\n1. longitud minima de 6 y maximo de 20 caracteres" +
                    "\n2. debe contener letras mayusculas y minusculas" +
                    "\n3. debe contener numeros" +
                    "\n4. puede contener unicamente los siguientes simbolos: /*+#$%&?¡¿" +
                    "\n5. SU CONTRASENA NO SERA VISIBLE EN CONSOLA; CUANDO LA TERMINE DE DIGITAR PULSE INTRO" 
                    );
                    System.out.println(repetNtimes("-", 50));

                    contrasena = Input("Contrasena: "); //enmascaramiento de la contraseña, para no ser visible en consola
                    if (!validarRestriccionesPswd(contrasena)) {
                        System.out.println("\nContrasena NO cumple los requisitos\n");
                        if (confirmation("Intentar otra contrasena?: 1. SI // 2. NO: ", 1,2)==2) 
                        {contrasena = null; continuar = false;}
                    } else {continuar = false;}
                    break;

                case VALIDATE://caso cuando nesecito validar si la contraseña 
                    System.out.println("\nSU CONTRASENA NO SERA VISIBLE EN CONSOLA; CUANDO TERMINE DE DIGITAR PULSE INTRO");
                    String digitedPassword = Input("Contrasena: "); //enmascaramiento de la contraseña, para no ser visible en consola
                    if (!validarContrasena(alias, digitedPassword)) {
                        System.out.println("\nContrasena NO valida\n");
                        if (confirmation("Intentar de Nuevo?: 1. SI // 2. NO: ",1,2)==2) 
                        {contrasena = null; continuar = false; primaryKey = null; setDatos();}
                    } else {continuar = false;}
                    break;
            }
        } while (continuar);
        return contrasena;
    }

    /**
     * enmascara el texto para no ser mostrado en consola mientras se digita
     * es usado para el ingreso de datos para contraseñas
     * @param text
     * @return retorna el valor escrito por el usuario
     */
    private String maskString(String text) {//100%
        Console cons; 
        char[] passwd; 
        String result = "";

        if ((cons = System.console()) != null && (passwd = cons.readPassword("%s", text)) != null) {
            for (char c : passwd) {
                result += c;
                System.out.print('*');
            }
        }
        return result;
    }

    /**
     * retorna boleano dependiendo si el usuario ingresado coincide con la contraseña de la BD
     * se usa para el login y para el requestContraseña
     * @param usuario
     * @param contrasena
     * @return
     */
    private boolean validarContrasena(String usuario, String contrasena) {//100%
        consulta = myJDBC.READ_as_ARRAY("SELECT contrasena FROM clientes WHERE alias = BINARY '"+ usuario +"';");
        boolean salida = (contrasena.equals(consulta.get(0)[0]))?true:false; 
        consulta.clear();
        return salida;
    }

    /**
     * contiene las validaciones para validar si una contreseña cumple las restricciones
     *  al crear un nuevo usuario o al modificar un usuario.
     * se usa desde el login o desde el administrador
     * @param contrasena
     * @return
     */
    private boolean validarRestriccionesPswd(String contrasena) {//100%
        boolean salida = true;
        salida &= contrasena.length()>=6;
        salida &= contrasena.matches("[a-zA-Z0-9/*+#$%&?¡¿)]+");
        return salida;
    }

    /**
     * imprime en consola los datos del usuario
     */
    @Override
     public String toString() {//100%
        textToString =
            "\nAlias:      " + alias+
            "\nNombre:     " + nombre+
            "\nApellido:   " + apellido+
            "\nTelefono:   " + telefono+
            "\nContrasena: " + repetNtimes("*",contrasena.length());
            return super.toString();
    }

    /**
     * menu que permite editar los datos de un cliente segun se elija el dato que se quiere cambiar.
     * es usado por el menu del administrador
     * @return 
     */
    @Override
     public boolean modifyEntity() {//100%
        limites = new int[]{0,5};
        columnasQuery = "alias,nombre,apellido,telefono"; //nombres de las columnas en que van a ser actualizadas en la tabla de la BD
        return super.modifyEntity();
    }

    /**
     * imprime en la consola los datos dependiendo de las opcion elegida en el parametro de entrada.
     * busca dentro de la lista entidad la llave primaria y genera un objeto temporal el cual permite
     * leer todos los datos de dicho registro y mostrarlo en pantalla.
     * @param listaEntidad  //debe contener los datos de los registros incluido la llave primaria
     * @param opcion    //seleccione el registro(opc=registro), todos los registros(maximo numero +1) o salir(opc=0)
     */
    @Override
     public void showData(ArrayList<String[]> listaEntidad, int opcion) {//100%
        User temporal;  //se crea objeto temporal de la misma clase para no alterar los valores del objeto que invoco este metodo
        if(opcion == 0){
            return;
        } else if(opcion == listaEntidad.size()+1){//muestra todos los registros de una entidad
            for (String[] entidad : listaEntidad) {
                if(!entidad[0].equals("admin")){
                    temporal = new User();
                    temporal.setPrimaryKey(entidad[0]);
                    temporal.setDatos();
                    System.out.println(temporal.toString());
                }
            }
        } else {primaryKey = (listaEntidad.get(opcion-1)[0]); setDatos(); System.out.println(toString());} //muestra solo un registro de una entidad
    }


    /**
     * elimina los datos dependiendo de la opcion elegida por el usuario en el parametro opcion.
     * hace uso de la funcion delete user y es invocado por el administrador desde el menuIntenciones.
     * los datos que se pueden eliminar son los que no tenga como llave primaria ni foranea en otra tabla.
     * esa es la razon por la que no siempre aparecen todos lo usuarios.
     * @param listaEntidad
     * @param opcion
     */
    @Override
     public void opcionDelete(ArrayList<String[]> listaEntidad, int opcion) {//100%
        User temporal;  //se crea objeto temporal de la misma clase para no alterar los valores del objeto que invoco este metodo
        if(opcion == 0){
            return;
        } else if(opcion == listaEntidad.size()+1){//elimina todos los registros posibles. solo deja los que tenga cruce con otras tablas.
            for (String[] entidad : listaEntidad) {
                // if(!entidad[0].equals("admin")){
                    temporal = new User();
                    temporal.setPrimaryKey(entidad[0]);
                    temporal.setDatos();
                    temporal.deleteRegistro();
                // }
            }
        } else {primaryKey = (listaEntidad.get(opcion-1)[0]); setDatos(); deleteRegistro();} //muestra solo un registro de una entidad
        System.out.println("\nAccion realizada.");
    }

    /**
     * elimina un registro individual.
     */
    @Override
     public void deleteRegistro() {
        textDelete = "DELETE FROM "+tabla+" WHERE alias='"+primaryKey+"';";
        super.deleteRegistro();
    }

    //GETTER'S Y SETTERS

    /**
     * establece los datos de la instancia creada. si el parametro de entrada es nulo, los atributos
     * se estableceran como nulos.
     * @param
     * @return
     */
    @Override
    public boolean setDatos() {//100%
        if(primaryKey == null){entidad = ENTIDAD.NULO;}
        else {entidad = ENTIDAD.CLIENTE;this.perfil = getPerfil();}
        return super.setDatos();
    }

    /**
     * establece un perfil para la instancia de la clase.
     * es usado por el administrador y el setDatos
     * @return
     */
    public USUARIO getPerfil() {//100%
        if (alias == null){perfil = USUARIO.NO_IDENTIFICADO;}
        else if (alias.equals("admin")) {perfil = USUARIO.ADMINISTRADOR;} 
        else{perfil = USUARIO.CLIENTE;}
        return perfil;
    }
}