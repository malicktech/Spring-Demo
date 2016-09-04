package org.jdw.blog.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Extend and include {@link PropertySourcesPlaceholderConfigurer} beans in Configuration classes.
 */
public abstract class PropertySourcePlaceholderAbstractConfig {

    protected static PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceholderConfigurer(String... propertiesLocations) {
        final Resource[] resources = new Resource[propertiesLocations.length];
        for (int i = 0; i < propertiesLocations.length; i++) {
            String propertiesLocation = propertiesLocations[i];
            resources[i] = new ClassPathResource(propertiesLocation);
        }
        // check if yml or classic properties file
        if (propertiesLocations[0].endsWith (".yml")){
         System.out.println("Malick : " + "yml file");
            return createYamlPropertySourcesPlaceholderConfigurer(resources);
        }
        return createPropertySourcesPlaceholderConfigurer(resources);
    }

    /**
     * how to tell the Spring Framework context how to load the YAML file:
     * @param resources
     * @return
     */
    public static PropertySourcesPlaceholderConfigurer createYamlPropertySourcesPlaceholderConfigurer(Resource... resources) {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        // just to load yml file
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        // load and espose YML file in env
        YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();

        factory.setResources(resources);
        System.out.println("Malick : " + factory.getObject().getProperty("spring.jpa.database"));
        propertySourcesPlaceholderConfigurer.setProperties(factory.getObject());
        return propertySourcesPlaceholderConfigurer;
    }

    protected static PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceholderConfigurer(Resource... resources) {
        final PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
        ppc.setIgnoreUnresolvablePlaceholders(true);
        ppc.setIgnoreResourceNotFound(false);
        ppc.setLocations(resources);
        return ppc;
    }

}
