package com.pdsu.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pdsu.edu.dao.InfoDao;
import com.pdsu.edu.dao.StateDao;
import com.pdsu.edu.domain.Info;
import com.pdsu.edu.domain.State;
import com.pdsu.edu.service.InfoService;
import com.pdsu.edu.service.StateService;

/**
 * 类说明：用户Service实现
 * 
 * @author 作者: LiuJunGuang
 * @version 创建时间：2012-3-25 下午02:26:48
 */
@Service
@Transactional
public class StateServiceImpl implements StateService {

	@Autowired
	private StateDao stateDao;

	public List<State> findAllState() {
		return stateDao.findAll();
	}

}
