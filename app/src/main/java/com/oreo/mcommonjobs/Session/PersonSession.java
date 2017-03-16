package com.oreo.mcommonjobs.Session;

/**
 * Created by Jason on 2017-02-24.
 */

public class PersonSession {

    private String email;
    private String firstName;
    private String lastName;
    private String typeofuser;

    public String getTypeofuser() {
        return typeofuser;
    }

    public void setTypeofuser(String typeofuser) {
        this.typeofuser = typeofuser;
    }

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

    private static PersonSession personCtrl = null;

    protected PersonSession() {


    }


    public static PersonSession getInstance() {
        if (personCtrl == null) {
            personCtrl = new PersonSession();
        }
        return personCtrl;
    }
}
