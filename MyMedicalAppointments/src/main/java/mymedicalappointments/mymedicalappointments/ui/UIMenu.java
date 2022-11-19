package mymedicalappointments.mymedicalappointments.ui;
import java.util.ArrayList;
import java.util.Scanner;
import model.Doctor;
import model.Patient;

public class UIMenu {
    final static String[] MONTHS = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
    public static Doctor doctorLogged;
    public static Patient patientLogged;
    
    public static void showMenu(){
        System.out.println("Welcome to My Appointments");
        System.out.println("Selecciona la opción deseada");

        int response = 0;
        do {
            System.out.println("1. Doctor");
            System.out.println("2. Patient");
            System.out.println("0. Salir");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    System.out.println("Doctor");
                    response = 0;
                    authUser(1);
                    break;
                case 2:
                    response = 0;
                    authUser(2);
                    break;
                case 0:
                    System.out.println("Thank you for you visit");
                    break;
                default:
                    System.out.println("Please select a correct answer");
            }
        }while (response != 0);
    }
    
    private static void authUser(int userType){
        //userType = 1 doctor
        //userType = 2 paciente
        ArrayList<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("alejandro martinez","alej@mail.com"));
        doctors.add(new Doctor("rocio gomez","roci@mail.com"));
        doctors.add(new Doctor("karen sosa","karen@mail.com"));
    
        ArrayList<Patient> patients = new ArrayList<>();
        patients.add(new Patient("jonathan chila","chila@mail.com"));
        patients.add(new Patient("montserrat sanchez","montse@mail.com"));
        patients.add(new Patient("andres anaya","camilo@mail.com"));
        
        boolean emailCorrect = false;
        do {
            System.out.println("Inset your email: [a@mail.com]");
            Scanner scan1 = new Scanner(System.in);
            String email = scan1.nextLine();
            if (userType == 1) {
                for (Doctor d : doctors) {
                    if (d.getEmail().equals(email)) {
                        emailCorrect = true;
                        System.out.println("DATO VALIDADO");
                        doctorLogged = d;
                        UIDoctorMenu.showDoctorMenu();
                    }
                }
            }
            if (userType == 2) {
                for (Patient p : patients) {
                    if(p.getEmail().equals(email))
                        emailCorrect = true;
                        patientLogged = p;
                        UIPatientMenu.showPatientMenu();
                }
            }
        } while (!emailCorrect);
    }
    
    static void showPatientMenu(){
        int response = 0;
        do {
            System.out.println("\n\n");
            System.out.println("Paciente");
            System.out.println("1. agenda una cita");
            System.out.println("2. mis citas");
            System.out.println("0. salir");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    System.out.println("::Book an appointment");
                    for (int i = 1; i < 4; i++) {
                        System.out.println(i+". "+MONTHS[i-1]);
                    }
                    break;
                case 2:
                    System.out.println("::My appointments");
                    
                    break;
                case 0:
                    showMenu();
                    break;
            }
        }while (response != 0);
    }
}
