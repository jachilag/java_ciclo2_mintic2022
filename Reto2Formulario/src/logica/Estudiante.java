package logica;
public abstract class Estudiante {
    private String nombre ;
    private int edad;
    private String programa ;
    private String tipo_etnia ;
    private String modalidad;
    private int cantidad_creditos;

    public Estudiante(String nombre, String edad, String programa, String tipo_etnia) {
        this.nombre = nombre;
        this.edad = Integer.valueOf(edad);
        this.programa = programa;
        this.tipo_etnia = tipo_etnia;
    }
    
    
    public String toString(){
        return "\tNombre: " + nombre + "\n" +
                "\tEdad: " + edad + " anios\n" + 
                "\tPrograma: " + programa + "\n" +
                "\tEtnia: " + tipo_etnia + "\n";
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getPrograma() {
        return programa;
    }

    public String getTipo_etnia() {
        return tipo_etnia;
    }

    public int getCantidad_creditos() {
        return cantidad_creditos;
    }

    public String getModalidad() {
        return modalidad;
    }

}
