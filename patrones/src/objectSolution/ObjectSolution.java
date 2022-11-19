package objectSolution;


/*
 * en esta forma de solucion podemos recibir cualquier tipo de dato y convertirlo a un tipo mas 
 * general como el tipo Object; sin embargo se pierden las propiedades propias asignadas al tipo
 * de objeto. 
 */
public class ObjectSolution {
    Object obj;

    public ObjectSolution(Object obj) {
        this.obj = obj;
    }

    public void DibujarObject(){
        String s = "*  "+obj.toString()+"  *\n";
        String linea = "";
        String mensaje;
        for(int i = 0; i < s.length()-1;i++) {
            linea += "*";
        }
        mensaje = linea + "\n" + s + linea;
        System.out.println(mensaje);
    }

    public Object getObj() {
        return obj;
    }
}
