package com.pdsu.edu.dao;

import java.util.List;

import com.pdsu.edu.domain.Info;
import com.pdsu.edu.domain.State;

/**
 * 类说明：
 * 
 * @author 作者: LiuJunGuang
 * @version 创建时间：2012-3-25 下午03:03:19
 */
public interface StateDao {

	public abstract List<State> findAll();

}