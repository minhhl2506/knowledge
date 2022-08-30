package com.example.knowledge.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.example.knowledge.label.Labels;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "spring.messages")
public class MessageConfiguration {

	private String encoding;

	private String[] basename;

	private int cacheDuration;

	private boolean useCodeAsDefaultMessage;

	@Bean
	public LocaleResolver getLocaleResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();

        localeResolver.setDefaultLocale(Labels.VN);
        
        return localeResolver;
		
	}

	@Bean
	public MessageSource getMessageResource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();

		messageResource.setBasenames(this.basename);
		messageResource.setDefaultEncoding(this.encoding);
		messageResource.setCacheSeconds(this.cacheDuration);
		messageResource.setUseCodeAsDefaultMessage(this.useCodeAsDefaultMessage);
		return messageResource;
	}
}
