package com.jv6d1;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.jv6d1.interceptor.GlobalInterceptor;

@Configuration
public class InterceptorCpnfig implements WebMvcConfigurer {
	@Autowired
	GlobalInterceptor globalInterceptor;
	
	@Bean(name="localeResolver")
	public LocaleResolver getLocaleResolver()  {
        CookieLocaleResolver resolver= new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));;
        // 60 minutes 
        resolver.setCookiePath("/");
        resolver.setCookieMaxAge(60*60); 
        return resolver;
    } 
	
	@Bean(name = "messageSource")
    public MessageSource getMessageResource()  {
        ReloadableResourceBundleMessageSource messageResource= new ReloadableResourceBundleMessageSource();
         
        // Đọc vào file i18n/messages_xxx.properties
        // Ví dụ: i18n/messages_en.properties
        messageResource.setBasename("classpath:i18n/messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");
        
        registry.addInterceptor(localeInterceptor).addPathPatterns("/**");
        
		registry.addInterceptor(globalInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/rest/**","/admin/**","/assets/**");
	}
	
}
