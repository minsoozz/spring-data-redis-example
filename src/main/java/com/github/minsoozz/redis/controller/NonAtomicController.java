package com.github.minsoozz.redis.controller;

import com.github.minsoozz.redis.service.atomic.NonAtomicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/non-atomic")
public class NonAtomicController {

    private final NonAtomicService nonAtomicService;

    public NonAtomicController(NonAtomicService nonAtomicService) {
        this.nonAtomicService = nonAtomicService;
    }

    @GetMapping
    public String get() {
        return nonAtomicService.getCount();
    }

    @PostMapping
    public void set() {
        nonAtomicService.incrementCount();
    }
}
