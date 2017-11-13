package com.pdsu.edu.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pdsu.edu.dao.InfoDao;
import com.pdsu.edu.domain.Info;

/**
 * ��˵�����û�daoʵ��
 * 
 * @author ����: LiuJunGuang
 * @version ����ʱ�䣺2012-3-25 ����02:24:15
 */
@Repository
public class InfoDaoImpl implements InfoDao {
	private final String INSERT_INFO = "insertInfo";
	private final String SELECT_ALL_INFO = "selectAllInfo";
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;


	public void insertInfo(Info info) {
		sqlSessionTemplate.insert(INSERT_INFO, info);
	}

	public List<Info> findAll() {
		return sqlSessionTemplate.selectList(SELECT_ALL_INFO);
	}

}
