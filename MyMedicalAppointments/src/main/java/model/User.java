package model;

public abstract class User {

    static int id = 0;
    String name;
    String email;
    String address;
    String phoneNumber;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.length()==10){
            this.phoneNumber = phoneNumber;
        }
        else{
            System.out.println("el numero de celular es de 10 digitos");
        }
    }


    /* cuando se desea imprimir el usuario directamente, se obtiene el lugar
    en memoria donde se almacena el objeto. para cambiar esto, se escribe en
    intellij tostring +tab y se obtiene el metodo tostring. luego se cambia
    el comportamiento del return para que podamos visualizar los datos que
    queremos ver del objeto como e el siguiente ejemplo
    * */
    @Override
    public String toString() {
        return "\nUser: " + name + "\nEmail: " +email+
                "\nAddress: " + address+"\nPhone: "+phoneNumber;
    }
}
