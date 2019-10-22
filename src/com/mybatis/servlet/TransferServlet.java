package com.mybatis.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mybatis.po.Account;
import com.mybatis.service.AccountService;
import com.mybatis.service.impl.AccountServiceImpl;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
	private AccountService accService=new AccountServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Account accOut=new Account();
		accOut.setAccNo(req.getParameter("accOutNo"));
		accOut.setPassword(Integer.parseInt(req.getParameter("accOutPwd")));
		accOut.setBalance(Double.parseDouble(req.getParameter("accOutBalance")));
		
		Account accIn=new Account();
		accIn.setAccNo(req.getParameter("accInAccNo"));
		accIn.setName(req.getParameter("accInName"));
		int index=accService.transfer(accIn, accOut);
		if(index==AccountService.SUCCESS){
			resp.sendRedirect("/mybatisDemo/show");
		}else {
			HttpSession session=req.getSession();
			session.setAttribute("code", index);
			resp.sendRedirect("/mybatisDemo/error.jsp");
		}
	}
	
}
