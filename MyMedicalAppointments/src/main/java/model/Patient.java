package model;

import java.util.ArrayList;
import java.util.Date;

public class Patient extends User {

    private String birthday;
    private double weight;
    private double height;
    private String blood;
    
    private ArrayList<AppointmentDoctor> AppointmentDoctors = new ArrayList<>();
    private ArrayList<AppointmentNurse> AppointmentNurses = new ArrayList<>();

    public Patient(String name, String email) {
        super(name, email);
    }

    public String getHeight() {
        return this.height +" Mts.";
    }

    public void setHeight(double height) {
        this.height = height;
    }


    public String getWeight() {
        return this.weight + "Kg.";
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    @Override
    public String toString() {
        return super.toString() + "\nAge: "+birthday+"\nWeight: "+getWeight()+
                "\nHeight: "+getHeight()+"\nBlood: "+blood;
    }

    public ArrayList<AppointmentDoctor> getAppointmentDoctors() {
        return AppointmentDoctors;
    }

    public void addAppointmentDoctors(Doctor doctor, Date date, String time) {
        AppointmentDoctor appointmentDoctor = new AppointmentDoctor(this, doctor);
        appointmentDoctor.schedule(date, time);
        //appointmentDoctor.add(appointmentDoctor);
    }

    public ArrayList<AppointmentNurse> getAppointmentNurses() {
        return AppointmentNurses;
    }

    public void setAppointmentNurses(ArrayList<AppointmentNurse> AppointmentNurses) {
        this.AppointmentNurses = AppointmentNurses;
    }
}
