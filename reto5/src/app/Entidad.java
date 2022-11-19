package app;

import java.util.ArrayList;
import app.MenuAccion;
import fabricantes.Bicicleta;
import fabricantes.Moto;
import intencionCompra.IntencionCompra;

import static app.funProp.*;
import myJDBC.MyJDBC;
import proveedor.Proveedor;
import user.User;
import user.Administrador.ENTIDAD;
import user.User.NEED_PSWD;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * clase abstracta de las clases que representan las entidades de la BD.
 * esta abstraccion se realiza por dos razones. la primera es para garantizar que todas
 * las clases qeu hereden de esta clase, tengan obligatoriamente los metodos. y la 
 * segunda es para poder realizar la genericidad en el menu de las acciones sobre cada
 * entidad.
 */
public class Entidad {
    //ATRIBUTOS
    protected MyJDBC myJDBC;
    protected String fabricante;
    protected String fabricanteBici;
    protected long precioUnitario;
    protected long anio;
    protected enum NEED{EXIST,NOT_EXIST};
    protected enum DATO{NOMBRE,APELLIDO,TELEFONO,CONTRASENA,  PRECIO_UNITARIO,ANIO,  ALIAS,FABRICANTE,TIPO,FECHA,  ACTUALIZAR,SALIR, DIRECCION_PROVEEDOR, AUTONOMIA, PROVEEDOR_ID};
    protected enum FABRICANTE{MOTO, BICICLETA,NO_IDENTIFICADO};
    protected FABRICANTE tipo;
    protected DATO dato;
    protected ENTIDAD entidad;
    protected String tabla;
    protected ArrayList<String[]> consulta;
    protected String alias;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String contrasena;
    protected MenuAccion menuTemporal;
    protected String queryCreate;
    protected String primaryKey;
    protected String valuePK;
    protected String queryRequest;
    protected String textRequest;
    protected String forString;
    protected String textToString;
    protected String columnasQuery;
    protected String id;
    protected String fecha;
    protected String textModify;
    protected String textUpdate;
    protected int[] limites;
    protected String textDelete;

    protected String idProv;
    protected String nombreProv;
    protected String telefonoProv;
    protected String direccionProv;

    protected String fabricanteMoto;
    protected int autonomia;
    protected int proveedorMoto;

    protected LocalDateTime myDateObj;
    protected DateTimeFormatter myFormatObj;
    protected int opc;
    protected User userTemporal;
    protected IntencionCompra intencionTemporal;
    protected Bicicleta bicicletaTemporal;
    protected Moto motoTemporal;
    protected Proveedor preveedorTemporal;
    
    //CONSTRUCTOR
    public Entidad() {
        myJDBC = new MyJDBC("grupo53", "grupo53", "Grupo53Ciclo2*");
        myJDBC.ConnectionMyDB();
        entidad = ENTIDAD.NULO;
    }


    //METODOS
    protected boolean createEntity(){
        boolean salida = false;
        if(myJDBC.CREATE(queryCreate)){
            System.out.println("\n\nREGISTRO CREADO"); 
            salida = true;
        } else {System.out.println("\n\nregistro NO creado");}
        return salida;
    }

    protected String requestEntity(NEED need){
        boolean valPK = true;
        boolean continuar = true;
        do {
            primaryKey =  Input(textRequest);
            String pk2 = forString + primaryKey + forString;
            ArrayList<String[]> consulta = myJDBC.READ_as_ARRAY("SELECT "+valuePK+" FROM "+tabla+" WHERE "+valuePK+" = BINARY "+ pk2 +";");
            valPK = (consulta.size()==0)?false:true;
            switch (need) {
                case EXIST://caso cuando necesito que nickname exista. por ejemplo para VALIDAR 
                    if (!valPK) {
                        System.out.println("\nRegistro NO existe\n");
                        if(confirmation("Intentar con otro registro?: 1. SI // 2. NO: ", 1,2)==2)
                        {primaryKey = null; continuar = false;}
                    }  else {continuar = false;}
                    break;
                case NOT_EXIST://caso cuando nesecito que NO exista el nickname. por ejempo para CREAR
                    if (valPK) {
                        System.out.println("\nRegistro YA existe\n");
                        if(confirmation("Intentar con otro registro?: 1. SI // 2. NO: ", 1,2)==2)
                        {primaryKey = null; continuar = false;}
                    } else {continuar = false;}
                    break;
            }
        } while (continuar);
        return primaryKey;
    }

