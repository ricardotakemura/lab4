package com.sensedia.poc.call.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensedia.poc.call.bean.CallBean;
import com.sensedia.poc.call.controller.CallController;

@SuppressWarnings("unchecked")
public class CsvCallController extends CallController {

	@Override
	@ResponseBody
	public String getAll() {
		try {
			return convertCallBeanToCsv(callUseCase.getCalls().toArray(CallBean[]::new));
		} catch (Exception e) {
			return "\"ERROR\";\n\"" + e.getMessage() + "\";\n";
		}
	}

	@Override
	@ResponseBody
	public String get(@PathVariable("id") Long id) {
		try {
			return convertCallBeanToCsv(callUseCase.getCall(id));
		} catch (Exception e) {
			return "\"ERROR\";\n\"" + e.getMessage() + "\";\n";
		}
	}

	@Override
	@ResponseBody
	public String delete(@PathVariable("id") Long id) {
		try {
			callUseCase.deleteCall(id);
			return "\"INFO\";\n\"Dado excluído com sucesso.\";\n";
		} catch (Exception e) {
			return "\"ERROR\";\n\"" + e.getMessage() + "\";\n";
		}
	}

	@Override
	@ResponseBody
	public String create(@RequestBody String body) {
		try {
			CallBean callBean = convertToCallBean(body).get(0);
			callUseCase.createCall(callBean);
			return convertCallBeanToCsv(callBean);
		} catch (Exception e) {
			return "\"ERROR\";\n\"" + e.getMessage() + "\";\n";
		}
	}

	private String convertCallBeanToCsv(CallBean... callBeans) {
		String csv = "\"ID\";\"URL\";\"STATUS CODE\";\n";
		for (CallBean callBean : callBeans) {
			csv += "\"" + callBean.getId() + "\";\"" + callBean.getUrl() + "\";\"" + callBean.getStatusCode() + "\";\n";
		}
		return csv;
	}

	private List<CallBean> convertToCallBean(String cvs) {
		boolean first = true;
		List<CallBean> callBeans = new ArrayList<>();
		for (String line : cvs.split("\n")) {
			String[] columns = line.split(";");
			if (first) {
				if (!"\"ID\"".equals(columns[0]) || !"\"URL\"".equals(columns[1])
						|| !"\"STATUS CODE\"".equals(columns[2])) {
					return null;
				}
				first = false;
			} else {
				String idAsString = columns[0].replaceAll("\"", "");
				Long id = idAsString.isBlank() ? null : Long.parseLong(idAsString);
				String statusCodeAsString = columns[2].replaceAll("\"", "");
				Integer statusCode = statusCodeAsString.isBlank() ? null : Integer.parseInt(statusCodeAsString);
				callBeans.add(new CallBean(id, columns[1].replaceAll("\"", ""), statusCode));
			}
		}
		return callBeans;
	}
}
