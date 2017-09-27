package com.szymongrochowiak.androidstarterpack.ui.main;

import com.szymongrochowiak.androidstarterpack.dagger.ApplicationComponent;
import com.szymongrochowiak.androidstarterpack.dagger.NetworkingModule;
import com.szymongrochowiak.androidstarterpack.dagger.RepositoryModule;
import com.szymongrochowiak.androidstarterpack.data.ApplicationRepository;
import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.test.data.DataProvider;
import com.szymongrochowiak.androidstarterpack.test.utils.RxJavaImmediateSchedulersRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

import io.reactivex.Observable;
import it.cosenonjaviste.daggermock.DaggerMockRule;

import static com.szymongrochowiak.androidstarterpack.test.utils.TestUtils.TEST_URL;
import static com.szymongrochowiak.androidstarterpack.test.utils.TestUtils.onlyOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * @author Szymon Grochowiak
 */
public class MainPresenterTest {

    @Mock
    MainView mMainView;
    Repository mRepository;
    MainPresenter mMainPresenter;
    Repository mSpyRepository;
    DataProvider mDataProvider;

    @Rule
    public RxJavaImmediateSchedulersRule mRxJavaSchedulersRule = new RxJavaImmediateSchedulersRule();

    @Rule
    public DaggerMockRule<ApplicationComponent> mDaggerRule =
            new DaggerMockRule<>(ApplicationComponent.class, new NetworkingModule(TEST_URL),
                    new RepositoryModule()).set(new DaggerMockRule.ComponentSetter<ApplicationComponent>() {
                @Override
                public void setComponent(ApplicationComponent component) {
                    mRepository = component.repository();
                }
            });

    @Before
    public void before() {
        mDataProvider = new DataProvider();
        mSpyRepository = spy(mRepository);
        mMainPresenter = new MainPresenter(mSpyRepository, (ApplicationRepository) mSpyRepository);
        mMainPresenter.attachView(mMainView);
    }

    @After
    public void after() {
        mMainPresenter.detachView(false);
    }

    @Test
    public void show_content() throws Exception {
        doReturn(mDataProvider.createBerry()).when(mSpyRepository).queryBerry(1);
        mMainPresenter.queryBerry(1);
        verify(mMainView, onlyOnce()).showLoading();
        verify(mMainView, onlyOnce()).showContent(ArgumentMatchers.any());
        verify(mMainView, onlyOnce()).hideLoading();
        verify(mMainView, never()).showError(ArgumentMatchers.any());
    }

    @Test
    public void show_error() throws Exception {
        doReturn(Observable.error(new RuntimeException("Test error"))).when(mSpyRepository).queryBerry(1);
        mMainPresenter.queryBerry(1);
        verify(mMainView, onlyOnce()).showLoading();
        verify(mMainView, onlyOnce()).showError(ArgumentMatchers.any());
        verify(mMainView, onlyOnce()).hideLoading();
        verify(mMainView, never()).showContent(ArgumentMatchers.any());
    }
}
