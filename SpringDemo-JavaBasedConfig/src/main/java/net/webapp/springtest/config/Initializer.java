package net.webapp.springtest.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Interface to be implemented in Servlet 3.0+ environments in order to configure the ServletContext programmatically
 * 
 * configures the ServletContext ,DispatcherServlet remplace web.xml
 * 
 * Thereby allowing the web.xml file to be removed from modern Spring MVC applications. implementation of
 * WebApplicationInitializer
 * 
 * WebApplicationContext = spring container build an instance of the Spring container in the WebApplicationInitializer
 * 
 * DispatcherServlet served as front controller in Spring MVC webapp DispatcherServlet registered with the Web container
 * without web.xm : with the WebApplicationInitializer
 * 
 */

public class Initializer implements WebApplicationInitializer {

	/* onStartup() method, to add DispatcherServlet to ServletContext. */
	// gets invoked automatically when application starts up
	public void onStartup(ServletContext container) throws ServletException {

		// Create the 'root' Spring application context (i.e instance of the WebApplicationContext / Spring container)
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		// rootContext.setConfigLocation("net.webapp.springtest.config");
		// rootContext.register(ServiceConfig.class, DataBaseConfig.class, SecurityConfig.class);
		rootContext.register(MvcConfig.class);

		// Manage the lifecycle of the root application context
		// container.addListener(new ContextLoaderListener(rootContext));

		// provide the container to the DispatcherServlet.
		// Register and map the dispatcher servlet
		// DispatcherServlet is created and initialized with WebApplicationContext we have created,
		// and it's mapped to "/*" URLs and set to eagerly load on application startup
		// Add the servlet mapping manually and make it initialize automatically
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(rootContext));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);

		// Spring Framework Web MVC applications can now rid themselves of web.xml.

	}

}
