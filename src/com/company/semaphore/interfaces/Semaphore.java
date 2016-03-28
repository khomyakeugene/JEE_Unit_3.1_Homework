package com.company.semaphore.interfaces;

/**
 * Created by Yevgen on 28.03.2016 as a part of the project "JEE_Unit_3_Homework".
 */
public interface Semaphore {
    // Acquires a permit from this semaphore, blocking until one is available
    public void acquire() throws InterruptedException;

    // Acquires the given number of permits from this semaphore, blocking until all are available
    public void acquire(int permits) throws InterruptedException;

    // Release permission, returning it to semaphore
    public void release();

    // Releases the given number of permits, returning them to the semaphore
    public void release(int permits);

    // Returns the current number of permits available in this semaphore at the moment
    public int getAvailablePermits();
}
