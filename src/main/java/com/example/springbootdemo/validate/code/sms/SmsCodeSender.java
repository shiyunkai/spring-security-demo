/**
 * 
 */
package com.example.springbootdemo.validate.code.sms;

/**
 * @author zhailiang
 *
 */
public interface SmsCodeSender {
	
	void send(String mobile, String code);

}
