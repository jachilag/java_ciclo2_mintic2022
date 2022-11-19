package logica;
public class Posgrado extends Estudiante {
    private String modalidad;

    public Posgrado(String nombre, String edad, String programa, String tipo_etnia, String modalidad) {
        super(nombre, edad, programa, tipo_etnia);
        this.modalidad = modalidad;
    }

    @Override
    public String toString() {
        return "Estudiante Posgrado\n" +
                super.toString() +
                "\tModalidad: " + modalidad + "\n";
    }

    @Override
    public String getModalidad() {
        return modalidad;
    }
}
