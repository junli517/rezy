package com.rezy.dialog.service.email;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @ClassName: EmailServiceImpl
 * @Description: (描述)
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月27
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.from}")
	private String from;

	/**
	 * @Description: 发送文本邮件
	 * @param to：发送对象(可以是多个人数组)
	 * @param subject：邮件主题
	 * @param content：内容
	 * @param cc：抄送人
	 */
	@Override
	public void sendSimpleMail(String to, String subject, String content, String... cc) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		if (StringUtils.isNoneEmpty(cc)) {
			message.setCc(cc);
		}
		message.setSubject(subject);
		message.setText(content);
		mailSender.send(message);
	}

	/**
	 * @Description: 发送HTML邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @throws MessagingException
	 */
	@Override
	public void sendHtmlMail(String to, String subject, String content, String... cc) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		if (StringUtils.isNoneEmpty(cc)) {
			helper.setCc(cc);
		}
		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(message);
	}

	/**
	 * @Description: 发送带附件的邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 * @throws MessagingException
	 */
	@Override
	public void sendAttachmentsMail(String to, String subject, String content, String filePath, String... cc)
			throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		if (StringUtils.isNoneEmpty(cc)) {
			helper.setCc(cc);
		}
		helper.setSubject(subject);
		helper.setText(content, true);
		FileSystemResource file = new FileSystemResource(new File(filePath));
		String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
		helper.addAttachment(fileName, file);
		mailSender.send(message);
	}

	/**
	 * @Description: 发送正文中有静态资源的邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param rscPath
	 * @param rscId
	 * @throws MessagingException
	 */
	@Override
	public void sendResourceMail(String to, String subject, String content, String rscPath, String rscId, String... cc)
			throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		if (StringUtils.isNoneEmpty(cc)) {
			helper.setCc(cc);
		}
		helper.setSubject(subject);
		helper.setText(content, true);
		FileSystemResource res = new FileSystemResource(new File(rscPath));
		helper.addInline(rscId, res);
		mailSender.send(message);
	}
}