    protected boolean modifyEntity(){
        //MENU PARA SELECCIONAR FABRICANTE A MODIFICAR
        boolean salida = true;
        menuTemporal = new MenuAccion<>();
        int opc = menuTemporal.menuConsultarGeneral(entidad, tabla, columnasQuery);
        if(opc != 0){
            userTemporal = new User();
            intencionTemporal = new IntencionCompra();
            bicicletaTemporal = new Bicicleta();
            motoTemporal = new Moto();
            preveedorTemporal = new Proveedor();
            primaryKey = menuTemporal.valueFromArray(opc, 0);
            setDatos();
    
            //MENU PARA ACTUALIZAR EL DATO DESEADO
            boolean validacion = false;
            
            do {
                System.out.println("\nElija el DATO que desea modificar : ");
                setTextModify();
                int opcion = confirmation(textModify, limites[0], limites[1]); 

                switch (entidad) {
                    case CLIENTE:
                        dato = (opcion==1)?DATO.NOMBRE:((opcion==2)?DATO.APELLIDO:((opcion==3)?DATO.TELEFONO:((opcion==4)?DATO.CONTRASENA:((opcion==5)?DATO.ACTUALIZAR:DATO.SALIR))));
                        textUpdate = "UPDATE clientes SET nombre= '" + nombre + "', apellido='" + apellido + "', telefono='" + telefono + "'," + 
                                    "contrasena='" + contrasena + "' WHERE alias = '" +  alias + "';";
                        break;
                    case BICICLETA:
                        dato = (opcion==1)?DATO.PRECIO_UNITARIO:((opcion==2)?DATO.ANIO:((opcion==3)?DATO.ACTUALIZAR:DATO.SALIR));
                        textUpdate = "UPDATE bicicletas SET precio_unitario=" + precioUnitario + ", anio=" + anio + " WHERE fabricante = '" +  fabricanteBici + "';";
                        fabricante = "" + fabricanteBici;
                        break;
                    case INTENCIONES:
                        dato = (opcion==1)?DATO.ALIAS:((opcion==2)?DATO.FABRICANTE:((opcion==3)?DATO.FECHA:((opcion==4)?DATO.ACTUALIZAR:DATO.SALIR)));
                        textUpdate = "UPDATE "+tabla+" SET cliente= '" + alias + "', fabricante='" + fabricante + "', tipo='" + tipo + "'," + 
                        "fecha='" + fecha + "' WHERE id = " +  id + ";";
                        break;
                    case PROVEEDOR:
                        dato = (opcion==1)?DATO.NOMBRE:((opcion==2)?DATO.DIRECCION_PROVEEDOR:((opcion==3)?DATO.TELEFONO:((opcion==4)?DATO.ACTUALIZAR:DATO.SALIR)));
                        textUpdate = "UPDATE proveedor SET nombre='" + nombreProv + "', direccion='" + direccionProv + "', telefono='" + telefonoProv + "' WHERE id = " +  idProv + ";";
                        break;
                    case MOTO:
                        dato = (opcion==1)?DATO.PRECIO_UNITARIO:((opcion==2)?DATO.AUTONOMIA:((opcion==3)?DATO.PROVEEDOR_ID:((opcion==4)?DATO.ACTUALIZAR:DATO.SALIR)));
                        textUpdate = "UPDATE motocicletas_electricas SET precio_unitario=" + precioUnitario + ", autonomia=" + autonomia + ", proveedor=" + idProv + " WHERE fabricante = '" +  fabricanteMoto + "';";
                        fabricante = "" + fabricanteMoto;
                        break;
                
                    default:
                        break;
                }

                switch (dato) {
                    //datos de bicicleta
                    case PRECIO_UNITARIO:
                        precioUnitario = confirmation("Ingrese nuevo " + dato + " :",0,Long.MAX_VALUE);
                        break;
                    case ANIO:
                        anio = confirmation("Ingrese nuevo " + dato + " :",0,Long.MAX_VALUE);
                        break;
                    case ACTUALIZAR:
                        myJDBC.UPDATE(textUpdate);
                        System.out.println("\n\nDATOS ACTUALIZADOS");
                        setDatos();    //establece los atributos del fabricante
                        salida = false;
                        validacion = true;
                        break;
                    case SALIR:
                        salida = false;
                        validacion = true;
                        break;
                    case ALIAS:
                        System.out.println("Elija un " + dato + ":");
                        opc = menuTemporal.menuConsultarGeneral(ENTIDAD.CLIENTE, "clientes","alias");
                        if(opc != 0) {alias = menuTemporal.valueFromArray(opc,0);} //set los datos de la intencion
                        break;
                    //datos de intenciones de compra
                    case TIPO:
                        intencionTemporal.menuTipoTransporte();
                        break;
                    case FABRICANTE:
                        System.out.println("Elija un " + dato + ":");
                        intencionTemporal.menuTipo();
                        fabricante = intencionTemporal.fabricante;
                        tipo = intencionTemporal.tipo;
                        break;
                    case FECHA:
                        System.out.println("\nCOMO DESEA ESTABLECER LA FECHA:");
                        opc = confirmation("0. Salir // 1. Digitar una fecha // 2. Fecha y hora actual:  ", consecutiveList(0, 2, 1));
                        if(opc != 0){fecha = (opc==1)?Input("formato yyyy-mm-dd hh:mm:ss = "):intencionTemporal.TimeNow();} //HUECO DE SEGURIDAD. SI USUARIO INGRESA FECHA INCORRECTA
                        break;

                    //datos de clientes
                    case NOMBRE:
                        nombre = Input("Ingrese nuevo " + dato + " :");
                        nombreProv = nombre+"";
                        break;
                    case APELLIDO:
                        apellido = Input("Ingrese nuevo " + dato + " :");
                        break;
                    case TELEFONO:
                        telefono = Input("Ingrese nuevo " + dato + " :");
                        telefonoProv = telefono+"";
                        break;
                    case CONTRASENA:
                        contrasena = userTemporal.requestContrasena(NEED_PSWD.CREATE);
                        if(contrasena == null){validacion = true;}
                        break;
                    case DIRECCION_PROVEEDOR:
                        direccionProv = Input("Ingrese nuevo " + dato + " :");
                        break;
                    case AUTONOMIA:
                        autonomia = confirmation("Ingrese nuevo " + dato + " :",0, 1000);
                        break;
                    case PROVEEDOR_ID:
                        System.out.println("Elija un " + dato + ":");
                        opc = menuTemporal.menuConsultarGeneral(ENTIDAD.PROVEEDOR, "proveedor","id, nombre");
                        if(opc != 0) {idProv = menuTemporal.valueFromArray(opc,0);} //set los datos de la intencion
                        break;
                    
                }
            } while (!validacion);
        } //else {entidad = ENTIDAD.NULO;}
        return salida;
    }

