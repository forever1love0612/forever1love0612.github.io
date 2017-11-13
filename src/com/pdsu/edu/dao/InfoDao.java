package com.pdsu.edu.dao;

import java.util.List;

import com.pdsu.edu.domain.Info;

/**
 * 类说明：
 * 
 * @author 作者: LiuJunGuang
 * @version 创建时间：2012-3-25 下午03:03:19
 */
public interface InfoDao {

	public abstract void insertInfo(Info info);

	public abstract List<Info> findAll();

}