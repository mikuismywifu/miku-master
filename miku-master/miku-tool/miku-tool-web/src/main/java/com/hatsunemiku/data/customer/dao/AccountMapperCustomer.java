package com.hatsunemiku.data.customer.dao;

import com.hatsunemiku.data.model.Account;

/**
 * Project Name:hatsunemiku
 * File Name:${AccountMapperCustomer.java}
 * Package:com.hatsunemiku.data.customer.dao
 *
 * @author:panwang
 * @Description: ${todo}(用一句话描述该文件做什么)
 * Date:2017/3/2
 * @version:V1.0
 * @see:jdk7 Copyright (c) 2017, mikuismywifu@gmail.com All Rights Reserved.
 */
public interface AccountMapperCustomer {

    /**
     * 根据用户邮箱，查询是否存在该用户
     * @param account
     * @return
     * @throws Exception
     */
    Account selectByAccount(Account account) throws Exception;

    /**
     * 新增用户插入数据
     * @param account
     * @return
     * @throws Exception
     */
    Integer insertNewAccount(Account account) throws Exception;
}
