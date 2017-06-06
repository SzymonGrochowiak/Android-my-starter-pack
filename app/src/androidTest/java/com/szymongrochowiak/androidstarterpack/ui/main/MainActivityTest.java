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

import io.appflate.restmock.RESTMockServer;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.mockwebserver.MockResponse;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static io.appflate.restmock.RESTMockServer.whenGET;
import static io.appflate.restmock.RequestsVerifier.verifyRequest;
import static io.appflate.restmock.utils.RequestMatchers.pathContains;
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
        RESTMockServer.reset();
        RxIdlingResource rxIdlingResource = new RxIdlingResource();
        Espresso.registerIdlingResources(rxIdlingResource);
        RxJavaPlugins.setScheduleHandler(rxIdlingResource);
    }

    @Test
    public void show_content() throws Exception {
        whenGET(pathContains("berry")).thenReturnString(200, "{\"natural_gift_type\":{\"url\":\"http:\\/\\/pokeapi" +
                ".co\\/api\\/v2\\/type\\/2\\/\",\"name\":\"fighting\"},\"name\":\"mock berry\"," +
                "\"max_harvest\":5,\"soil_dryness\":15,\"smoothness\":20," +
                "\"item\":{\"url\":\"http:\\/\\/pokeapi.co\\/api\\/v2\\/item\\/131\\/\"," +
                "\"name\":\"leppa-berry\"},\"firmness\":{\"url\":\"http:\\/\\/pokeapi" +
                ".co\\/api\\/v2\\/berry-firmness\\/4\\/\",\"name\":\"very-hard\"},\"growth_time\":4,\"id\":6," +
                "\"size\":28}");

        mActivityTestRule.launchActivity(null);

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.berryTextView)).check(matches(withText("mock berry")));

        verifyRequest(pathContains("berry")).invoked();
    }

    @Test
    public void show_error() throws Exception {
        MockResponse errorResponse = new MockResponse();
        errorResponse.setResponseCode(401);
        errorResponse.setBody("Unauthorized exception");
        whenGET(pathContains("berry")).thenReturn(errorResponse);

        mActivityTestRule.launchActivity(null);

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));

        verifyRequest(pathContains("berry")).invoked();
    }
}
