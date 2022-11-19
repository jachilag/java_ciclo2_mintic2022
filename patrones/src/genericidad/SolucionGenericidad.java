package genericidad;

/*
 * es la mejor solucion ya que no hay perdida de informacion a diferencia que con la entrada de un 
 * dato de tipo Object, el cual pierde algunas de las propiedades. y conservamos la capacidad
 * de reutilizacion de codigo ya que para cualquier tipo de dato obtenemos el mismo resultado
 */

public class SolucionGenericidad<T> {
    T obj;
    public SolucionGenericidad(T obj){
        this.obj = obj;
    }

    public void DibujarObjetoGenerico(){
        String s = "*  "+obj.toString()+"  *\n";
        String linea = "";
        String mensaje;
        for(int i = 0; i < s.length()-1;i++) {
            linea += "*";
        }
        mensaje = linea + "\n" + s + linea;
        System.out.println(mensaje);
    }

    public T getObj() {
        return obj;
    }
}
