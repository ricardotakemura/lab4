package com.sensedia.poc.call.controller.impl;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensedia.poc.call.bean.CallBean;
import com.sensedia.poc.call.bean.ErrorResponseBean;
import com.sensedia.poc.call.bean.ResultResponseBean;
import com.sensedia.poc.call.bean.Status;
import com.sensedia.poc.call.controller.CallController;
import com.sensedia.poc.call.exception.CallAlreadyExistsException;
import com.sensedia.poc.call.exception.CallNotFoundException;

@SuppressWarnings("unchecked")
public class RestCallController extends CallController {

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public ResponseEntity<ResultResponseBean> getAll() {
		try {
			final ResultResponseBean result = new ResultResponseBean(callUseCase.getCalls(), Status.success);
			return new ResponseEntity<ResultResponseBean>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResultResponseBean>(
					new ResultResponseBean(new ErrorResponseBean("Ocorreu um erro na solicitação."), Status.fail),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResultResponseBean> get(@PathVariable("id") Long id) {
		try {
			final ResultResponseBean result = new ResultResponseBean(callUseCase.getCall(id), Status.success);
			return new ResponseEntity<ResultResponseBean>(result, HttpStatus.OK);
		} catch (CallNotFoundException e) {
			return new ResponseEntity<ResultResponseBean>(
					new ResultResponseBean(new ErrorResponseBean("Chamada não encontrada."), Status.fail),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResultResponseBean>(
					new ResultResponseBean(new ErrorResponseBean("Ocorreu um erro na solicitação."), Status.fail),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			callUseCase.deleteCall(id);
			return ResponseEntity.noContent().build();
		} catch (CallNotFoundException e) {
			return new ResponseEntity<ResultResponseBean>(
					new ResultResponseBean(new ErrorResponseBean("Chamada não encontrada."), Status.fail),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResultResponseBean>(
					new ResultResponseBean(new ErrorResponseBean("Ocorreu um erro na solicitação."), Status.fail),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> create(@RequestBody String body) {
		try {
			CallBean callBean = mapper.readValue(body, CallBean.class);
			callUseCase.createCall(callBean);
			return ResponseEntity.created(URI.create("/calls/" + callBean.getId())).build();
		} catch (CallAlreadyExistsException e) {
			return new ResponseEntity<ResultResponseBean>(
					new ResultResponseBean(new ErrorResponseBean("Chamada já existe."), Status.fail),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResultResponseBean>(
					new ResultResponseBean(new ErrorResponseBean("Ocorreu um erro na solicitação."), Status.fail),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
