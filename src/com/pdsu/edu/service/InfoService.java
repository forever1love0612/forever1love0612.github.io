package com.pdsu.edu.service;

import java.util.List;

import com.pdsu.edu.domain.Info;

/**
 * ��˵����
 * 
 * @author ����: LiuJunGuang
 * @version ����ʱ�䣺2012-3-25 ����02:31:11
 */
public interface InfoService {

	// �����Ϣ
	public abstract void addInfo(Info info);

	public abstract List<Info> findAllInfo();


}