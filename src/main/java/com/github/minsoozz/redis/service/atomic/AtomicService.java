package com.github.minsoozz.redis.service.atomic;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class AtomicService {

    private final RedisTemplate<String, String> redisTemplate;

    public AtomicService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void incrementCount() {
        redisTemplate.opsForValue().increment("atomic-count");
    }

    public String getCount() {
        return redisTemplate.opsForValue().get("atomic-count");
    }
}
