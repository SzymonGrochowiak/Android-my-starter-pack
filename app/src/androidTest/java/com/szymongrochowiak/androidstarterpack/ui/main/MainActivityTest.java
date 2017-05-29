package com.szymongrochowiak.androidstarterpack.ui.main;

import android.support.test.rule.ActivityTestRule;

import com.szymongrochowiak.androidstarterpack.test.utils.DisableAnimationsRule;
import com.szymongrochowiak.androidstarterpack.test.utils.RxJavaImmediateSchedulersRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Szymon Grochowiak
 */
public class MainActivityTest {

    @ClassRule
    public static DisableAnimationsRule disableAnimationsRule = new DisableAnimationsRule();
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Test
    public void show_content() throws Exception {
        mActivityTestRule.launchActivity(null);
        Thread.sleep(5000);
        Assert.assertEquals(1, 0);
    }
}
