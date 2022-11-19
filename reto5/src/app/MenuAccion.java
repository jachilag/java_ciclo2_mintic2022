package app;

import java.util.ArrayList;
import static app.funProp.*;
import myJDBC.MyJDBC;
import user.Administrador.ENTIDAD;
import user.Administrador.ACCION;

/**
 * clase generica para cada Entidad de la base de datos: clientes, proveedores, motos
 * bicicletas e intenciones de compra. como todas las clases tienen los mismos metodos, 
 * pero funcionan de manera distinta, se realiza una abstraccion en la superclase padre 
 * llamada Entidad. con este cambio la genericidad se hace posible por herencia.
 * El objetivo de realizar la genericidad es para reducir codigo en la clase administrador, 
 * en donde, una vez se elige la entidad y la accion a realizar, el submenu resultante
 * requiere la instanciacion de un objeto del tipo o entidad elegida. por lo tanto para no 
 * crear un submenu para cada entidad se decide usar la genericidad.
 * recurso de apoyo en la idea: 
 * https://javadesdecero.es/avanzado/genericos-ejemplos-java/
 * https://www.campusmvp.es/recursos/post/java-comodines-para-tipos-genericos-pecs-diferencias-entre-extends-t-y-super-t.aspx
 * 
 */
public class MenuAccion<T extends Entidad> {
    private MyJDBC myJDBC;
    private ArrayList<String[]> listaEntidad;
    private ArrayList<String[]> listaEntidadGeneral;
    public int opcion;
    private ACCION accion;
    private ENTIDAD entidad;
    private T entity; // objeto generico del tipo de las subclases de entidad: bicicleta,moto,user,proveedor,intenciones_compra.
    private String tabla;
    private String columnasSQL ;
    private String queryDelete;


    public MenuAccion(ENTIDAD entidad, String tabla, ACCION accion, T entity, String columnasSQL, String queryDelete) {
        myJDBC = new MyJDBC("grupo53", "grupo53", "Grupo53Ciclo2*");
        myJDBC.ConnectionMyDB();
        this.accion = accion;
        this.entity = entity;
        this.columnasSQL = columnasSQL;
        this.queryDelete = queryDelete;
        this.entidad = entidad;
        this.tabla = tabla;
    }

    public MenuAccion() {
        myJDBC = new MyJDBC("grupo53", "grupo53", "Grupo53Ciclo2*");
        myJDBC.ConnectionMyDB();

    }

    public  void Desplegar() {//falta 100%
        boolean validacion = false;
        do {
            switch (accion) {
                case CREAR:
                    validacion = entity.createEntity();
                    break;
                case MODIFICAR:
                    validacion = entity.modifyEntity();
                    break;
                case CONSULTAR:
                    opcion = menuConsultar(columnasSQL);
                    if(opcion == 0){return;}
                    entity.showData(listaEntidad, opcion);
                    break;
                case ELIMINAR:
                    listaEntidad = myJDBC.READ_as_ARRAY(queryDelete);
                    opcion = menuConsultar(columnasSQL);
                    if(opcion == 0){return;}
                    entity.opcionDelete(listaEntidad, opcion);
                    break;
                case SALIR:
                    validacion = true;
                    break;
                default:
                    break;
            }
        } while (!validacion);
    }

    private int menuConsultar(String columnasSQL) {
        //impresion en pantalla de los registros de cada entidad y las opciones al final
        String textoConsulta = "";
        System.out.println("\nElija un registo de la entidad de tipo " + entidad +" a "+accion+": ");
        listaEntidad = (entidad != ENTIDAD.CLIENTE)? myJDBC.READ_as_ARRAY("SELECT "+ columnasSQL + " FROM " + tabla):myJDBC.READ_as_ARRAY("SELECT "+ columnasSQL + " FROM " + tabla + " WHERE alias != 'admin';");
        
        for (int i = 0; i < listaEntidad.size(); i++) {
            textoConsulta += i+1 + ". " + printArray(listaEntidad.get(i));
        }
        textoConsulta += listaEntidad.size()+1 + ". TODOS\n0. SALIR\n";
        return confirmation(textoConsulta, consecutiveList(0,listaEntidad.size()+1,1));
    }

    public int menuConsultarGeneral(ENTIDAD entidad, String tabla, String columnasSQL) {
        //impresion en pantalla de los registros de cada entidad y las opciones al final
        String textoConsulta = "";
        System.out.println("\nElija un registo de la entidad de tipo " + entidad +": ");
        listaEntidadGeneral = (entidad != ENTIDAD.CLIENTE)? myJDBC.READ_as_ARRAY("SELECT "+ columnasSQL + " FROM " + tabla):myJDBC.READ_as_ARRAY("SELECT "+ columnasSQL + " FROM " + tabla + " WHERE alias != 'admin';");
        for (int i = 0; i < listaEntidadGeneral.size(); i++) {
            textoConsulta += i+1 + ". " + printArray(listaEntidadGeneral.get(i));
        }
        textoConsulta += "0. SALIR\n";
        return confirmation(textoConsulta, 0,listaEntidadGeneral.size());
    }

    public ArrayList<String[]> getListaEntidadGeneral() {
        return listaEntidadGeneral;
    }

    public String valueFromArray(int opc, int index) {
        return getListaEntidadGeneral().get(opc-1)[index];
    }
}