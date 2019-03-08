package com.song.api.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * created on 2019/3/3 10:27
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@RequestMapping("/test")
@RestController
public interface DemoApiService {

    @GetMapping("/demo")
    public Map<String, Object> demo();

    @GetMapping("/setKey")
    public Map<String, Object> setKey(String key, String value);

    @GetMapping("/getKey")
    public Map<String, Object> getKey(String key);
}
