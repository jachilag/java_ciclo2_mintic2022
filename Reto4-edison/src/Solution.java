import java.util.ArrayList;
public abstract class Solution{
    //ESTA CLASE NO TIENE MAIN
    
    public static Object[] reportes(ArrayList<Cliente> tienda){
        //EN ESTE ESPACIO PONER SU LÓGICA
        Object[] salida = new Object[5];
        Cliente masBajo = clienteMenor(tienda);
        Cliente masAlto = clienteMayor(tienda);

        salida[0] = promedio(tienda);
        salida[1] = masBajo.getNombreCompleto();
        salida[2] = masBajo.getTotalAPagar();
        salida[3] = masAlto.getNombreCompleto();
        salida[4] = masAlto.getTotalAPagar();
        
        return salida;
    }

    public static double promedio(ArrayList<Cliente> tienda) {
        double salida = 0;
        for (Cliente cliente : tienda) {
            salida += cliente.getTotalAPagar();
        }
        return salida/((double) tienda.size());
    }

    public static Cliente clienteMenor(ArrayList<Cliente> tienda) {
        int menor = tienda.get(0).getTotalAPagar();
        Cliente salida = tienda.get(0);
        for (Cliente cliente : tienda) {
            if (menor>cliente.getTotalAPagar()) {
                menor = cliente.getTotalAPagar();
                salida = cliente;
            }
        }
        return salida;
    }
    
    public static Cliente clienteMayor(ArrayList<Cliente> tienda) {
        int mayor = tienda.get(0).getTotalAPagar();
        Cliente salida = tienda.get(0);
        for (Cliente cliente : tienda) {
            if (mayor<cliente.getTotalAPagar()) {
                mayor = cliente.getTotalAPagar();
                salida = cliente;
            }
        }
        return salida;
    }
    
}