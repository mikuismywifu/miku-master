package com.hatsunemiku.data.service.impl;

import com.hatsunemiku.data.customer.dao.AccountMapperCustomer;
import com.hatsunemiku.data.entity.dao.AccountMapper;
import com.hatsunemiku.data.eunm.AccountStatus;
import com.hatsunemiku.data.model.Account;
import com.hatsunemiku.data.service.IdaAccountService;
import com.hatsunemiku.data.util.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class IdaAccountServiceImpl implements IdaAccountService {

    static Log log = LogFactory.getLog(IdaAccountServiceImpl.class);

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountMapperCustomer accountMapperCustomer;

    @Autowired
    private HttpSession httpSession;

    /**
     * 根据登录用户id查询用户信息
     * @param id
     * @return
     */
    @Override
    public Account selectById(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    /**
     * 用户登录查询
     * @param account
     *          email:账户
     *          password:密码
     * @return
     *      result:错误代码编号
     *      message:错误提示
     *      url：返回跳转地址
     */
    @Override
    public String selectAccount(Account account) {
        Account selectByAccount = null;
        String password = account.getPassword();
        String email = account.getEmail();
        boolean type = false;
        try {
            selectByAccount = accountMapperCustomer.selectByAccount(account);
            if (selectByAccount == null) {
                return JsonUtils.createSimpleJson(
                        AccountStatus.AccountNotExist.getValue(),AccountStatus.AccountNotExist.getMessage(),null);
            }
            if (password == null || email == null || !selectByAccount.getEmail().equals(email)
                    || !selectByAccount.getPassword().equals(password)) {
                return JsonUtils.createSimpleJson(
                        AccountStatus.incorrectPassword.getValue(),AccountStatus.incorrectPassword.getMessage(),null);
            }
            httpSession.setAttribute("userId",selectByAccount.getId());
            httpSession.setAttribute("account",selectByAccount.getAccount());
            return JsonUtils.createSimpleJson(AccountStatus.ok.getValue(),null,"index");
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }
       return JsonUtils.createSimpleJson(AccountStatus.error.getValue(),AccountStatus.error.getMessage(),null);
    }
    /**
     * 新增用户插入数据
     *      根据注册用户邮箱查询该账户是否存在
     * @param account
     * @return
     */
    @Override
    public String insertNewAccount(Account account) {
        try {
            Account selectByAccount = accountMapperCustomer.selectByAccount(account);
            if (selectByAccount != null) {
                return JsonUtils.createSimpleJson(
                        AccountStatus.accountExist.getValue(),AccountStatus.accountExist.getMessage(),null);
            }
            Integer insertNewAccount = accountMapperCustomer.insertNewAccount(account);
            if (insertNewAccount != null && insertNewAccount.equals(1)) {
                httpSession.setAttribute("userId",account.getId());
                return JsonUtils.createSimpleJson(AccountStatus.ok.getValue(),null,"index");
            }
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }
        return JsonUtils.createSimpleJson(AccountStatus.error.getValue(),AccountStatus.error.getMessage(),null);
    }
}
