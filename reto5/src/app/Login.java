package app;

import static app.funProp.*;
import intencionCompra.IntencionCompra;
import user.Administrador;
import user.User;
import user.User.USUARIO;


public class Login {
    //atributos
    public String textCreditos;

    //constructor
    public Login() {
        menuLogin();
    }

    //metodos
    private void menuLogin() {
        boolean validacion = false;
        User user1 = new User();
        User user2 = new User();
        do {
            System.out.println("\n*********** Menu Login ***********" + 
                            "\n1. Crear usuario" +
                            "\n2. Cargar usuario" +
                            "\n3. Creditos" +
                            "\n0. FINALIZAR PROGRAMA"
                            );
            String opcion = Input("\nSeleccione una opcion: ");
            switch (opcion) {
                case "0":
                    validacion = true;
                    break;
                case "1":
                    user1.createEntity();
                    break;
                case "2":
                    user2.logUser();
                    USUARIO user = user2.getPerfil();
                    switch (user) {
                        case ADMINISTRADOR:
                            Administrador administrador = new Administrador();
                            administrador.menuAdministrator();
                            break;
                        case CLIENTE:
                            IntencionCompra intencion = new IntencionCompra(user2.getAlias());
                            System.out.println("\n********* Bienvenid@ " + user2.getAlias() +" *********\n");
                            intencion.menuTipoTransporte();
                            System.out.println("\nCONSULTA REALIZADA\n");
                            break;
                        default:
                            break;
                    }
                case "3":
                    textCreditos = "\n" + repetNtimes("=", 50);
                    textCreditos += "\nTrabajo realizado por: ";
                    textCreditos += "\nJonathan Alexander Chila Guevara";
                    textCreditos += "\nRuta de aprendizaje MINTIC 2022\nDesarrollo de software, ciclo 2";
                    textCreditos += "\n" + repetNtimes("=", 50);
                    System.out.println(textCreditos);
                    break;
                default:
                    System.out.println("OPCION NO VALIDA - elija de nuevo");
                    break;
            }
        } while (!validacion);
    }
}