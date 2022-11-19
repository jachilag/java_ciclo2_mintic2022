package user;

import static app.funProp.*;
import app.MenuAccion;
import fabricantes.Bicicleta;
import fabricantes.Moto;
import intencionCompra.IntencionCompra;
import proveedor.Proveedor;


public class Administrador {
    //ATRIBUTOS
    public enum ACCION{CREAR,MODIFICAR,CONSULTAR,ELIMINAR,SALIR};
    private ACCION accion;
    public enum ENTIDAD{PROVEEDOR,BICICLETA,MOTO,CLIENTE,INTENCIONES,SALIR,NULO};
    private ENTIDAD entidad;
    private Proveedor proveedor;
    private Bicicleta bicicleta;
    private Moto moto;
    private IntencionCompra intencion;
    private User cliente;
    private String tabla;
    public int opcion;
    public MenuAccion menu;
    private int[] limites;

    //CONSTRUCTOR
    public Administrador() {
    }
    
    //METODOS MENU
    public void menuAdministrator() {
        boolean validacion = false;
        System.out.println("\n********* Bienvenid@ ADMINISTRADOR *********\n");

        do {
            System.out.println("\n--- MENU PRINCIPAL DEL ADMINISTRADOR ---");
            System.out.println("\nElija una accion: ");
            opcion = confirmation("1. CREAR\n2. CONSULTAR\n3. MODIFICAR\n4. ELIMINAR\n0. LOGOUT\n", consecutiveList(0, 4, 1)); 
            if(opcion ==  0){validacion = true;}
            accion = (opcion==1)?ACCION.CREAR:((opcion==2)?ACCION.CONSULTAR:((opcion==3)?ACCION.MODIFICAR:((opcion==4)?ACCION.ELIMINAR:ACCION.SALIR)));
            if(accion==ACCION.SALIR){return;}
            menuEntidad(accion);
        } while (!validacion);
    }

    private void menuEntidad(ACCION accion) {
        boolean validacion = false;

        do {
            System.out.println("\n--- menu para " + accion + " una ENTIDAD ---");
            System.out.println("\nElija una ENTIDAD: ");
            opcion = confirmation("1. PROVEEDOR\n2. BICICLETAS\n3. MOTOS\n4. CLIENTES\n5. INTENCIONES COMPRA\n0. VOLVER AL MENU PRINCIPAL\n", 0,5); 
            if(opcion ==  0){validacion = true;}
            entidad = (opcion==1)?ENTIDAD.PROVEEDOR:((opcion==2)?ENTIDAD.BICICLETA:((opcion==3)?ENTIDAD.MOTO:((opcion==4)?ENTIDAD.CLIENTE:((opcion==5)?ENTIDAD.INTENCIONES:ENTIDAD.SALIR))));
            
            switch (entidad) {
                case PROVEEDOR:
                    proveedor = new Proveedor();
                    tabla = proveedor.getTabla();
                    limites = proveedor.getLimites();
                    menu = new MenuAccion<Proveedor>(entidad, tabla, accion, proveedor, "id,nombre", "SELECT id FROM proveedor WHERE id NOT IN(SELECT proveedor FROM motocicletas_electricas);");
                    menu.Desplegar();
                    break;
                case BICICLETA:
                    bicicleta = new Bicicleta();
                    tabla = bicicleta.getTabla();
                    limites = bicicleta.getLimites();
                    menu = new MenuAccion<Bicicleta>(entidad, tabla, accion, bicicleta, "fabricante", "SELECT fabricante FROM bicicletas WHERE fabricante NOT IN(SELECT fabricante FROM intencion_compra);");
                    menu.Desplegar();
                    break;
                case MOTO:
                    moto = new Moto();
                    tabla = moto.getTabla();
                    limites = moto.getLimites();
                    menu = new MenuAccion<Moto>(entidad, tabla, accion, moto, "fabricante", "SELECT fabricante FROM motocicletas_electricas WHERE fabricante NOT IN(SELECT fabricante FROM intencion_compra);");
                    menu.Desplegar();
                    break;
                case CLIENTE:
                    cliente = new User();
                    tabla = cliente.getTabla();
                    limites = cliente.getLimites();
                    menu = new MenuAccion<User>(entidad, tabla, accion, cliente, "alias", "SELECT alias FROM clientes WHERE alias != 'admin' AND alias NOT IN(SELECT cliente FROM intencion_compra);");
                    menu.Desplegar();
                    break;
                case INTENCIONES:
                    intencion = new IntencionCompra();
                    tabla = intencion.getTabla();
                    limites = intencion.getLimites();
                    menu = new MenuAccion<IntencionCompra>(entidad, tabla, accion, intencion, "id,cliente,fabricante,fecha", "SELECT id,cliente,fabricante,fecha FROM intencion_compra");
                    menu.Desplegar();
                    break;
                case SALIR:
                    validacion = true;
                    break;
                default:
                    break;
            }
        } while (!validacion);
    }
}