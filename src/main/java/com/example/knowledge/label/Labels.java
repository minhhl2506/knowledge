package com.example.knowledge.label;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Labels {

	public interface Language {
		
		public static final String VI = "vi";
		
		public static final String EN = "en";
	}
	
	public interface Country {
		
		public static final String US = "US";
		
		public static final String VN = "VI";
	}
	
    public static final Locale US = Locale.US;
	
	public static final Locale VN = new Locale(Language.VI, Country.VN);
	
	public static Locale getDefaultLocale() {
		return US;
	}
	
	private static MessageSource messageSource;
	
	@Autowired
    public Labels(MessageSource messageSource) {
        Labels.messageSource = messageSource;
    }
	
	public static String getLabels(String key) {
		return getLabels(key, null, US);
	}
	
	public static String getLabels(String key, Object[] objs, Locale locale) {
		locale = getDefaultLocale();
		return messageSource.getMessage(key, null, locale);
	}
	
}
