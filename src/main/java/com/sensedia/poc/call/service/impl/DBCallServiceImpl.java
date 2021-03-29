package com.sensedia.poc.call.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sensedia.poc.call.bean.CallBean;
import com.sensedia.poc.call.model.Call;
import com.sensedia.poc.call.repository.CallRepository;
import com.sensedia.poc.call.service.CallService;

public class DBCallServiceImpl implements CallService {

	@Autowired
	private CallRepository messageRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CallBean> getAll() {
		return messageRepository.findAll().stream().map(this::convertCallToCallBean).collect(Collectors.toList());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CallBean get(Long id) {
		return convertCallToCallBean(messageRepository.findById(id).orElseThrow());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(CallBean callBean) {
		Call call = convertCallBeanToCall(callBean);
		callBean.setId(messageRepository.saveAndFlush(call).getId());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		messageRepository.deleteById(id);
	}

	@Override
	public boolean exists(Long id) {
		if (id == null) {
			return false;
		} else {
			return messageRepository.existsById(id);
		}
	}

	private Call convertCallBeanToCall(CallBean callBean) {
		return new Call(callBean.getId(), callBean.getUrl(), callBean.getStatusCode());
	}

	private CallBean convertCallToCallBean(Call call) {
		return new CallBean(call.getId(), call.getUrl(), call.getStatusCode());
	}

}
