package jonathan.minticciclo2.animales;

public class Ave {
    private String especie;
    private boolean plumas;
    private boolean vuela;

    public Ave(String especie, boolean plumas, boolean vuela) {
        this.especie = especie;
        this.plumas = plumas;
        this.vuela = vuela;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public boolean isPlumas() {
        return plumas;
    }

    public void setPlumas(boolean plumas) {
        this.plumas = plumas;
    }

    public boolean isVuela() {
        return vuela;
    }

    public void setVuela(boolean vuela) {
        this.vuela = vuela;
    }

    public String showInfo() {
        return "AVE: {" + "especie: "+ this.especie+", tiene plumas?: "+this.plumas+ ", vuela?: "+this.vuela+"}";
    }
}
