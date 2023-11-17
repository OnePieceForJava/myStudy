package com.wusp.util;

import java.util.Optional;

import com.alibaba.fastjson.JSONObject;

import org.springframework.util.Assert;

public class FastJsonUtil {

    public static void convertsExpectValueToSpecifiedValue(JSONObject jsonObject, String key, String expectValue,
        String specifiedValue) {
        if (jsonObject == null) { return; }
        Assert.notNull(expectValue, "convertsOlderValueToSpecifiedValue 方法中expectValue入参不能为空");
        jsonObject.computeIfPresent(key, (k, oldV) -> Optional.of(oldV).filter(v -> !expectValue.equals(v))
            .orElse(specifiedValue));
    }

}
