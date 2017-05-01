package com.szymongrochowiak.androidstarterpack.test.utils;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * @author Szymon Grochowiak
 */
public class RxJavaImmediateSchedulersRule implements TestRule {

    private final Scheduler mImmediate = new Scheduler() {
        @Override
        public Worker createWorker() {
            return new ExecutorScheduler.ExecutorWorker(Runnable::run);
        }
    };

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaPlugins.setInitIoSchedulerHandler(__ -> mImmediate);
                RxJavaPlugins.setInitComputationSchedulerHandler(__ -> mImmediate);
                RxJavaPlugins.setInitNewThreadSchedulerHandler(__ -> mImmediate);
                RxJavaPlugins.setInitSingleSchedulerHandler(__ -> mImmediate);
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> mImmediate);

                try {
                    base.evaluate();
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }
            }
        };
    }
}
