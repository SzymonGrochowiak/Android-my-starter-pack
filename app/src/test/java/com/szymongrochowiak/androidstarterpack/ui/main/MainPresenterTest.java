package com.szymongrochowiak.androidstarterpack.ui.main;

import com.szymongrochowiak.androidstarterpack.dagger.ApplicationComponent;
import com.szymongrochowiak.androidstarterpack.dagger.RepositoryModule;
import com.szymongrochowiak.androidstarterpack.data.ApplicationRepository;
import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.test.utils.RxJavaImmediateSchedulersRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static org.mockito.Mockito.verify;

/**
 * @author Szymon Grochowiak
 */
public class MainPresenterTest {

    @Mock
    MainView mMainView;
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
        mMainPresenter.attachView(mMainView);
    }

    @After
    public void after() {
        mMainPresenter.detachView(false);
    }

    @Test
    public void show_content() throws Exception {
        mMainPresenter.queryBerry();
        verify(mMainView, Mockito.times(1)).showLoading();
        verify(mMainView, Mockito.times(1)).showContent(ArgumentMatchers.any());
        verify(mMainView, Mockito.times(1)).hideLoading();
    }
}
