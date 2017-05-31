package com.szymongrochowiak.androidstarterpack.ui.main;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.szymongrochowiak.androidstarterpack.R;
import com.szymongrochowiak.androidstarterpack.test.utils.DisableAnimationsRule;
import com.szymongrochowiak.androidstarterpack.test.utils.RxIdlingResource;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import io.reactivex.plugins.RxJavaPlugins;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

/**
 * @author Szymon Grochowiak
 */
public class MainActivityTest {

    @ClassRule
    public static DisableAnimationsRule disableAnimationsRule = new DisableAnimationsRule();

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);


    @Before
    public void before() {
        RxIdlingResource rxIdlingResource = new RxIdlingResource();
        Espresso.registerIdlingResources(rxIdlingResource);
        RxJavaPlugins.setScheduleHandler(rxIdlingResource);
    }

    @Test
    public void show_content() throws Exception {
        mActivityTestRule.launchActivity(null);

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.textView)).check(matches(withText("mock leppa")));
    }
}
