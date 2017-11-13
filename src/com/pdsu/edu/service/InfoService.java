package com.pdsu.edu.service;

import java.util.List;

import com.pdsu.edu.domain.Info;

/**
 * 类说明：
 * 
 * @author 作者: LiuJunGuang
 * @version 创建时间：2012-3-25 下午02:31:11
 */
public interface InfoService {

	// 添加信息
	public abstract void addInfo(Info info);

	public abstract List<Info> findAllInfo();


}