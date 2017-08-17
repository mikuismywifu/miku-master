package com.hatsunemiku.data.controller;

import com.hatsunemiku.data.annotation.AuthPassport;
import com.hatsunemiku.data.service.IdaAccountService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Project Name:hatsunemiku
 * File Name:${AccountController.java}
 * Package:com.hatsunemiku.data.controller
 *
 * @author:panwang
 * @Description: ${todo}(查询用户信息)
 * Date:2017/3/2
 * @version:V1.0
 * @see:jdk7 Copyright (c) 2017, mikuismywifu@gmail.com All Rights Reserved.
 */
@Controller
@RequestMapping("/")
public class AccountController {

    @Autowired
    private IdaAccountService idaAccountService;

    @Autowired
    private HttpSession httpSession;

    /**
     * 主页显示
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "index", method = {RequestMethod.GET})
    @AuthPassport//拦截器实现未登录无法访问后续页面，跳转至登录页面
    public String index(ModelMap modelMap) {

        modelMap.put("account", idaAccountService.selectById((Integer) httpSession.getAttribute("userId")));
        return "major/index";
    }

    @RequestMapping(value = "gps", method = {RequestMethod.GET})
    public String GPS(ModelMap modelMap) {
        return "major/GPS";
    }

    public static void main(String[] args) {
        String host = "http://yhk.market.alicloudapi.com";
        String path = "/rest/160601/ocr/ocr_bank_card.json";
        String method = "POST";
        String appcode = "你自己的AppCode";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        String bodys = "{\"inputs\": [ {\"image\": {\"dataType\": 50,\"dataValue\": \"base64_image_string\" } }] }";

        try {
        /**
         * 重要提示如下:
         * HttpUtils请从
         * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
         * 下载
         *
         * 相应的依赖请参照
         * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
         */
//        HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//        System.out.println(response.toString());
        //获取response的body
        //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
        e.printStackTrace();
        }
        }
}
