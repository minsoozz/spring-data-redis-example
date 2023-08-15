package com.github.minsoozz.redis.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class AtomicServiceTest {

    @Autowired
    private AtomicService atomicService;

    @Test
    void 동시성_이슈가_발생하지_않는_연산() throws InterruptedException {

        final int peopleCount = 10;
        final ExecutorService executorService = Executors.newFixedThreadPool(peopleCount);
        final CountDownLatch countDownLatch = new CountDownLatch(peopleCount);

        // when
        for (int i = 0; i < peopleCount; i++) {
            executorService.execute(() -> {
                atomicService.incrementCount();
                countDownLatch.countDown();
            });
        }

        // then
        countDownLatch.await();
        String count = atomicService.getCount();
        Assertions.assertThat(count).isEqualTo("10");
    }
}