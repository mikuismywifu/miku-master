package com.hatsunemiku.data.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Name:hatsunemiku
 * File Name:${FILE_NAME}
 * Package:com.hatsunemiku.data.util
 *
 * @author:panwang
 * @Description: ${todo}(用一句话描述该文件做什么)
 * Date:2017/3/2
 * @version:V1.0
 * @see:jdk7 Copyright (c) 2017, mikuismywifu@gmail.com All Rights Reserved.
 */
public class JsonUtils {

    /**
     * 创建一个简单的成功json串
     */
    public static final String createSimpleJson(Integer code , String message,String url)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", code);
        map.put("message", message);
        map.put("url",url);
        return JSON.toJSONString(map).toString();
    }
}
