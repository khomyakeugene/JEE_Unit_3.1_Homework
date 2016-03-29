package com.company.semaphore.implemetation;

import com.company.semaphore.interfaces.Semaphore;

/**
 * Created by Yevgen on 28.03.2016 as a part of the project "JEE_Unit_3_Homework".
 */
public class SemaphoreImpl implements Semaphore {
    private volatile int counter;

    public SemaphoreImpl(int counter) {
        this.counter = counter;
    }

    public SemaphoreImpl() {
        this (0);
    }

    @Override
    public void acquire() throws InterruptedException {

    }

    @Override
    public void acquire(int permits) throws InterruptedException {

    }

    @Override
    public void release() throws InterruptedException {


    }

    @Override
    public void release(int permits) throws InterruptedException {

    }

    @Override
    public int getAvailablePermits() {
        return 0;
    }
}
