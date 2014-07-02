package com.rotek.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.dao.impl.AlgorithmsDao;

/**
* @ClassName:AlgorithmsService
* @Description:
* @Author liusw
* @date 2014年6月29日 下午3:16:24
* @Version:1.1.0
*/
@Service
public class AlgorithmsService {
	
	@Autowired
	private AlgorithmsDao algorithmsDao;

}
