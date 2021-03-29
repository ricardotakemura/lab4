package com.sensedia.poc.call.usecase;

import java.net.MalformedURLException;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sensedia.poc.call.bean.CallBean;
import com.sensedia.poc.call.exception.CallAlreadyExistsException;
import com.sensedia.poc.call.exception.CallNotFoundException;
import com.sensedia.poc.call.service.CallService;

@Component
public class CallUseCase {

	@Autowired
	private CallService callService;

	public List<CallBean> getCalls() {
		return callService.getAll();
	}

	public CallBean getCall(Long id) throws CallNotFoundException {
		if (callService.exists(id)) {
			return callService.get(id);
		} else {
			throw new CallNotFoundException();
		}
	}

	public void createCall(CallBean call) throws CallAlreadyExistsException, MalformedURLException, InvalidParameterException {
		if (callService.exists(call.getId())) {
			throw new CallAlreadyExistsException();
		} else {
			if (StringUtils.hasText(call.getUrl())) {
				URI.create(call.getUrl()).toURL();
				if (call.getStatusCode() != null) {
					callService.create(call);
					return;
				}				
			}
			throw new InvalidParameterException();
		}
	}

	public void deleteCall(Long id) throws CallNotFoundException {
		if (callService.exists(id)) {
			callService.delete(id);
		} else {
			throw new CallNotFoundException();
		}
	}
}
