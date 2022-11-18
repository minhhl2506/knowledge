package com.example.knowledge.label;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.knowledge.util.ApiConstants;
import com.example.knowledge.util.GetterUtils;

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
		return VN;
	}

	private static MessageSource messageSource;

	@Autowired
	public Labels(MessageSource messageSource) {
		Labels.messageSource = messageSource;
	}

	// get message with none value of object
	public static String getLabels(String key) {
		return getLabels(key, null, getLocale());
	}
	
	// get message with value of object
	public static String getLabels(String key, Object[] objs) {
		return getLabels(key, objs, getLocale());
	}
	
	public static Locale getLocale() {
		
		String language = getLanguage();
		
		switch (language) {
		
		case Language.VI:
			return getDefaultLocale();
		
		case Language.EN:
			return Locale.US;
		
		default:
			return getDefaultLocale();
		}
	}

	public static String getLabels(String key, Object[] objs, Locale locale) {
		String ms = null;

        try {
            if (locale == null) {
                locale = getDefaultLocale();
            }

            ms = messageSource.getMessage(key, objs, locale);
        } catch (NoSuchMessageException ex) {
            
        }

        return ms;
	}
	
	public static String getLanguage() {
		HttpServletRequest request = null;
		
		RequestAttributes requestAttr = RequestContextHolder.getRequestAttributes();

		if (requestAttr instanceof ServletRequestAttributes) {
			request = ((ServletRequestAttributes) requestAttr).getRequest();
		}
		
		if(request == null) {
			return Language.VI;
		}
		
		return GetterUtils.getString(request.getHeader(ApiConstants.HEADER.LOCALE), Language.VI);
	}

}
