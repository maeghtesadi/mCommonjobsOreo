package com.oreo.mcommonjobs.Controller;

/**
 * Created by kimcodes on 2017-02-24.
 */

public class PersonController {

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

    private static PersonController personCtrl = null;

    protected PersonController(){


    }


    public static PersonController getInstance() {
        if(personCtrl == null) {
            personCtrl = new PersonController();
        }
        return personCtrl;
    }
}
