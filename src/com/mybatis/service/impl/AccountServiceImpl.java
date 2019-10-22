package com.mybatis.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.mybatis.po.Account;
import com.mybatis.po.Log;
import com.mybatis.service.AccountService;

public class AccountServiceImpl implements AccountService {

	@Override
	public int transfer(Account accIn, Account accOut) throws IOException {
		InputStream is=Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		SqlSession session=factory.openSession();
		
		//先判断账号和密码是否配备
		Account accOutSelect=session.selectOne("com.mybatis.mapper.AccountMapper.selByAccnoPwd",accOut);
		if (accOutSelect!=null) {
			//判断余额是否大于或等于取款金额
			if(accOutSelect.getBalance()>=accOut.getBalance()){
				Account accInSelect=session.selectOne("com.mybatis.mapper.AccountMapper.selByAccnoName",accIn);
				//判断账号和姓名是否正确
				if(accInSelect!=null){
					//设置收款账号的收入金额
					accIn.setBalance(accOut.getBalance());
					//设置转账账号的转出金额
					accOut.setBalance(-accOut.getBalance());
					int index=session.update("com.mybatis.mapper.AccountMapper.updBalanceByAccno",accOut);
					index+=session.update("com.mybatis.mapper.AccountMapper.updBalanceByAccno",accIn);
					if(index==2){
						//数据库日志表记录
						Log log=new Log();
						log.setAccIn(accIn.getAccNo());
						log.setAccOut(accOut.getAccNo());
						log.setMoney(accIn.getBalance());
						session.insert("com.mybatis.mapper.LogMapper.insLog",log);
						//日志文件记录
						Logger logger=Logger.getLogger(AccountServiceImpl.class);
						logger.info(log.getAccOut()+"给"+log.getAccIn()+"在"+new Date().toLocaleString()+"转了"+log.getMoney());
						
						session.commit();
						session.close();
						return SUCCESS;
					}else {
						session.rollback();
						session.close();
						return ERROR;
					}
				}else {
					//账号姓名不匹配
					session.close();
					return ACCOUNT_NAME_MATCH;
				}
			}else {
				//余额不足
				session.close();
				return ACCOUNT_BALANCE_NOT_ENOUGH;
			}
		}else {
			//账号密码不匹配
			session.close();
			return ACCOUNT_PASSWORD_NOT_MATCH;
		}
	}
	
}
