package com.github.minsoozz.redis.controller;

import com.github.minsoozz.redis.service.atomic.AtomicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/atomic")
public class AtomicController {

    private final AtomicService atomicService;

    public AtomicController(AtomicService atomicService) {
        this.atomicService = atomicService;
    }

    @GetMapping("/count")
    public String getCount() {
        return atomicService.getCount();
    }

    @PostMapping("/increment")
    public void increment() {
        atomicService.incrementCount();
    }
}
