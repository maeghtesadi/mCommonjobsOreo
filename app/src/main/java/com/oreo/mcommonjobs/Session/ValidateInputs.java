package com.oreo.mcommonjobs.Session;

/**
 * This class consists methods which validate the inputs of various methods in our application's activities
 *
 * @author Jason
 * @author Rameen
 */

public class ValidateInputs {
    private static ValidateInputs validateInputs = null;


    public static ValidateInputs getInstance() {
        if (validateInputs == null) {
            validateInputs = new ValidateInputs();
        }
        return validateInputs;
    }

    public boolean ValidateCreatePosting(String typeofjob, final String descriptionofjob, final String email){
        if(typeofjob.isEmpty() || descriptionofjob.isEmpty() || email.isEmpty() ){
            return false;
        }else {
            return true;
        }
    }

}
