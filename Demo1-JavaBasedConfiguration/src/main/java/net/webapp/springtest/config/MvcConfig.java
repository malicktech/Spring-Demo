/**
 * Copyright 2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package net.webapp.springtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * equivalent of servlet-context
 * 
 * @EnableWebMvc, enables Spring MVC
 * WebMvcConfigurerAdapter, which provides empty methods that can be overridden to customize default configuration of Spring MVC
 * Configuration of the business, persistence and security layers.
 * 
 */

@Configuration
@EnableWebMvc
//@Import({DataBaseConfig.class})
//@Import({DataBaseConfig.class, ServiceConfig.class, SecurityConfig.class})
@ComponentScan(basePackages = {"net.webapp.springtest" })
public class MvcConfig extends WebMvcConfigurerAdapter {

	//	private static final Logger LOG = LoggerFactory.getLogger(MvcConfig.class);
	
	//TODO set cache period ?? needed ???? 

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        // add a resource handler for /webjars/** to associate it with classpath:/META-INF/resources/webjars/ located 
        // allows  TO specify web libraries (JavaScript, CSS,
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    // It's a performance optimization 
    // tells Spring to use the container's default servlet for certain requests
    // Configures a request handler for serving static resources by forwarding the request to the Servlet container's "default" Servlet.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // Serving static files using the Servlet container's default Servlet.
        configurer.enable();
    }

	/** 
	 * Resolves views selected for rendering by "@Controllers" to .jsp resources in the /WEB-INF/views directory 
	 */
    @Bean
    public InternalResourceViewResolver  setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getMultipartResolver() {
        return new CommonsMultipartResolver();
    }
	// @Bean
	// public void multipartResolver(CommonsMultipartResolver multipartresolver)
	// { // Configuration Upload
	// // taille de la requête, y compris les données
	// multipartresolver.setMaxUploadSize(100000);
	// }
    
     
	 // Application custom initialization code.
	// @PostConstruct
	// public void init() {
	// requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
	//
	// LOG.debug("Looking for Spring profiles...");
	// if (env.getActiveProfiles().length == 0) {
	// LOG.info("No Spring profile configured, running with default
	// configuration.");
	// } else {
	// for (String profile : env.getActiveProfiles()) {
	// LOG.info("Detected Spring profile: {}", profile);
	// }
	// }
	// }
	// @Override
	// public void addFormatters(FormatterRegistry formatterRegistry) {
	// // add your custom formatters
	// }
	//
	// @Override
	// public void addViewControllers(ViewControllerRegistry registry) {
	// registry.addViewController("/about");
	// // the about page did not required any controller
	// }
    
   

}
