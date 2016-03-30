package com.company.test;

import com.company.semaphore.implemetation.SemaphoreImpl;

/**
 * Created by Yevgen on 30.03.2016 as a part of the project "JEE_Unit_3_Homework".
 */
public class SemaphoreTest {
    private final SemaphoreImpl semaphore;

    public SemaphoreTest(int permits) {
        semaphore = new SemaphoreImpl(permits);
    }

    public void demonstrate() {

    }
}


