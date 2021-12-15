package com.boots.service.exception;

public class ArticleException extends ServiceException{
	private static final long serialVersionUID = 1L;

	public ArticleException() {
		super();
	}

	public ArticleException(String message) {
		super(message);
	}

	public ArticleException(Exception e) {
		super(e);
	}

	public ArticleException(String message, Exception e) {
		super(message, e);
	}

}
