package com.mybatis.service;

import java.io.IOException;

import com.mybatis.po.PageInfo;

public interface LogService {
	/**
	 * 分页显示
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	PageInfo showPage(int pageSize,int pageNumber) throws IOException;
}
