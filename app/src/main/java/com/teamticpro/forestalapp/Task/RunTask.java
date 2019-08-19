package com.teamticpro.forestalapp.Task;

import android.content.Context;
import android.os.Handler;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Metrofico on 14/09/2017.
 */

public class RunTask {

    private Handler x;
    private Runnable e;
    private Thread t;
    private boolean stop;
    private Timer timer;
    private ThreadPoolExecutor executor;

    public RunTask() {
        stop = false;

    }

    public Runnable addTimerRunnable(final Runnable e, final long miliseconds) {
        this.e = e;
        stop = false;
        x = new Handler();
        Runnable n = new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    try {
                        Thread.sleep(miliseconds);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Runnable xd = e;
                    x.post(xd);
                }
            }
        };
        t = new Thread(n);
        t.start();


        // this method performs the task

       /* Runnable runnable = new Runnable() {
            @Override
            public void run() {

                x.postDelayed(e, 1000);
            }
        };
        x.postDelayed(runnable, 1000);
        new Thread(runnable).start();*/
        /*new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... params) {

                SystemClock.sleep(miliseconds);
                Looper.loop();
                return null;
            }
        }.execute();*/
        return e;
    }

    public Runnable AnimationRunnable(Context f, final Runnable e) {
        return new SafeRunnable(f) {

            @Override
            public void runWithParent(Context t1) {

                // ... animation code
                x.postDelayed(e, 1000);
                // repeat the animation in 1 second

            }

        };
    }

    public void pauseThread() {
        stop = true;
    }


    public void resumeThread() {
        stop = false;
    }

    public void addTaskRunnable(final Runnable e, final long miliseconds) {
        this.e = e;
        Runnable n = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Runnable xd = e;
                x.post(xd);
                cancel();

            }
        };
        t = new Thread(n);
        t.start();
    }

    public void cancel() {
        if (x != null) {
            x.removeCallbacks(e);
        }
        if (executor != null) {
            executor.shutdown();
        }
        if (timer != null) {
            timer.cancel();
        }
        stop = true;
    }


    public static class SafeRunnable implements Runnable {

        private final WeakReference<Context> parentReference;

        SafeRunnable(Context parent) {
            parentReference = new WeakReference<Context>(parent);
        }

        @Override
        public void run() {
            final Context parent = parentReference.get();
            if (parent != null) {
                runWithParent(parent);
            }
        }

        public void runWithParent(Context parent) {

        }
    }

}
