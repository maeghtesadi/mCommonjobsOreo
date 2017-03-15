package com.oreo.mcommonjobs;


import android.content.Context;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.oreo.mcommonjobs.Models.DatabaseTasks.RegisterAccount;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jason on 2017-02-26.
 */














@RunWith(AndroidJUnit4.class)
public class InsertTest {

    private Context instrumentationCtx;

    final CountDownLatch signal = new CountDownLatch(1);
    RegisterAccount reg;


    @Before
    public void setup() {
        Looper.prepare();
        instrumentationCtx = InstrumentationRegistry.getTargetContext();

        reg =  new RegisterAccount(instrumentationCtx);

    }




    @Test
    public void dbtest() throws Exception {
        String email = "jtsalikis@hotmail.ca";
        String firstname = "jason";
        String lastname = "tsalikis";
        String typeofuser="Job provider";

        reg.execute("insert", firstname, lastname, email, typeofuser);

        signal.await(2000, TimeUnit.MILLISECONDS);
        assertEquals("exsists",reg.getTestresults());


    }
}





