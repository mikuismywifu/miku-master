package com.hatsunemiku.data.controller;

import com.hatsunemiku.data.model.Account;
import com.hatsunemiku.data.service.IdaAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Project Name:hatsunemiku
 * File Name:${RegisterLoginController.java}
 * Package:com.hatsunemiku.data.controller
 *
 * @author:panwang
 * @Description: ${todo}(注册登录校验)
 * Date:2017/3/2
 * @version:V1.0
 * @see:jdk7 Copyright (c) 2017, mikuismywifu@gmail.com All Rights Reserved.
 */
@Controller
@RequestMapping("/")
public class RegisterLoginController {

    @Autowired
    private IdaAccountService idaAccountService;

    /**
     * 跳转至登录页面
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public String login(ModelMap modelMap) {
        return "major/login";
    }

    /**
     * 登录校验
     * @param modelMap
     * @param account
     * @return
     */
    @RequestMapping(value = "gologin", method = {RequestMethod.POST})
    @ResponseBody
    public String gologin(ModelMap modelMap, Account account) {
        return idaAccountService.selectAccount(account);
    }

    /**
     * 注册账户(只做邮箱注册，且不验证邮箱)
     *
     * @param modelMap
     * @param account
     * @return
     */
    @RequestMapping(value = "goregister", method = {RequestMethod.POST})
    @ResponseBody
    public String goregister(ModelMap modelMap, Account account) {
        return idaAccountService.insertNewAccount(account);
    }
}
