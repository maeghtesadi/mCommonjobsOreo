package com.oreo.mcommonjobs;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static com.oreo.mcommonjobs.Activtity.SignInActivity.getContext;
import static junit.framework.Assert.assertEquals;

/**
 * Created by Rameen on 3/13/2017.
 */
@RunWith(AndroidJUnit4.class)
public class LocaleTest {
    private Context context;

    @Before
    public void setup(){
        context = getTargetContext();

    }
    private void setLocale(String language) {

        Locale locale = new Locale(language);
        // here we update locale for date formatters
        Locale.setDefault(locale);
        // here we update locale for app resources
        Context context = getInstrumentation().getTargetContext();
        Resources res = context.getResources();
        Configuration config = res.getConfiguration();
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    @Test
    public void testEnglishLocale() {
        setLocale("en");


        String profileString = getTargetContext().getString(R.string.main_menu_profile_title);
        assertEquals("Profile", profileString);
    }

    @Test
    public void testFrenchLocale() {
        setLocale("fr");


        String profileString = getTargetContext().getString(R.string.main_menu_profile_title);
        assertEquals("Profil", profileString);
    }


}
