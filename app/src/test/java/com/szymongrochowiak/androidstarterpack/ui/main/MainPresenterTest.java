package com.szymongrochowiak.androidstarterpack.ui.main;

import com.szymongrochowiak.androidstarterpack.dagger.ApplicationComponent;
import com.szymongrochowiak.androidstarterpack.dagger.RepositoryModule;
import com.szymongrochowiak.androidstarterpack.data.ApplicationRepository;
import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.test.utils.RxJavaImmediateSchedulersRule;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import it.cosenonjaviste.daggermock.DaggerMockRule;

import static org.junit.Assert.assertEquals;

/**
 * @author Szymon Grochowiak
 */
public class MainPresenterTest {

    Repository mRepository;
    MainPresenter mMainPresenter;

    @Rule
    public RxJavaImmediateSchedulersRule mRxJavaSchedulersRule = new RxJavaImmediateSchedulersRule();

    @Rule
    public DaggerMockRule<ApplicationComponent> mDaggerRule =
            new DaggerMockRule<>(ApplicationComponent.class, new RepositoryModule()).set(
                    new DaggerMockRule.ComponentSetter<ApplicationComponent>() {
                        @Override
                        public void setComponent(ApplicationComponent component) {
                            mRepository = component.repository();
                        }
                    });

    @Before
    public void before() {
        mMainPresenter = new MainPresenter(mRepository, (ApplicationRepository) mRepository);
    }

    @Test
    public void check_berry_querying_correctness() throws Exception {
        mMainPresenter.queryBerry();
        assertEquals(2, 0);
    }
}
