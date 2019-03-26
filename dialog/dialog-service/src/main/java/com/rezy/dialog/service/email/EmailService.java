package com.rezy.dialog.service.email;

import javax.mail.MessagingException;

/**
 * @ClassName: EmailService
 * @Description: (描述)
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月27
 */
public interface EmailService {

	/**
	 * @Description: 发送文本邮件
	 * @param to：发送对象(可以是多个人数组)
	 * @param subject：邮件主题
	 * @param content：内容
	 * @param cc：抄送人
	 */
	public void sendSimpleMail(String to, String subject, String content, String... cc);

	/**
	 * @Description: 发送HTML邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param cc
	 * @throws MessagingException
	 */
	public void sendHtmlMail(String to, String subject, String content, String... cc) throws MessagingException;

	/**
	 * @Description: 发送带附件的邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 * @throws MessagingException
	 */
	public void sendAttachmentsMail(String to, String subject, String content, String filePath, String... cc)
			throws MessagingException;

	/**
	 * @Description: 发送正文中有静态资源的邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param rscPath
	 * @param rscId
	 * @throws MessagingException
	 */
	public void sendResourceMail(String to, String subject, String content, String rscPath, String rscId, String... cc)
			throws MessagingException;
}
