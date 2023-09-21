package com.github.minsoozz.redis.service;

import com.github.minsoozz.redis.service.atomic.NonAtomicService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class NonAtomicServiceTest {

    @Autowired
    private NonAtomicService nonAtomicService;

    @Test
    void 동시성_이슈가_발생하는_연산() throws InterruptedException {

        final int peopleCount = 10;
        final ExecutorService executorService = Executors.newFixedThreadPool(peopleCount);
        final CountDownLatch countDownLatch = new CountDownLatch(peopleCount);

        // when
        for (int i = 0; i < peopleCount; i++) {
            executorService.execute(() -> {
                nonAtomicService.incrementCount();
                countDownLatch.countDown();
            });
        }

        // then
        countDownLatch.await();
        String count = nonAtomicService.getCount();
        Assertions.assertThat(count).isNotEqualTo("10");
    }
}