package com.mybatis.service;

import java.io.IOException;

import com.mybatis.po.Account;

public interface AccountService {
	/**
	 * 账号和密码不匹配码
	 */
	int ACCOUNT_PASSWORD_NOT_MATCH=1;
	
	/**
	 * 余额不足
	 */
	int ACCOUNT_BALANCE_NOT_ENOUGH=2;
	
	/**
	 * 账户姓名不匹配
	 */
	int ACCOUNT_NAME_MATCH=3;
	
	/**
	 * 异常，转账失败
	 */
	int ERROR=4;
	
	/**
	 * 转账成功
	 */
	int SUCCESS=5;
	
	/**
	 * 转账
	 * @param accIn 收账账号
	 * @param accOut 转账账号
	 * @return
	 */
	int transfer(Account accIn,Account accOut) throws IOException;
}
