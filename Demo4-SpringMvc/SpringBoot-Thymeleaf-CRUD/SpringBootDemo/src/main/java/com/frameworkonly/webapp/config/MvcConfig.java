package com.frameworkonly.webapp.config;

import java.util.Locale;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

// @Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	/*
	 * ThymeLeaf configuration
	 * =========================================================================
	 */

	@Bean
	public TemplateResolver getTemplateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		// resolver.setPrefix("/WEB-INF/views/");
		resolver.setPrefix("classpath:/templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setCacheable(false);

		return resolver;
	}

	@Bean
	public SpringTemplateEngine getTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(getTemplateResolver());
		// templateEngine.setDialect(new LayoutDialect());
		return templateEngine;
	}

	@Bean
	public ViewResolver getThymeleafViewResolver() {
		ThymeleafViewResolver viewresolver = new ThymeleafViewResolver();
		viewresolver.setTemplateEngine(getTemplateEngine());
		viewresolver.setOrder(0);
		viewresolver.setViewNames(new String[] { "templates/*" });

		return viewresolver;
	}

	/*
	 * Spring MVC view resolver
	 * =========================================================================
	 */

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver internalViewResolver = new InternalResourceViewResolver();
		// internalViewResolver.setPrefix("/WEB-INF/views/");
		internalViewResolver.setPrefix("classpath:/templates/");
		internalViewResolver.setSuffix(".html");
		internalViewResolver.setOrder(1);

		return internalViewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	/*
	 * It18 configuration
	 * =========================================================================
	 */
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("i18n/messages");
		return messageSource;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieName("lang");
		localeResolver.setDefaultLocale(new Locale("fr"));
		return localeResolver;
	}

}
