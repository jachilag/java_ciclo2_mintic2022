package fabricantes;

import java.util.ArrayList;
import app.Entidad;
import static app.funProp.*;
import user.Administrador.ENTIDAD;

public class Bicicleta extends Entidad{
    //atributos


    //constructor
    public Bicicleta() {
        super();
        tabla = "bicicletas";
        primaryKey = null;
        setDatos();
        valuePK = "fabricante";
        entidad = ENTIDAD.BICICLETA;
    }

    //metodos
    @Override
    protected boolean createEntity() {//100%
        //VALIDA NO EXISTENCIA DE BICICLETA EN LA BD
        String fabricante = requestEntity(NEED.NOT_EXIST);
        primaryKey = fabricante;
        if(fabricante != null){
            long precioUnitario = confirmation("Precio: ", 0l, Long.MAX_VALUE);
            int anio = confirmation("Anio: ",1900,3000);
            queryCreate = "INSERT INTO bicicletas(fabricante,precio_unitario,anio) VALUES ('" + fabricante +"'," + precioUnitario +"," + anio +");";
            primaryKey = (super.createEntity())?primaryKey:null;
            setDatos(); //establece los atributos de la llave primaria
        }
        return true;
    }

    /**
     * submenu que solicita ingreso de fabricante y valida si este existe o no en la BD.
     * aplica para los casos en que necesite la existencia del fabricante, por ejemplo para realizar querys, y 
     * tambien para los casos en que No necesecito que exista, por ejemplo para creacion de fabricante nuevo.
     * @return en caso que no cumpla con lo necesitado en el parametro de entrada, retornara nulo
     */
    protected String requestEntity(NEED need){//100%
        forString = "'";
        textRequest = "Ingrese nombre de un Fabricante: ";
        return super.requestEntity(need);
    }

    /**
     * menu que permite editar los datos de un FABRICANTE segun se elija el dato que se quiere cambiar.
     * es usado por el menu del administrador
     */
    @Override
     public boolean modifyEntity() {//100%
        limites = new int[]{0,3};
        columnasQuery = "fabricante,precio_unitario,anio";
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
        Bicicleta temporal;  //se crea objeto temporal de la misma clase para no alterar los valores del objeto que invoco este metodo
        if(opcion == 0){
            return;
        } else if(opcion == listaEntidad.size()+1){//muestra todos los registros de una entidad
            for (String[] entidad : listaEntidad) {
                temporal = new Bicicleta();
                temporal.setPrimaryKey(entidad[0]);
                temporal.setDatos();
                System.out.println(temporal.toString());
            }
        } else {primaryKey = listaEntidad.get(opcion-1)[0]; setDatos() ;System.out.println(toString());} //muestra solo un registro de una entidad
    }

    /**
     * imprime en consola los datos del fabricante
     */
    @Override
    public String toString() {//100%
        textToString = 
            "\nFABRICANTE:      " + fabricanteBici +
            "\nPRECIO_UNITARIO: " + precioUnitario + " pesos m/cte" +
            "\nANIO:            " + anio;
            return super.toString();
    }

    /**
     * elimina los datos dependiendo de la opcion elegida por el fabricante en el parametro opcion.
     * hace uso de la funcion delete fabricante y es invocado por el administrador desde el menuIntenciones.
     * los datos que se pueden eliminar son los que no tenga como llave primaria ni foranea en otra tabla.
     * esa es la razon por la que no siempre aparecen todos lo fabricantes.
     * @param listaEntidad
     * @param opcion
     */
    @Override
    public void opcionDelete(ArrayList<String[]> listaEntidad, int opcion) {
        Bicicleta temporal;  //se crea objeto temporal de la misma clase para no alterar los valores del objeto que invoco este metodo
        if(opcion == 0){
            return;
        } else if(opcion == listaEntidad.size()+1){//elimina todos los registros posibles. solo deja los que tenga cruce con otras tablas.
            for (String[] entidad : listaEntidad) {
                    temporal = new Bicicleta();
                    temporal.setPrimaryKey(entidad[0]);
                    temporal.setDatos();
                    temporal.deleteRegistro();
            }
        } else {primaryKey = listaEntidad.get(opcion-1)[0]; setDatos();deleteRegistro();} //muestra solo un registro de una entidad
        System.out.println("\nAccion realizada.");
    }

    /**
     * elimina un registro individual.
     */
    @Override
    public void deleteRegistro() {
        textDelete = "DELETE FROM "+tabla+" WHERE fabricante='"+fabricanteBici+"';";
        super.deleteRegistro();
    }

    /**
     * establece los datos de la instancia creada. si el parametro de entrada es nulo, los atributos
     * se estableceran como nulos.
     * @param primaryKey
     * @return
     */
    @Override
    public boolean setDatos() {//100%
        if(primaryKey == null){entidad = ENTIDAD.NULO;}
        else {entidad = ENTIDAD.BICICLETA;}
        return super.setDatos();
    }
    
}