package com.sensedia.poc.call.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sensedia.poc.call.bean.CallBean;
import com.sensedia.poc.call.service.CallService;
import com.sensedia.poc.call.util.FileAsyncTask;

public class FileCallServiceImpl implements CallService {

	private FileAsyncTask<CallBean> fileAsync;

	@Autowired
	public FileCallServiceImpl(FileAsyncTask<CallBean> fileAsync) {
		this.fileAsync = fileAsync;
		this.fileAsync.start();
	}

	@Override
	public List<CallBean> getAll() {
		return fileAsync.getData();
	}

	@Override
	public CallBean get(Long id) {
		return fileAsync.getData().stream().filter(it -> it.getId() == id).findFirst().orElseThrow();
	}

	@Override
	public void create(CallBean call) {
		synchronized (call) {
			if (call.getId() == null) {
				if (fileAsync.getData().isEmpty()) {
					call.setId(1l);
				} else {
					ArrayList<CallBean> calls = fileAsync.getData();
					call.setId(calls.get(calls.size() - 1).getId() + 1);
				}
			}
			fileAsync.getData().add(call);
		}
	}

	@Override
	public void delete(Long id) {
		CallBean call = fileAsync.getData().stream().filter(it -> it.getId() == id).findFirst().orElseThrow();
		synchronized (call) {
			fileAsync.getData().remove(call);
		}
	}

	@Override
	public boolean exists(Long id) {
		return fileAsync.getData().stream().anyMatch(it -> it.getId() == id);
	}

}
