package model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Doctor extends User {
    private ArrayList<AvialableAppointment> AvialableAppointments =new ArrayList<>();
    //constructor
    public Doctor(String name, String email){
        super(name,email);
        id++;
    }
      
    
    /* Se crea un arreglo tipo lista varible que puede almacenar objetos y 
    elementos de diferentes tipos. cada vez que se realice el llamado a la 
    funcion "addAvialableAppointment", esta creara un objeto y lo agregara al 
    arreglo
    */
    
    public void addAvailableAppointment(String date, String time){
        AvialableAppointments.add(new Doctor.AvialableAppointment(date,time));


    }
    
    // metodo que devuelve el arreglo de listas de las citas del doctor
    public ArrayList<AvialableAppointment> getAvialableAppointments(){
        return AvialableAppointments;
    }
    
    
    //clase interna citas disponibles
    public static class AvialableAppointment{
        private Date date;
        private String time;
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");

        public AvialableAppointment(String date, String time) {
            try {
                this.date = format.parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.time = time;
        }

        public Date getDate(String DATE) {
            return date;
        }
        public String getDate() {
            return format.format(date);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Available Appointments \nDate: "+date+"\nTime: "+time;
        }
    }
    //trae el resultado del metodo padre con el super. y agrega mas al resultado de este metodo
    @Override
    public String toString() {
        return super.toString() + "\nAvailable: " +AvialableAppointments.toString();
    }
}