    private void setTextModify(){
        switch (entidad) {
            case CLIENTE:
                this.textModify = "1. NOMBRE:     " + nombre +
                "\n2. APELLIDO:   " + apellido +
                "\n3. TELEFONO:   " + telefono + 
                "\n4. CONTRASEÑA: " + repetNtimes("*", this.contrasena.length()) + 
                "\n5. ACTUALIZAR Y SALIR"+
                "\n0. CANCELAR\n";
                break;
            case BICICLETA:
                this.textModify = "1. PRECIO_UNITARIO: " + precioUnitario +
                "\n2. ANIO:            " + anio + 
                "\n3. ACTUALIZAR Y SALIR"+
                "\n0. CANCELAR\n";
                break;
            case INTENCIONES:
                this.textModify = "1. NICKNAME:   " + alias +
                "\n2. FABRICANTE: " + fabricante +
                "\n3. FECHA:      " + fecha + 
                "\n4. ACTUALIZAR Y SALIR"+
                "\n0. CANCELAR\n";
                break;
            case PROVEEDOR:
                this.textModify = 
                  "1. NOMBRE:    " + nombreProv +
                "\n2. DIRECCION: " + direccionProv + 
                "\n3. TELEFONO:  " + telefonoProv + 
                "\n4. ACTUALIZAR Y SALIR"+
                "\n0. CANCELAR\n";
                break;
            case MOTO:
                this.textModify = 
                  "1. PRECIO_UNITARIO: " + precioUnitario +
                "\n2. AUTONOMIA:       " + autonomia + 
                "\n3. PROVEEDOR:       " + idProv + 
                "\n4. ACTUALIZAR Y SALIR"+
                "\n0. CANCELAR\n";
                break;
        
            default:
                break;
        }
    }

