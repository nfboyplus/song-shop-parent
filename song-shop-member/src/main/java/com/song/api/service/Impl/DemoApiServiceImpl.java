package com.song.api.service.Impl;

import com.song.api.service.DemoApiService;
import com.song.common.api.BaseApiService;
import com.song.common.redis.BaseRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * created on 2019/3/3 10:44
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Slf4j
@RestController
public class DemoApiServiceImpl extends BaseApiService implements DemoApiService {

    @Autowired
    private BaseRedisService baseRedisService;

    @Override
    public Map<String, Object> demo() {
        log.info("this is demo");
        return setResultSuccess();
    }

    @Override
    public Map<String, Object> setKey(String key, String value) {
        baseRedisService.setString(key, value);
        return setResultSuccess();
    }

    @Override
    public Map<String, Object> getKey(String key) {
        String value = baseRedisService.get(key);
        return setResultSuccessData(value);
    }

}
