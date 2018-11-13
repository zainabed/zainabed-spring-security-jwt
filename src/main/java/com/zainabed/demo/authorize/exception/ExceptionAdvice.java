package com.zainabed.demo.authorize.exception;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<ExceptionMessage> handle(Exception e) {
		ExceptionMessage message = new ExceptionMessage(e.getLocalizedMessage());
		return new ResponseEntity<ExceptionMessage>(message, getHttpStatus(e));
	}

	private static HttpStatus getHttpStatus(Exception e) {
		ResponseStatus responseStatus = findAnnotation(e.getClass(), ResponseStatus.class);
		if (responseStatus != null) {
			return responseStatus.value();
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	private class ExceptionMessage {
		private String message;

		public String getMessage() {
			return message;
		}

		public ExceptionMessage(String message) {
			this.message = message;
		}
	}
}
