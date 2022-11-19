package jonathan.minticciclo2.animales;

public class Mamifero {
    private String especie;
    private int edad;
    private boolean marsupial;

    public Mamifero(String especie, int edad, boolean marsupial) {
        this.especie = especie;
        this.edad = edad;
        this.marsupial = marsupial;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isMarsupial() {
        return marsupial;
    }

    public void setMarsupial(boolean marsupial) {
        this.marsupial = marsupial;
    }

    public String showInfo() {
        return "MAMIFERO: {" + "especie: "+ this.especie+", edad: "+this.edad+ ", marsupial?: "+this.marsupial+"}";
    }
}

