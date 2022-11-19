public abstract class Estudiante {
    String nombre ;
    int edad;
    String programa ;
    String tipo_etnia ;

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


}
