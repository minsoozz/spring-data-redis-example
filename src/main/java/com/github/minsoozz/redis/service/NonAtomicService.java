package com.github.minsoozz.redis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class NonAtomicService {
    private final RedisTemplate<String, String> redisTemplate;

    public NonAtomicService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void incrementCount() {
        String countValue = redisTemplate.opsForValue().get("non-atomic-count");
        int currentCount = Integer.parseInt(countValue != null ? countValue : "0");

        currentCount++;
        redisTemplate.opsForValue().set("non-atomic-count", String.valueOf(currentCount));
    }

    public String getCount() {
        return redisTemplate.opsForValue().get("non-atomic-count");
    }
}
