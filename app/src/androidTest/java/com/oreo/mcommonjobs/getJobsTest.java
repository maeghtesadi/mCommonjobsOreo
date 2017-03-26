package com.oreo.mcommonjobs;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import com.oreo.mcommonjobs.Controllers.JobSeekerController;
import com.oreo.mcommonjobs.Models.Job;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by jason on 2017-03-25.
 */
@RunWith(AndroidJUnit4.class)
public class getJobsTest {

    private Context context;

    JobSeekerController jobSeekerController;

    List<Job> jobs;
    /**
     * Setup method to run before every test to set the target context.
     */
    @Before
    public void setup() {
        context = getTargetContext();
        jobSeekerController = new JobSeekerController();



    }


    @Test
    public void testGetJobResponse() {



        assertNotNull( jobSeekerController.getJobs(context));
       // assertTrue(jobs.size()==1); // size of entries on db




    }
}