package com.pdsu.edu.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pdsu.edu.dao.InfoDao;
import com.pdsu.edu.dao.StateDao;
import com.pdsu.edu.domain.Info;
import com.pdsu.edu.domain.State;

/**
 * ��˵�����û�daoʵ��
 * 
 * @author ����: LiuJunGuang
 * @version ����ʱ�䣺2012-3-25 ����02:24:15
 */
@Repository
public class StateDaoImpl implements StateDao {
	private final String SELECT_ALL_STATE = "selectAllState";
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<State> findAll() {
		return sqlSessionTemplate.selectList(SELECT_ALL_STATE);
	}

}
