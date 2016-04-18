package com.company.semaphore;

/**
 * Created by Yevgen on 28.03.2016 as a part of the project "JEE_Unit_3_Homework".
 */
public class SemaphoreImpl implements Semaphore {
    private static final String INVALID_ARGUMENT_PATTERN = "%d is invalid argument";

    private int startPermits;
    private int permits;

    public SemaphoreImpl(int permits) throws IllegalArgumentException  {
        checkNonNegativeArgument(permits);

        this.startPermits = permits;
        this.permits = permits;
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
                int requiredPermits = permits > this.startPermits ? this.startPermits : permits;
                while (this.permits < requiredPermits) {
                    wait();
                }
                this.permits -= requiredPermits;
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

        int maxAvailableAdditionalPermits = this.startPermits - this.permits;
        int availablePermits = permits < maxAvailableAdditionalPermits ? permits : maxAvailableAdditionalPermits;

        for (int i = 0; i < availablePermits; i++) {
            notify();
        }

        this.permits += availablePermits;
    }

    @Override
    public void release() throws InterruptedException {
        release(1);
    }

    @Override
    public synchronized int getAvailablePermits() {
        return this.permits;
    }
}
