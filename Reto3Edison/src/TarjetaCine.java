import java.util.Map;

public class TarjetaCine {
    // Atributos
    private String idTarjeta;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private int edad;
    private double porcentajeDescuento;

    private Map<String, Integer> itemPrecio = Map.of("Boleta", 6000,
                                            "Combo 1 - Crispetas + Gaseosa", 8000,
                                            "Combo 2 - Perro + Gaseosa", 12000);

    // Constructor
    public TarjetaCine(String idTarjeta, String nombreCompleto, String email, 
    String telefono, int edad, double porcentajeDescuento) {

        this.idTarjeta = idTarjeta;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.edad = edad;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    // Método
    public double pagar(String[] cuenta) {
        double total = 0;
        for (String item : cuenta) {
            total += Double.valueOf(itemPrecio.get(item));
        }
        return total * (1 - porcentajeDescuento / 100);
    }

   
    // Getters y Setters
    public String getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(String idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}