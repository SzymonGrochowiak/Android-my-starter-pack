package com.szymongrochowiak.androidstarterpack;

import android.support.test.rule.ActivityTestRule;

import com.szymongrochowiak.androidstarterpack.ui.main.MainActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Szymon Grochowiak
 */

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Test
    public void testBerry() throws Exception {
        mActivityTestRule.launchActivity(null);
        Thread.sleep(5000);
        Assert.assertEquals(1, 0);
    }
}
