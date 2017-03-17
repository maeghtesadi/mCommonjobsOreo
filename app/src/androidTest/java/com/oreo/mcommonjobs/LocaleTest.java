package com.oreo.mcommonjobs;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertEquals;

/**
 * This test class tests the multi-language support feature.
 *
 * @author Rameen
 * @author kimcodes
 */
@RunWith(AndroidJUnit4.class)
public class LocaleTest {
    private Context context;
    private final String LOCALE_ENGLISH_STRING = "en";
    private final String LOCALE_FRENCH_STRING = "fr";


    /**
     * Setup method to run before every test to set the target context.
     */
    @Before
    public void setup(){
        context = getTargetContext();
    }

    /**
     * This method sets the phone to the designated language.
     *
     * @param language  name of the language to set the locale to
     */
    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Context context = getInstrumentation().getTargetContext();
        Resources res = context.getResources();
        Configuration config = res.getConfiguration();
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    /**
     * Test - setting phone locale to English.
     * @result the application will display strings in the appropriate language
     */
    @Test
    public void testEnglishLocale() {
        setLocale(LOCALE_ENGLISH_STRING);
        String profileString = getTargetContext().getString(R.string.main_menu_profile_title);
        assertEquals("Profile", profileString);
    }

    /**
     * Test - setting phone locale to French.
     * @result the application will display strings in the appropriate language
     */
    @Test
    public void testFrenchLocale() {
        setLocale(LOCALE_FRENCH_STRING);
        String profileString = getTargetContext().getString(R.string.main_menu_profile_title);
        assertEquals("Profil", profileString);
    }

}
