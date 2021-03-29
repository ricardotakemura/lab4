package com.sensedia.poc.call.config;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sensedia.poc.call.bean.CallBean;
import com.sensedia.poc.call.controller.CallController;
import com.sensedia.poc.call.controller.impl.RestCallController;
import com.sensedia.poc.call.service.CallService;
import com.sensedia.poc.call.service.impl.DBCallServiceImpl;
import com.sensedia.poc.call.util.FileAsyncTask;
import com.sensedia.poc.call.util.impl.DataFileAsyncTaskImpl;

@Configuration
public class AppConfig {

	@Bean
	public CallController callController() {
		// return new CsvCallController();
		return new RestCallController();
	}

	@Bean
	public CallService callService() {
		// return new FileCallServiceImpl(fileAsyncTask());
		return new DBCallServiceImpl();
	}

	@Bean
	public FileAsyncTask<CallBean> fileAsyncTask() {
		// return new JsonFileAsyncTaskImpl<CallBean>(new File("calls.json"),
		// CallBean.class);
		return new DataFileAsyncTaskImpl<CallBean>(new File("calls.dat"));
	}
}
