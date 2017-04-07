package com.szymongrochowiak.androidstarterpack.ui.common.dialog.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.delegate.FragmentMvpDelegate;
import com.hannesdorfmann.mosby3.mvp.delegate.FragmentMvpDelegateImpl;
import com.hannesdorfmann.mosby3.mvp.delegate.MvpDelegateCallback;

/**
 * @author Szymon Grochowiak
 */
public abstract class BaseDialogFragment<V extends MvpView, P extends MvpPresenter<V>> extends DialogFragment
        implements MvpDelegateCallback<V, P> {

    @NonNull
    private FragmentMvpDelegate<V, P> mMvpDelegate = new FragmentMvpDelegateImpl<>(this, this, true, true);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMvpDelegate.onAttach((Activity) context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mMvpDelegate.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMvpDelegate.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMvpDelegate.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMvpDelegate.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mMvpDelegate.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMvpDelegate.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMvpDelegate.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMvpDelegate.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMvpDelegate.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMvpDelegate.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMvpDelegate.onDestroy();
    }
}
