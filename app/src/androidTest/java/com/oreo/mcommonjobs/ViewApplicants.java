package com.oreo.mcommonjobs;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import com.oreo.mcommonjobs.Controllers.JobProviderController;
import com.oreo.mcommonjobs.Models.Application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by jason on 2017-03-26.
 */
@RunWith(AndroidJUnit4.class)
public class ViewApplicants {

    private Context context;

    JobProviderController jobProviderController;

    List<Application> applicants;
    /**
     * Setup method to run before every test to set the target context.
     */
    @Before
    public void setup() {
        context = getTargetContext();
        jobProviderController = new JobProviderController();



    }


    @Test
    public void testGetJobResponse() {

        String email = "jtsalikis@hotmail.ca";

        assertNotNull (jobProviderController.getApplicants(email ,context));


     //   assertTrue(applicants.size()==3); // size of entries on db



}}
