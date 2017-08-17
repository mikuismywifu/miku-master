package com.hatsunemiku.data.service;

import com.hatsunemiku.data.model.Account;

public interface IdaAccountService {

    /**
     * 根据登录用户id查询用户信息
     * @param id
     * @return
     */
    Account selectById(Integer id);

    /**
     * 用户登录查询
     * @param account
     *          email:账户
     *          password:密码
     * @return
     */
    String selectAccount(Account account);

    /**
     * 新增用户插入数据
     *
     * @param account
     * @return
     */
    String insertNewAccount(Account account);
}
