package com.hatsunemiku.data.support;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;

@PropertySource(value = { "classpath:dns.properties","classpath:email.properties" })
public final class Resources {
	public static final ResourceBundle DNSHOSTS = ResourceBundle.getBundle("dns");
	
	public static final ResourceBundle EMAIL = ResourceBundle.getBundle("email");
	/** 国际化信息 */
	private static final Map<String, ResourceBundle> MESSAGES = new HashMap<String, ResourceBundle>();

	/** 国际化信息 */
	public static String getMessage(String key, Object... params) {
		Locale locale = LocaleContextHolder.getLocale();
		ResourceBundle message = MESSAGES.get(locale.getLanguage());
		if (message == null) {
			synchronized (MESSAGES) {
				message = MESSAGES.get(locale.getLanguage());
				if (message == null) {
					message = ResourceBundle.getBundle("i18n/messages", locale);
					MESSAGES.put(locale.getLanguage(), message);
				}
			}
		}
		if (params != null) {
			return String.format(message.getString(key), params);
		}
		return message.getString(key);
	}

	/** 清除国际化信息 */
	public static void flushMessage() {
		MESSAGES.clear();
	}
}
