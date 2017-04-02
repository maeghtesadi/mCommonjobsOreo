package com.oreo.mcommonjobs;

import android.support.test.runner.AndroidJUnit4;

import com.oreo.mcommonjobs.Session.ValidateInputs;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;

/**
 * Created by jason on 2017-03-26.
 */
@RunWith(AndroidJUnit4.class)
public class testValidationOfJobPosting {

    ValidateInputs validateInputs = ValidateInputs.getInstance();

    @Test
    public void testValidation(){

        String typeofjob="Painting Duties";
        String description="hi";
        String email="jtsalikis@hotmail.ca";


        assertTrue(validateInputs.ValidateCreatePosting(typeofjob,description,email));
    }



}
