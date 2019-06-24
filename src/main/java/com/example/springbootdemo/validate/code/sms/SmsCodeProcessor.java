/**
 * 
 */
package com.example.springbootdemo.validate.code.sms;

import com.example.springbootdemo.config.properties.SecurityConstants;
import com.example.springbootdemo.validate.code.AbstractValidateCodeProcessor;
import com.example.springbootdemo.validate.code.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * 短信验证码处理器
 * 
 * @author zhailiang
 *
 */
@Component
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
		// 获取手机号码
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		// 向指定手机号码发送验证码
		smsCodeSender.send(mobile, validateCode.getCode());
	}

}
