package com.szymongrochowiak.androidstarterpack.ui.main;

import android.support.test.rule.ActivityTestRule;

import com.szymongrochowiak.androidstarterpack.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.appflate.restmock.RESTMockServer;
import okhttp3.mockwebserver.MockResponse;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.szymongrochowiak.androidstarterpack.test.utils.HttpTestUtils.HTTP_CODE_FAIL_AUTHORIZATION;
import static com.szymongrochowiak.androidstarterpack.test.utils.HttpTestUtils.HTTP_CODE_SUCCESS;
import static io.appflate.restmock.RESTMockServer.whenGET;
import static io.appflate.restmock.RequestsVerifier.verifyRequest;
import static io.appflate.restmock.utils.RequestMatchers.pathContains;
import static org.hamcrest.CoreMatchers.not;

/**
 * @author Szymon Grochowiak
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void before() {
        RESTMockServer.reset();
    }

    @Test
    public void show_content() throws Exception {
        whenGET(pathContains("berry")).thenReturnFile(HTTP_CODE_SUCCESS, "berry.json");

        mActivityTestRule.launchActivity(null);

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.berryTextView)).check(matches(withText("mock berry")));

        verifyRequest(pathContains("berry")).invoked();
    }

    @Test
    public void show_error() throws Exception {
        MockResponse errorResponse = new MockResponse();
        errorResponse.setResponseCode(HTTP_CODE_FAIL_AUTHORIZATION);
        errorResponse.setBody("Unauthorized exception");
        whenGET(pathContains("berry")).thenReturn(errorResponse);

        mActivityTestRule.launchActivity(null);

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));

        verifyRequest(pathContains("berry")).invoked();
    }
}
