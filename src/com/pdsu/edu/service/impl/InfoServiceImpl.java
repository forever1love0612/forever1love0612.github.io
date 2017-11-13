package com.pdsu.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pdsu.edu.dao.InfoDao;
import com.pdsu.edu.domain.Info;
import com.pdsu.edu.service.InfoService;

/**
 * ��˵�����û�Serviceʵ��
 * 
 * @author ����: LiuJunGuang
 * @version ����ʱ�䣺2012-3-25 ����02:26:48
 */
@Service
@Transactional
public class InfoServiceImpl implements InfoService {

	@Autowired
	private InfoDao infoDao;


	public void addInfo(Info info) {
		infoDao.insertInfo(info);
	}

	public List<Info> findAllInfo() {
		return infoDao.findAll();
	}

}
