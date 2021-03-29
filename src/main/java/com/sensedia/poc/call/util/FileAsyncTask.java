package com.sensedia.poc.call.util;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class FileAsyncTask<T extends Serializable> {

	protected static final int TIMEOUT = 1000;
	protected File file;
	protected ArrayList<T> data;
	protected boolean stopped;

	public FileAsyncTask(File file) {
		this.data = new ArrayList<>();
		this.stopped = false;
		this.file = file;
	}

	public synchronized void start() {
		stopped = false;
		read();
		startThread();
	}

	public ArrayList<T> getData() {
		synchronized (data) {
			return data;
		}
	}

	public void setData(ArrayList<T> data) {
		synchronized (this.data) {
			this.data = data;
		}
	}

	public synchronized void stop() {
		stopped = true;
	}

	protected abstract void read();

	protected abstract void writeAsync();

	private void startThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				writeAsync();
			}
		}).start();
	}

	@Override
	protected void finalize() {
		stopped = true;
	}

}
