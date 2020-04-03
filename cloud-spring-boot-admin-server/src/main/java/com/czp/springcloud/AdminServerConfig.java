package com.czp.springcloud;

import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.notify.MailNotifier;
import de.codecentric.boot.admin.server.notify.Notifier;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.nio.charset.StandardCharsets;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-18 11:12:03
 * @description :
 */
//@Configuration
public class AdminServerConfig implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public TemplateEngine mailNotifierTemplateEngine() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(this.applicationContext);
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());

		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(resolver);
		return templateEngine;
	}


	@Bean
	@ConfigurationProperties("spring.boot.admin.notify.mail")
	public Notifier notifier(JavaMailSender mailSender, InstanceRepository repository) {
		MailNotifier mailNotifier = new MailNotifier(mailSender, repository, mailNotifierTemplateEngine());
		mailNotifier.setIgnoreChanges(new String[]{});
		return mailNotifier;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
