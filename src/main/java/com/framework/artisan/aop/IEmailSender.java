package com.framework.artisan.aop;

public interface IEmailSender {
	void sendEmail(String email, String message);
	String getOutgoingMailServer();
}