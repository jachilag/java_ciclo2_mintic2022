public class TarjetaPlata extends TarjetaCine {
    // Atributos
    private int cantidadVisitas;
    private boolean elegibleOro;

    // Constructor
    public TarjetaPlata(String idTarjeta, String nombreCompleto, String email,
            String telefono, int edad) {
                super(idTarjeta, nombreCompleto, email, telefono, edad, 10);

        cantidadVisitas = 0;
        elegibleOro = false;
    }

    // Método
    @Override
    public double pagar(String[] cuenta) {
        this.cantidadVisitas++;
        if (this.cantidadVisitas == 5) {
            this.elegibleOro = true;
        }
        return super.pagar(cuenta);
    }

    // Getters y Setters
    public int getCantidadVisitas() {
        return cantidadVisitas;
    }

    public void setCantidadVisitas(int cantidadVisitas) {
        this.cantidadVisitas = cantidadVisitas;
    }

    public boolean isElegibleOro() {
        return elegibleOro;
    }

    public void setElegibleOro(boolean elegibleOro) {
        this.elegibleOro = elegibleOro;
    }
}