package com.oreo.mcommonjobs;

import android.content.Context;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.oreo.mcommonjobs.Models.DatabaseTasks.UserExists;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by jason on 2017-02-26.
 */




@RunWith(AndroidJUnit4.class)
public class DatabaseLoginTest {

    private Context instrumentationCtx;
   // final Context context = getInstrumentation().getTargetContext();
    final CountDownLatch signal = new CountDownLatch(1);
    UserExists user ;


    @Before
    public void setup() {
        Looper.prepare();
        instrumentationCtx = InstrumentationRegistry.getTargetContext();

        user =  new UserExists(instrumentationCtx);

    }




    @Test
    public void UserExsistsTest() throws Exception {
    String email = "jtsalikis@hotmail.ca";

    user.execute("login", email);

        signal.await(2000, TimeUnit.MILLISECONDS);
        assertEquals("success",user.getTestresult());


    }



}





