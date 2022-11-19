public class TurnoVirtual {
    // atributos
    String[] turnos;
    String[] turnosPerdidos;
    boolean estadoTurnoVirtual = true;
    int turnoEnAtencion = 0;
    int cantidadTurnosAtendidos = 1;


    // constructor
    public TurnoVirtual(String[] turnos) {
        this.turnos = turnos;
        turnosPerdidos = new String[turnos.length];
        for (int i = 0; i < turnosPerdidos.length; i++) {
            turnosPerdidos[i] = " ";
        }
    }


    // metodos
    public void atenderProximoTurno() {
        if (estadoTurnoVirtual) {
            turnoEnAtencion++;
            cantidadTurnosAtendidos++;
        }
    }

    public void agregarTurnoPerdido() {
        int cont = 0;
        boolean bandera = true;

        while (bandera) {
            if(turnosPerdidos[cont] == " "){
                turnosPerdidos[cont] = turnos[turnoEnAtencion];
                bandera = false;
            }
            cont++;
        }
    }

    public void cambiarEstadoTurno() {
        estadoTurnoVirtual = estadoTurnoVirtual?false:true;
    }


    // getters y setters
    public String[] getTurnos() {
        return turnos;
    }

    public void setTurnos(String[] turnos) {
        this.turnos = turnos;
    }

    public String[] getTurnosPerdidos() {
        return turnosPerdidos;
    }

    public void setTurnosPerdidos(String[] turnosPerdidos) {
        this.turnosPerdidos = turnosPerdidos;
    }

    public boolean isEstadoTurnoVirtual() {
        return estadoTurnoVirtual;
    }

    public void setEstadoTurnoVirtual(boolean estadoTurnoVirtual) {
        this.estadoTurnoVirtual = estadoTurnoVirtual;
    }

    public int getTurnoEnAtencion() {
        return turnoEnAtencion;
    }

    public void setTurnoEnAtencion(int turnoEnAtencion) {
        this.turnoEnAtencion = turnoEnAtencion;
    }

    public int getCantidadTurnosAtendidos() {
        return cantidadTurnosAtendidos;
    }

    public void setCantidadTurnosAtendidos(int cantidadTurnosAtendidos) {
        this.cantidadTurnosAtendidos = cantidadTurnosAtendidos;
    }

}
