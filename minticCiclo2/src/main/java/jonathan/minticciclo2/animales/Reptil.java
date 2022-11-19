package jonathan.minticciclo2.animales;


public class Reptil  extends Animal{
    private String especie;
    private boolean oviparo;
    private int edad;

    public Reptil(String especie, boolean oviparo, int edad) {
        this.especie = especie;
        this.oviparo = oviparo;
        this.edad = edad;
    }
    


    public Reptil(double peso, double altura, String color, String especie, boolean oviparo, int edad) {
        super(peso, altura, color);
        this.especie = especie;
        this.oviparo = oviparo;
        this.edad = edad;
    }


    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public boolean isOviparo() {
        return oviparo;
    }

    public void setOviparo(boolean oviparo) {
        this.oviparo = oviparo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String showInfo() {
        return "REPTIL: {" + "especie: "+ this.especie+", Es oviparo: "+this.oviparo+ ", edad: "+this.edad+"}";
    }

}
