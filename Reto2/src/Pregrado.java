public class Pregrado extends Estudiante {
    int cantidad_creditos;

    public Pregrado(String nombre, String edad, String programa, String tipo_etnia, String cantidad_creditos) {
        super(nombre, edad, programa, tipo_etnia);
        this.cantidad_creditos = Integer.valueOf(cantidad_creditos);
    }

    @Override
    public String toString() {
        return "\tEstudiante Pregrado\n" +
                super.toString() +
                "\tCreditos aprobados: " + cantidad_creditos;
    }
}
