package com.pdsu.edu.dao;

import java.util.List;

import com.pdsu.edu.domain.Info;

/**
 * ��˵����
 * 
 * @author ����: LiuJunGuang
 * @version ����ʱ�䣺2012-3-25 ����03:03:19
 */
public interface InfoDao {

	public abstract void insertInfo(Info info);

	public abstract List<Info> findAll();

}