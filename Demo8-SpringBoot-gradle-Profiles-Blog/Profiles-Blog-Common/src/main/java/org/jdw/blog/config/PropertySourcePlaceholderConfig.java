package org.jdw.blog.config;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
//@PropertySource({"common_application.properties"})
public class PropertySourcePlaceholderConfig extends PropertySourcePlaceholderAbstractConfig {

	/*
     * PropertySourcesPlaceHolderConfigurer Bean only required for @Value("{}") annotations.
     * Remove this bean if you are not using @Value annotations for injecting properties.
     * 
     * with spring boot auto detect application.config class : no need to precize the two 
     * Spring Boot already provides that support out of the box. Only adding a @PropertySource 
     */
	
    @Bean
    @Profile(Constants.TEST)
    public static PropertySourcesPlaceholderConfigurer testProperties() {
        return createPropertySourcesPlaceholderConfigurer("common_application.properties","common_test_application.properties");
    }

    @Bean
    @Profile(Constants.DEV)
    public static PropertySourcesPlaceholderConfigurer devProperties() {
        // return createPropertySourcesPlaceholderConfigurer("common_application.yml"); // TODO delete : not work
        return createPropertySourcesPlaceholderConfigurer("common_application.properties", "common_dev_application.properties");
    }


    @Bean
    @Profile(Constants.PROD)
    public static PropertySourcesPlaceholderConfigurer prodProperties() {
        return createPropertySourcesPlaceholderConfigurer("common_application.properties", "common_prod_application.properties");
    }



    /**
     * used to expose YAML as a PropertySource in the Spring Environment
     * simply provided the loader is not enough to expose the loaded data to the context.
     * TODO : delete -> not work
     */
    /*
    @Bean
    public PropertySource<?> yamlPropertySourceLoader() throws IOException {
        System.out.println("Malick : " + "yamlPropertySourceLoader config ...");
        YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
        PropertySource<?> applicationYamlPropertySource = loader.load("common_application.yml",
                new ClassPathResource("common_application.yml"), "dev");
        System.out.println("Malick : " + applicationYamlPropertySource.getProperty("spring.jpa.hibernate.naming-strategy"));
        return applicationYamlPropertySource;
    }
    */


}
