package com.pdsu.edu.dao;

import java.util.List;

import com.pdsu.edu.domain.Info;
import com.pdsu.edu.domain.State;

/**
 * ��˵����
 * 
 * @author ����: LiuJunGuang
 * @version ����ʱ�䣺2012-3-25 ����03:03:19
 */
public interface StateDao {

	public abstract List<State> findAll();

}