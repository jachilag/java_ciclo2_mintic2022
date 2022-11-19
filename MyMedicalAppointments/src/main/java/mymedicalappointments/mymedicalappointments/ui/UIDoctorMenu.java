package mymedicalappointments.mymedicalappointments.ui;

import java.util.ArrayList;
import java.util.Scanner;
import model.Doctor;


public class UIDoctorMenu {
    
    public static ArrayList<Doctor> doctorsAvailableAppointments = new ArrayList<>();
    public static void showDoctorMenu(){
        int response = 0;
        do {            
            System.out.println("\n\n");
            System.out.println("Doctor");
            System.out.println("welcome "+ UIMenu.doctorLogged.getName());
            System.out.println("1. añadir cita disponible");
            System.out.println("2. my horario de citas");
            System.out.println("0. Logout");
            
            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());               
        
            switch (response) {
                case 1:
                    showAddAvailableAppointmentsMenu();
                    break;
                case 2:
                    
                    break;
                case 0:
                    UIMenu.showMenu();
                    break;
            }                      
        } while (response != 0);
    }

    private static void showAddAvailableAppointmentsMenu(){
        int response = 0;
        do {            
            System.out.println();
            System.out.println("::añadir cita disponible");
            System.out.println("::seleccionar un mes");
            
            for (int i = 0; i < 3; i++) {
                int j = i+1;
                System.out.println(j+". " +UIMenu.MONTHS[i]);                
            }
            
            System.out.println("0. Retornar");
            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());            
            
            if (response>0 && response<4) {
                //1,2,3
                int monthSelected = response;
                System.out.println(monthSelected + ". "+ UIMenu.MONTHS[monthSelected-1]);
                System.out.println("Insertar la fecha disponible: [dd/mm/yyyy]");
                String date = sc.nextLine();
                System.out.println("tu fecha es: "+date+"\n1. Correcto \n2. Cambiar Fecha");
                int responseDate = Integer.valueOf(sc.nextLine());
                if (responseDate == 2) continue;
                
                int responsiveTime = 0;
                String time = "";
                do {
                    System.out.println("inserte la hora disponible para la cita " + date + "[16:00]");
                    time = sc.nextLine();
                    System.out.println("tu fecha es: "+ time+ "\n1. Correcto \n2. Cambiar Fecha");
                    responsiveTime = Integer.valueOf(sc.nextLine());
                } while (responsiveTime == 2);
                UIMenu.doctorLogged.addAvailableAppointment(date, time);
                checkDoctorAvailableAppointments(UIMenu.doctorLogged);
                
                
            } else if (response == 0){
                showDoctorMenu();
            }
            
        } while (response !=0);
    }
    private static void checkDoctorAvailableAppointments(Doctor doctor){
        if (doctor.getAvialableAppointments().size()>0 && !doctorsAvailableAppointments.contains(doctor)) {
            doctorsAvailableAppointments.add(doctor);
        }
    }
}
