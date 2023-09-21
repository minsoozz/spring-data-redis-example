package com.github.minsoozz.redis.service.transaction;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

@SpringBootTest
class SessionCallbackServiceTest {

    @Autowired
    private SessionCallbackService sessionCallbackService;

    @Test
    void 예외가_발생하면_롤백된다() {

        assertThatThrownBy(() -> sessionCallbackService.incrementCount())
                .isInstanceOf(RuntimeException.class);

        String count = sessionCallbackService.getCount();
        // then
        Assertions.assertThat(count).isNull();
    }
}