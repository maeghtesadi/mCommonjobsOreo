package com.oreo.mcommonjobs.Session;

/**
 * Created by kimcodes on 2017-02-24.
 */

public class PersonSingleton {

    private String email;
    private String firstName;
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private static PersonSingleton personCtrl = null;

    protected PersonSingleton(){


    }


    public static PersonSingleton getInstance() {
        if(personCtrl == null) {
            personCtrl = new PersonSingleton();
        }
        return personCtrl;
    }
}
