package com.github.igabaydulin.aspect.sample.controller;

import com.github.igabaydulin.aspect.sample.controller.aspect.LogMapping;
import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    @LogMapping("logging")
    @GetMapping("/ping")
    public Map<String, ?> helloworld(@RequestParam String username) {
        return ImmutableMap.of("message", String.format("Hello, %s", username));
    }
}
