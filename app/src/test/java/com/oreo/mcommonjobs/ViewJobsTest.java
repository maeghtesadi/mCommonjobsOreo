package com.oreo.mcommonjobs;

import android.content.Context;

import com.oreo.mcommonjobs.Controllers.JobSeekerController;
import com.oreo.mcommonjobs.Models.Job;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by jason on 2017-03-18.
 */

@RunWith(AndroidJUnit4.class)
public class ViewJobsTest {

    private Context instrumentationCtx;
    JobSeekerController jobseeker = new JobSeekerController();
    private List<Job> listOfJobs = new ArrayList<>();



    @Before
    public void setup() {
        instrumentationCtx = InstrumentationRegistry.getContext();
    }



    @Test
    public void ViewJobsListQueredTest() throws Exception {


      listOfJobs=  jobseeker.getJobs(instrumentationCtx);
        assertThat(listOfJobs, is(not(empty())));

    }


}

