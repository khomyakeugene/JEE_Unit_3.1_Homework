package com.company;

import com.company.test.SemaphoreTest;

public class Main {
    private static final int DEFAULT_SEMAPHORE_PERMITS = 10;

    public static void main(String[] args) {
        new SemaphoreTest(DEFAULT_SEMAPHORE_PERMITS).demonstrate();
    }
}
