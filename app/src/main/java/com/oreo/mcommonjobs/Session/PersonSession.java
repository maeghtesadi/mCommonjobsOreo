package com.oreo.mcommonjobs.Session;

import com.oreo.mcommonjobs.Models.Profile;

import java.util.List;

/**
 * This class consists methods for operating on a Job object.
 *
 * @author Jason
 * @author kimcodes
 */
public class PersonSession {

    private static PersonSession personCtrl = null;
    private String email;
    private String firstName;
    private String lastName;
    private String typeOfUser;
    private List<Profile> profiles;
    private String currentprofile;
    enum userType {JOBPROVIDER, JOBSEEKER}

    /**
     * Default constructor & exists only to defeat instantiation.
     */
    protected PersonSession() {

    }

    /**
     * Method returns the instance of person session
     *
     * @return If a person session exists - return the person session instance
     *         If a person session does not exist - create a person session and return the instance
     */
    public static PersonSession getInstance() {
        if (personCtrl == null) {
            personCtrl = new PersonSession();
        }
        return personCtrl;
    }


    public String getCurrentprofile() {
        return currentprofile;
    }

    public void setCurrentprofile(String currentprofile) {
        this.currentprofile = currentprofile;
    }

    public void addProfile(Profile p){

       this.profiles.add(p);
   }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    /**
     * Getter method for the type of the user.
     *
     * @return
     */
    public String getTypeOfUser() {
        return typeOfUser;
    }

    /**
     * Setter method for the type of the user.
     *
     * @param typeOfUser
     */
    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    /**
     * Getter method for the user's email.
     *
     * @return String, user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for the user's email.
     *
     * @param email String, user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for the user's last name.
     *
     * @return String, user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method for the user's last name.
     *
     * @param lastName String, user's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter method for the user's first name.
     *
     * @return String, user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method for the user's first name.
     *
     * @param firstName tring, user's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
