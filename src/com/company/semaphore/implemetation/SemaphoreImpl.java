package com.company.semaphore.implemetation;

import com.company.semaphore.interfaces.Semaphore;

/**
 * Created by Yevgen on 28.03.2016 as a part of the project "JEE_Unit_3_Homework".
 */
public class SemaphoreImpl implements Semaphore {
    public static final String INVALID_ARGUMENT_PATTERN = "%d is invalid argument";

    private int permits;

    public SemaphoreImpl(int permits) throws IllegalArgumentException  {
        checkNonNegativeArgument(permits);

        this.permits = permits;
    }

    public SemaphoreImpl() throws IllegalAccessException {
        this (0);
    }

    void checkNonNegativeArgument(int argument) throws IllegalArgumentException {
        if (argument < 0 ) {
            throw new IllegalArgumentException(String.format(INVALID_ARGUMENT_PATTERN, argument));
        }
    }

    @Override
    public synchronized void acquire() throws InterruptedException {
        if (Thread.interrupted()) throw new InterruptedException();

        synchronized (this) {
            try {
                while (permits <= 0) {
                    wait();
                }
                permits--;
            } catch (InterruptedException e) {
                notify();
                throw e;
            }
        }
    }

    @Override
    public synchronized void acquire(int permits) throws InterruptedException, IllegalArgumentException  {
        checkNonNegativeArgument(permits);

    }

    @Override
    public synchronized void release() throws InterruptedException {
        permits++;
        notify();
    }

    @Override
    public void release(int permits) throws InterruptedException, IllegalArgumentException {
        checkNonNegativeArgument(permits);

    }

    @Override
    public synchronized int getAvailablePermits() {
        return permits;
    }
}
