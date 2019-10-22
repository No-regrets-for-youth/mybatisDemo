package com.mybatis.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.service.LogService;
import com.mybatis.service.impl.LogServiceImpl;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {
	private LogService LogService=new LogServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//判断是否是第一次打开，如果是，则显示第一页
		int pageSize=2;
		String pageSizeStr=req.getParameter("pageSize");
		if(pageSizeStr!=null&&!pageSizeStr.equals("")){
			pageSize=Integer.parseInt(pageSizeStr);
		}
		
		int pageNumber=1;
		String pageNumberStr=req.getParameter("pageNumber");
		if(pageNumberStr!=null&&!pageNumberStr.equals("")){
			pageNumber=Integer.parseInt(pageNumberStr);
		}
		
		req.setAttribute("pageInfo", LogService.showPage(pageSize, pageNumber));
		req.getRequestDispatcher("log.jsp").forward(req, resp);
	}
	
}
