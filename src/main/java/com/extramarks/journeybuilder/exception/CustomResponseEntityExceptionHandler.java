package com.extramarks.journeybuilder.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.jboss.logging.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.extramarks.journeybuilder.response.ResponseModel;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler 
{
	Logger loggerObj = Logger.getLogger(CustomResponseEntityExceptionHandler.class);

	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		loggerObj.info("handleAllExceptions method called.");
		loggerObj.error(makeErrorMsg(ex.toString(),request.toString()));
		ResponseModel error = new ResponseModel();
		error.setStatus(0);
		error.setMessage("Some Unknown Exception Occur!");
		return new ResponseEntity(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<Object> handleCustomException(CustomException ex, WebRequest request) {
		loggerObj.error(makeErrorMsg(ex.toString(),request.toString()));
		ResponseModel error = new ResponseModel();
		error.setStatus(0);
		error.setMessage(ex.getErrorMessage());
		return new ResponseEntity(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		loggerObj.error(makeErrorMsg(ex.toString(),request.toString()));
		
		ResponseModel error = new ResponseModel();
		error.setStatus(0);
		error.setMessage("Either null or duplicate entry found");
		return new ResponseEntity(error, HttpStatus.OK);
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public final ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
		loggerObj.error(makeErrorMsg(ex.toString(),request.toString()));
		ResponseModel error = new ResponseModel();
		error.setStatus(0);
		error.setMessage("SQLIntegrityConstraintViolationException Occur :: message = "+ex.getLocalizedMessage());
		return new ResponseEntity(error, HttpStatus.OK);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
		
		loggerObj.error(makeErrorMsg(ex.toString(),request.toString()));
		
		StringBuilder msg = new StringBuilder();
		
		for(ConstraintViolation<?> cv : ex.getConstraintViolations()) {
			msg.append(cv.getMessage());
		}

		ResponseModel error = new ResponseModel();
		error.setStatus(0);
		error.setMessage(msg.toString());

		return new ResponseEntity(error, HttpStatus.OK);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public final ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request){
		
		loggerObj.error(makeErrorMsg(ex.toString(),request.toString()));
		ResponseModel error = new ResponseModel();
		error.setMessage(ex.getMessage());
		error.setStatus(0);

		return new ResponseEntity(error, HttpStatus.OK);
	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
		
		loggerObj.error(makeErrorMsg(ex.toString(),request.toString()));
		ResponseModel error = new ResponseModel();
		error.setStatus(0);
		error.setMessage("Record Not Found!");
		return new ResponseEntity(error, HttpStatus.OK);
	}

	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public final ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		loggerObj.error(makeErrorMsg(ex.toString(),request.toString()));
		ResponseModel error = new ResponseModel();
		error.setStatus(0);
		error.setMessage(ex.getMessage());
		return new ResponseEntity(error, HttpStatus.OK);
	}
	
	private String makeErrorMsg(String ex, String request) {
		return "Exception = "+ex+ "\n request = "+request;
	}
}