    protected void showData(ArrayList<String[]> listaEntidad, int opcion) {}

    protected void opcionDelete(ArrayList<String[]> listaEntidad, int opcion){}

    protected void deleteRegistro() {
        try {
            myJDBC.DELETE(textDelete);
        } catch (Exception e) {System.out.println("\nNo se puede eliminar Registro");}
    };

    public String toString() {
        return "\n" + repetNtimes("-", 50) + textToString +"\n" + repetNtimes("-", 50);
    }

    protected boolean setDatos(){
        boolean salida = true;
        switch (entidad) {
            case NULO:
            this.alias = null;
            this.nombre = null;
            this.apellido = null;
            this.telefono = null;
            this.contrasena = null;
            this.fabricanteBici = null;
            this.fabricante = null;
            this.precioUnitario = 0;
            this.anio = 0;
            this.fecha = null;
            this.idProv = null;
            this.nombreProv = null;
            this.telefonoProv = null;
            this.direccionProv = null;
            this.fabricanteMoto = null;
            this.autonomia = 0;
            this.proveedorMoto = 0;
            this.tipo = FABRICANTE.NO_IDENTIFICADO;
            
            break;

            case BICICLETA:
            try {
                consulta = myJDBC.READ_as_ARRAY("SELECT*FROM bicicletas WHERE fabricante='"+primaryKey+"';");
                this.fabricanteBici = primaryKey;
                this.fabricante = primaryKey;
                this.precioUnitario = Long.valueOf(consulta.get(0)[1]);
                this.anio = Long.valueOf(consulta.get(0)[2]);
            } catch (Exception e) {e.printStackTrace();System.out.println("NO EXISTE FABRICANTE");salida = false;}
            break;

            case CLIENTE:
            try {
                consulta = myJDBC.READ_as_ARRAY("SELECT*FROM "+tabla+" WHERE "+valuePK+"='"+primaryKey+"';");
                this.alias = primaryKey;
                this.nombre = consulta.get(0)[1];
                this.apellido = consulta.get(0)[2];
                this.telefono = consulta.get(0)[3];
                this.contrasena = consulta.get(0)[4];
            } catch (Exception e) {System.out.println("NO EXISTE USUARIO");salida = false;}
            break;

            case INTENCIONES:
            try {
                
                consulta = myJDBC.READ_as_ARRAY("SELECT*FROM intencion_compra WHERE id="+primaryKey+";");
                this.id = primaryKey;
                this.alias = consulta.get(0)[0];
                this.fabricante = consulta.get(0)[1];
                this.tipo = (consulta.get(0)[2].equals("BICICLETA"))?FABRICANTE.BICICLETA:FABRICANTE.MOTO;
                this.fecha = consulta.get(0)[3];
            } catch (Exception e) {System.out.println("NO EXISTE CONSULTA");salida = false;}
            break;

            case MOTO:
            try {
                consulta = myJDBC.READ_as_ARRAY("SELECT*FROM motocicletas_electricas WHERE fabricante='"+primaryKey+"';");
                this.fabricanteMoto = primaryKey;
                this.fabricante = primaryKey;
                this.precioUnitario = Long.valueOf(consulta.get(0)[1]);
                this.autonomia = Integer.valueOf(consulta.get(0)[2]);
                this.proveedorMoto = Integer.valueOf(consulta.get(0)[3]);
            } catch (Exception e) {e.printStackTrace();System.out.println("NO EXISTE PROVEEDOR");salida = false;}
            break;

            case PROVEEDOR:
            try {
                consulta = myJDBC.READ_as_ARRAY("SELECT*FROM proveedor WHERE id="+primaryKey+";");
                this.idProv = primaryKey;
                this.nombreProv = consulta.get(0)[1];
                this.direccionProv = consulta.get(0)[2];
                this.telefonoProv = consulta.get(0)[3];
            } catch (Exception e) {e.printStackTrace();System.out.println("NO EXISTE PROVEEDOR");salida = false;}
            break;
        
            default:
                break;
        }


        return salida;
    }

    public String getTabla(){return tabla;}

    public String getAlias(){return alias;}

    public int[] getLimites(){return limites;}

    public String getPrimaryKey(){return primaryKey;}

    public void setPrimaryKey(String primaryKey){this.primaryKey = primaryKey;}

}