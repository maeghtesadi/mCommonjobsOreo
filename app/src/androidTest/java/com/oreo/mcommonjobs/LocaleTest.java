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
        //sets locale to be tested
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        //get access to resources
        Context context = getInstrumentation().getTargetContext();
        Resources res = context.getResources();
        Configuration config = res.getConfiguration();
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    @Test
    public void testEnglishLocale() {
        //set locale to EN (english)
        setLocale("en");

        //get profile string from strings.xml
        String profileString = getTargetContext().getString(R.string.main_menu_profile_title);
        assertEquals("Profile", profileString);
    }

    @Test
    public void testFrenchLocale() {
        //set locale to FR (french)
        setLocale("fr");


        String profileString = getTargetContext().getString(R.string.main_menu_profile_title);
        assertEquals("Profil", profileString);
    }


}
