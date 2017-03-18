package com.oreo.mcommonjobs;




import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 *
 */










@RunWith(AndroidJUnit4.class)

public class JobInsertionTest {
    private Context instrumentationCtx;
/*
    final CountDownLatch signal = new CountDownLatch(1);
    Addjob addjob;


    @Before
    public void setup() {
        Looper.prepare();
        instrumentationCtx = InstrumentationRegistry.getTargetContext();

        addjob =  new Addjob(instrumentationCtx);

    }




    @Test
    public void JobInsertionTest() throws Exception {
        String type = "Care duties";
        String description = "something";
        ;

        addjob.execute("addjob", type, description);

        signal.await(2000, TimeUnit.MILLISECONDS);
        assertEquals("exists",addjob.getTestresult());


    }
    */
}
