package mymedicalappointments.mymedicalappointments.ui;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import model.Doctor;


public class UIPatientMenu {
    public static ArrayList<Doctor> doctorsAvailableAppointments = new ArrayList<>();
    public static void showPatientMenu(){
        int response = 0;
        do {            
            System.out.println("\n\n");
            System.out.println("Paciente");
            System.out.println("welcome "+ UIMenu.patientLogged.getName());
            System.out.println("1. agenda una cita ");
            System.out.println("2. mis citas");
            System.out.println("0. Logout");
            
            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());               
        
            switch (response) {
                case 1:
                    showBookAppointmentMenu();
                    break;
                case 2:
                    showPatientMyAppointments();
                    break;
                case 0:
                    UIMenu.showMenu();
                    break;
            }                      
        } while (response != 0);
    }
    private static void showBookAppointmentMenu(){
        int response = 0;
        do {            
            System.out.println();
            System.out.println("::agendar una cita");
            System.out.println("::seleccionar una fecha");
            //numeracion de la lista de fechas
            //indice fecha seleccionada
            //[doctors]
            //1.-doctor1
            //  -1. fecha1
            //  -2. fecha2
            //2.- doctor2
            //3.- doctor2
            Map<Integer,Map<Integer,Doctor>> doctors = new TreeMap<>();
            int k = 0;
            for (int i = 0; i < UIDoctorMenu.doctorsAvailableAppointments.size(); i++) {
                ArrayList<Doctor.AvialableAppointment> availableAppointments
                        = UIDoctorMenu.doctorsAvailableAppointments.get(i).getAvialableAppointments();
                Map<Integer,Doctor> doctorAppointments = new TreeMap<>();
                for (int j = 0; j < availableAppointments.size(); j++) {
                    k++;
                    System.out.println(k+". "+availableAppointments.get(j).getDate());
                    doctorAppointments.put(Integer.valueOf(j), UIDoctorMenu.doctorsAvailableAppointments.get(i));
                    doctors.put(Integer.valueOf(k), doctorAppointments);
                }
            }
            Scanner sc = new Scanner(System.in);
            int responseDateSelected = Integer.valueOf(sc.nextLine());  
            Map<Integer,Doctor> doctorAvailableSelected = doctors.get(responseDateSelected);
            Integer indexDate = 0;
            Doctor doctorSelected = new Doctor("","");
            
            for (Map.Entry<Integer, Doctor> doc : doctorAvailableSelected.entrySet()) {
                indexDate = doc.getKey();
                doctorSelected = doc.getValue();
            }
            System.out.println(doctorSelected.getName()+
                    ". fecha: "+doctorSelected.getAvialableAppointments().get(indexDate).getDate()+
                    ". hora"+doctorSelected.getAvialableAppointments().get(indexDate).getTime());
            
            System.out.println("Confirmar tu cita: \n1. si \n2. Cambiar datos");
            response = Integer.valueOf(sc.nextLine());
            
            if (response == 1) {
                UIMenu.patientLogged.addAppointmentDoctors(doctorSelected, 
                        doctorSelected.getAvialableAppointments().get(indexDate).getDate(null),
                        doctorSelected.getAvialableAppointments().get(indexDate).getTime());
                showPatientMenu();
            }
            
            
        } while (response != 0);
    }   
    
    private static void showPatientMyAppointments(){
        int response = 0;
        do {
            System.out.println("::mis citas");
            if (UIMenu.patientLogged.getAppointmentDoctors().size() == 0) {
                System.out.println("no tiene citas");
                break;
            }
            
            for (int i = 0; i < UIMenu.patientLogged.getAppointmentDoctors().size(); i++) {
                int j = i + 1;
                System.out.println(j+ ". "+
                        "fecha: "+UIMenu.patientLogged.getAppointmentDoctors().get(i).getDate()+
                        "hora: "+ UIMenu.patientLogged.getAppointmentDoctors().get(i).getTime()+
                        "\ndoctor: "+ UIMenu.patientLogged.getAppointmentDoctors().get(i).getDoctor().getName());
            }
            System.out.println("0. Salir");
        } while (response != 0);
    }
}