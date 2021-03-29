package com.sensedia.poc.call.service;

import java.util.List;

import com.sensedia.poc.call.bean.CallBean;

public interface CallService {

	List<CallBean> getAll();

	CallBean get(Long id);

	void create(CallBean call);

	void delete(Long id);

	boolean exists(Long id);

}
