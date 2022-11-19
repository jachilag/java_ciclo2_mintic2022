package intencionCompra;

import static app.funProp.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import app.Entidad;
import app.MenuAccion;
import user.Administrador.ENTIDAD;

public class IntencionCompra extends Entidad{
    //atributos

    //constructor
    public IntencionCompra(String alias) {
        super();
        tabla = "intencion_compra";
        valuePK = "id";
        primaryKey = null;
        entidad = ENTIDAD.INTENCIONES;
        menuTemporal = new MenuAccion<>();
        this.alias = alias;
    }

    public IntencionCompra() {
        super();
        tabla = "intencion_compra";
        valuePK = "id";
        primaryKey = null;
        entidad = ENTIDAD.INTENCIONES;
        menuTemporal = new MenuAccion<>();
        setDatos();
    }

    //metodos
    @Override    
	public boolean createEntity() {//100%
        opc = menuTemporal.menuConsultarGeneral(ENTIDAD.CLIENTE, "clientes","alias");
        if(opc !=0){
            this.alias = menuTemporal.valueFromArray(opc,0);
            menuTipoTransporte();
            System.out.println("\nCONSULTA CREADA\n");
        }
        return true;
	}

    @Override    
    public boolean modifyEntity() {//100%
        limites = new int[]{0,4};
        columnasQuery = "id,cliente,fabricante,fecha";
        return super.modifyEntity();
    }

    @Override    
    public String toString() {//100%
        textToString = 
        "\nAlias:      " + alias + 
        "\nFabricante: " + fabricante + 
        "\nFecha:      " + fecha;
        return super.toString();
    }

    @Override    
    public void showData(ArrayList<String[]> listaEntidad, int opcion) {//100%
        IntencionCompra intencionTemporal;  //se crea objeto intencionTemporal de la misma clase para no alterar los valores del objeto que invoco este metodo
        int colPrimaryKey = 0;
        if(opcion == 0){return;} 
        else if(opcion == listaEntidad.size()+1){//muestra todos los registros de una entidad
            for (String[] entidad : listaEntidad) {
                    intencionTemporal = new IntencionCompra();
                    intencionTemporal.setPrimaryKey(entidad[colPrimaryKey]);
                    intencionTemporal.setDatos(); //la posicion del id esta en la 5ta columna
                    System.out.println(intencionTemporal.toString());
            }
        } 
        else {primaryKey = listaEntidad.get(opcion-1)[colPrimaryKey];setDatos();System.out.println(toString());}}//muestra solo un registro de una entidad

    @Override        
    public void opcionDelete(ArrayList<String[]> listaEntidad, int opcion) {//100%
        IntencionCompra intencionTemporal;  //se crea objeto intencionTemporal de la misma clase para no alterar los valores del objeto que invoco este metodo
        int colPrimaryKey = 0;
        if(opcion == 0){return;} 
        else if(opcion == listaEntidad.size()+1){//elimina todos los registros posibles. solo deja los que tenga cruce con otras tablas.
            for (String[] entidad : listaEntidad) {
                intencionTemporal = new IntencionCompra();
                intencionTemporal.setPrimaryKey(entidad[colPrimaryKey]);
                intencionTemporal.setDatos();
                intencionTemporal.deleteRegistro();
            }
        } else {primaryKey = listaEntidad.get(opcion-1)[0]; setDatos();deleteRegistro();} //muestra solo un registro de una entidad
        System.out.println("\nAccion realizada.");
    }

       /**
     * establece los datos de la instancia creada. si el parametro de entrada es nulo, los atributos
     * se estableceran como nulos.
     * @param primaryKey
     * @return
     */
    @Override     
    public boolean setDatos() {//pendiente
        if(primaryKey == null){entidad = ENTIDAD.NULO;}
        else {
            entidad = ENTIDAD.INTENCIONES;
            fecha = TimeNow();
        }
        return super.setDatos();
    }

    public boolean setDatosBD() {//pendiente
            entidad = ENTIDAD.INTENCIONES;
            // String fabricante = (entidad==ENTIDAD.MOTO)?fabricanteMoto:fabricanteBici;
            fecha = TimeNow();
            myJDBC.CREATE("INSERT INTO intencion_compra(cliente,fabricante,tipo,fecha) VALUES ('"+alias+"','"+fabricante+"','"+tipo+"','"+fecha+"')");
            primaryKey = myJDBC.READ("SELECT last_insert_id();");
        return super.setDatos();
    }

    @Override    
    public void deleteRegistro() {//100%
        textDelete = "DELETE FROM "+tabla+" WHERE id="+id+";";
        super.deleteRegistro();
    }

    public String TimeNow(){ //obtencion de fecha y hora de ahora
        myDateObj = LocalDateTime.now();
        myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return myDateObj.format(myFormatObj);
    }

    public void menuTipoTransporte() {
        boolean validacion = false;
        do {
            System.out.println("\nElija un medio de transporte");
            tipo = (confirmation("1. Bicicleta // 2. Motocicleta Electrica:  ", 1,2)==1)?FABRICANTE.BICICLETA:FABRICANTE.MOTO;
            menuFabricante(tipo);
            setDatosBD();
            System.out.println(toString());
            validacion = (confirmation("Desea Realizar otra consulta? 1. Si // 2. No  ", 1,2)==2) ?true:false;
        } while (!validacion);
    }

    public void menuTipo() {
        System.out.println("\nElija un tipo de fabricante: ");
        tipo = (confirmation("1. Bicicleta // 2. Motocicleta Electrica:  ", 1,2)==1)?FABRICANTE.BICICLETA:FABRICANTE.MOTO;
        menuFabricante(tipo);
    }

    public void menuFabricante(FABRICANTE tipo) {
        String textoFabricante = (tipo==FABRICANTE.BICICLETA)?"bicicletas;":"motocicletas_electricas;";
        entidad = (tipo==FABRICANTE.BICICLETA)?ENTIDAD.BICICLETA:ENTIDAD.MOTO;
        opc = menuTemporal.menuConsultarGeneral(entidad, textoFabricante, "fabricante");
        if(opc != 0){this.fabricante = menuTemporal.valueFromArray(opc,0);}
    }

}