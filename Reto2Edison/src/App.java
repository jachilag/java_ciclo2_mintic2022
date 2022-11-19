import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        // Pantallazo 1
        String[] cola = { "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11" };
        TurnoVirtual turnoVirtual1 = new TurnoVirtual(cola);

        // Pantallazo 2
        turnoVirtual1.atenderProximoTurno();
        turnoVirtual1.atenderProximoTurno();
        turnoVirtual1.atenderProximoTurno();
        turnoVirtual1.atenderProximoTurno();

        // Pantallazo 3
        turnoVirtual1.agregarTurnoPerdido();
        turnoVirtual1.atenderProximoTurno();
        turnoVirtual1.agregarTurnoPerdido();
        turnoVirtual1.atenderProximoTurno();
        turnoVirtual1.agregarTurnoPerdido();

        // Pantallazo 4
        turnoVirtual1.cambiarEstadoTurno();
        turnoVirtual1.atenderProximoTurno();
        turnoVirtual1.cambiarEstadoTurno();

        System.out.print("Turnos: ");
        System.out.println(Arrays.toString(turnoVirtual1.getTurnos()));
        System.out.print("Turnos Perdidos: ");
        System.out.println(Arrays.toString(turnoVirtual1.getTurnosPerdidos()));
        System.out.print("Estado del Turno Virtual: ");
        System.out.println(turnoVirtual1.isEstadoTurnoVirtual());
        System.out.print("Turno en Atención: ");
        System.out.println(turnoVirtual1.getTurnoEnAtencion());
        System.out.print("Cantidad de turnos atendidos:");
        System.out.println(turnoVirtual1.getCantidadTurnosAtendidos());
    }
}
