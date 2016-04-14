package com.company.test;

import com.company.semaphore.implemetation.SemaphoreImpl;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by Yevgen on 30.03.2016 as a part of the project "JEE_Unit_3_Homework".
 */
public class SemaphoreTest {
    private static final String THREAD_STARTED_PATTERN = "Thread <%s> started, %d permit(s) are (is) available";
    private static final String THREAD_STARTED_TO_SLEEP_PATTERN = "Thread <%s> started to sleep for %d ms (%d permit(s) are (is) available)";
    private static final String THREAD_WOKE_UP_PATTERN =
            "Thread <%s> woke up, %d permit(s) are (is) available; there are also %d active threads";
    private static final String THREAD_RELEASED_PERMITS_PATTERN = "RELEASER: Thread <%s> released %d permits";

    private static final int START_SEMAPHORE_PERMITS = 5;
    private static final int THREAD_COUNT = 10;
    private static final int MAX_SLEEP_INTERVAL = 2000;

    private final SemaphoreImpl semaphore = new SemaphoreImpl(START_SEMAPHORE_PERMITS);
    private Random random = new Random();

    public void demonstrate() {
        IntStream.range(0, THREAD_COUNT).forEach(i -> new Thread(() -> {
            try {
                String threadName = Thread.currentThread().getName();
                int sleepInterval = random.nextInt(MAX_SLEEP_INTERVAL);
                System.out.println(String.format(THREAD_STARTED_PATTERN, threadName, semaphore.getAvailablePermits()));

                semaphore.acquire();

                System.out.println(String.format(THREAD_STARTED_TO_SLEEP_PATTERN, threadName, sleepInterval,
                        semaphore.getAvailablePermits()));
                Thread.sleep(sleepInterval);
                System.out.println(String.format(THREAD_WOKE_UP_PATTERN, threadName, semaphore.getAvailablePermits(),
                        Thread.activeCount()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());

        new Thread(() -> {
            try {
                String threadName = Thread.currentThread().getName();
                do {
                    int sleepInterval = random.nextInt(MAX_SLEEP_INTERVAL);
                    int releasePermitsCount = random.nextInt(START_SEMAPHORE_PERMITS / 2) + 1;

                    semaphore.release(releasePermitsCount);

                    System.out.println(String.format(THREAD_RELEASED_PERMITS_PATTERN, threadName, releasePermitsCount));
                    System.out.println(String.format(THREAD_STARTED_TO_SLEEP_PATTERN, threadName, sleepInterval,
                            semaphore.getAvailablePermits()));
                    Thread.sleep(sleepInterval);

                    System.out.println(String.format(THREAD_WOKE_UP_PATTERN, threadName, semaphore.getAvailablePermits(),
                            Thread.activeCount()));

                } while (semaphore.getAvailablePermits() < START_SEMAPHORE_PERMITS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}


