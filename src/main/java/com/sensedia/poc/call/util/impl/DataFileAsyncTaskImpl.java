package com.sensedia.poc.call.util.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.sensedia.poc.call.util.FileAsyncTask;

public class DataFileAsyncTaskImpl<T extends Serializable> extends FileAsyncTask<T> {

	public DataFileAsyncTaskImpl(File file) {
		super(file);
	}

	@SuppressWarnings(value = "unchecked")
	protected void read() {
		if (file.exists() && file.isFile()) {
			try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
				synchronized (data) {
					data = (ArrayList<T>) inputStream.readObject();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void writeAsync() {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
			do {
				synchronized (data) {
					outputStream.writeObject(data);
					outputStream.flush();
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
