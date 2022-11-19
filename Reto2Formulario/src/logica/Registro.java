package logica;
import java.util.ArrayList;

public class Registro {
    //atributos
    private ArrayList<Estudiante> listaEstudiantes;
    private ArrayList<Estudiante> listaPregrado;
    private ArrayList<Estudiante> listaPosgrado;

    //constructor
    public Registro() {
        listaEstudiantes = new ArrayList<Estudiante>();
        listaPregrado = new ArrayList<Estudiante>();
        listaPosgrado = new ArrayList<Estudiante>();
    }

    //metodos
    public void agregarEstudiante(String datos) {
        String[] entrada = datos.split("&");
        if (entrada[1].equalsIgnoreCase("Pregrado")) {
            Pregrado estudiante = new Pregrado(entrada[2], entrada[3], entrada[4], entrada[5], entrada[6]);
            listaEstudiantes.add(estudiante);
            listaPregrado.add(estudiante);
        } else {
            Posgrado estudiante = new Posgrado(entrada[2], entrada[3], entrada[4], entrada[5], entrada[6]);
            listaEstudiantes.add(estudiante);
            listaPosgrado.add(estudiante);
        }
    }

    public String listarEstudiantes() {
        String salida;
        salida = "***Listado de estudiantes***\n";
        for (Estudiante estudiante: listaEstudiantes) {
            salida += estudiante.toString();
        }
        return salida;
    }

    public void limpiarDatos() {
        listaEstudiantes.clear();
        listaPregrado.clear();
        listaPosgrado.clear();
    }
    

    //getter y setter
    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public ArrayList<Estudiante> getListaPregrado() {
        return listaPregrado;
    }

    public ArrayList<Estudiante> getListaPosgrado() {
        return listaPosgrado;
    }
}
/* 1&Posgrado&Maria Sarmiento&25&Maestria en economia&Ninguna&Investigacion
 * 1&Pregrado&Luis Parra&21&Medicina&Raizal&15
 * 1&Pregrado&Martha Casas&19&Derecho&Ninguna&42
 * 1&Posgrado&Luz Salas&23&Maestria en ingenieria&Ninguna&Profundizacion
 * 2
 * 3
 */