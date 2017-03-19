package com.apress.spring.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

@RestController
public class UtilController {
	private static final Logger log = LoggerFactory.getLogger(UtilController.class);

	@RequestMapping("/changeLang/{langCode}")
	public String changeLang(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String langCode){
		log.info("改变语言为:{}", langCode);
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale locale;
		if ("zh".equals(langCode)) {
			locale = new Locale("zh", "CN");
		} else {
			locale = new Locale("en", "US");
		}
		localeResolver.setLocale(request, response, locale);
		return "Change Language Success:" + locale;
	}
}
