package com.sensedia.poc.call.util.impl;

import java.io.File;
import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensedia.poc.call.util.FileAsyncTask;

public class JsonFileAsyncTaskImpl<T extends Serializable> extends FileAsyncTask<T> {

	private ObjectMapper mapper;
	private Class<T> clazz;

	public JsonFileAsyncTaskImpl(File file, Class<T> clazz) {
		super(file);
		this.clazz = clazz;
		this.mapper = new ObjectMapper();
	}

	protected void read() {
		if (file.exists() && file.isFile()) {
			try {
				synchronized (data) {
					data = mapper.readerForListOf(clazz).readValue(file);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void writeAsync() {
		try {
			do {
				synchronized (data) {
					mapper.writeValue(file, data);
				}
				Thread.sleep(TIMEOUT);
			} while (!stopped);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() {
		stopped = true;
	}

}
