package com.github.minsoozz.redis.service.transaction;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

@Service
public class SessionCallbackService {

    private final RedisTemplate<String, String> redisTemplate;

    public SessionCallbackService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void incrementCount() {
        redisTemplate.execute(new SessionCallback<>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue().set((K) "session-callback-count", (V) "1");

                if(true) {
                    throw new RuntimeException();
                }

                return operations.exec();
            }
        });
    }

    public String getCount() {
        return redisTemplate.opsForValue().get("session-callback-count");
    }
}
