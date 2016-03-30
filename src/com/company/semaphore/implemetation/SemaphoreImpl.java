package com.company.semaphore.implemetation;

import com.company.semaphore.interfaces.Semaphore;

/**
 * Created by Yevgen on 28.03.2016 as a part of the project "JEE_Unit_3_Homework".
 */
public class SemaphoreImpl implements Semaphore {
    private static final String INVALID_ARGUMENT_PATTERN = "%d is invalid argument";

    private int permits;

    public SemaphoreImpl(int permits) throws IllegalArgumentException  {
        checkNonNegativeArgument(permits);

        this.permits = permits;
    }

    public SemaphoreImpl() throws IllegalAccessException {
        this (0);
    }

    private void checkNonNegativeArgument(int argument) throws IllegalArgumentException {
        if (argument < 0 ) {
            throw new IllegalArgumentException(String.format(INVALID_ARGUMENT_PATTERN, argument));
        }
    }

    @Override
    public void acquire(int permits) throws InterruptedException, IllegalArgumentException  {
        checkNonNegativeArgument(permits);
        if (Thread.interrupted()) throw new InterruptedException();

        synchronized (this) {
            try {
                while (this.permits < permits) {
                    wait();
                }
                this.permits -= permits;
            } catch (InterruptedException e) {
                notify();
                throw e;
            }
        }
    }

    @Override
    public void acquire() throws InterruptedException {
        acquire(1);
    }

    @Override
    public synchronized void release(int permits) throws InterruptedException, IllegalArgumentException {
        checkNonNegativeArgument(permits);

        for (int i = 0; i < permits; i++) {
            notify();
        }

        this.permits += permits;
    }

    @Override
    public void release() throws InterruptedException {
        release(1);
    }

    @Override
    public synchronized int getAvailablePermits() {
        return permits;
    }
}
