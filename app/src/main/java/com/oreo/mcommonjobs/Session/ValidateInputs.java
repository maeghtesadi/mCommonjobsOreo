package com.oreo.mcommonjobs.Session;

/**
 * Created by jason on 2017-03-26.
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
