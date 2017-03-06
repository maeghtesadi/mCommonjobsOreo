package com.oreo.mcommonjobs;




import android.content.Context;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.oreo.mcommonjobs.Models.DatabaseTasks.Addjob;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 *
 */










@RunWith(AndroidJUnit4.class)


public class JobInsertionTest {
    private Context instrumentationCtx;
    // final Context context = getInstrumentation().getTargetContext();
    final CountDownLatch signal = new CountDownLatch(1);
    Addjob addjob;


    @Before
    public void setup() {
        Looper.prepare();
        instrumentationCtx = InstrumentationRegistry.getTargetContext();

        addjob =  new Addjob(instrumentationCtx);

    }




    @Test
    public void dbtest() throws Exception {
        String type = "Care duties";
        String description = "something";
        ;

        addjob.execute("addjob", type, description);

        signal.await(2000, TimeUnit.MILLISECONDS);
        assertEquals("exists",addjob.getTestresult());


    }
}
