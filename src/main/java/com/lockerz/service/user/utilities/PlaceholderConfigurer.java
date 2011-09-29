package com.lockerz.service.user.utilities;

import java.io.IOException;
import java.util.Properties;
import org.springframework.core.io.Resource;
import org.springframework.beans.BeansException;
import org.springframework.core.io.ResourceLoader;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class PlaceholderConfigurer extends PropertyPlaceholderConfigurer implements FactoryBean<Properties> {
	
	// need this
	public static final String HANDLE = "user";
	
	// need these for storing defaults
    private static final String PROPERTY_KEY = "slug";
    private static final String DEFAULT_CONFIG = "slug";
    private static final String FALLBACK_CONFIG = "classpath:" + HANDLE + ".properties";

    // need this
    private final ResourceLoader loader = new DefaultResourceLoader();

    // need this
    private Properties properties;
        
    public PlaceholderConfigurer() throws IOException {
    	// set the location here
        this.setLocation(this.getDefaultLocation());
    }
    
    protected Resource getDefaultLocation() throws IOException {
    	// need this
        Resource config;
        // need this
        String configFile = System.getProperty(PROPERTY_KEY);
        // sanity check
        if (configFile == null) {
        	// set the configuration file here
            configFile = System.getenv(PROPERTY_KEY);
        }
        // sanity check
        if (configFile != null) {
        	// set the configuration file here
            config = loader.getResource(configFile);
        } else {
            // set the configuration file here
            config = loader.getResource(DEFAULT_CONFIG);
        } 
        // sanity check
        if (!config.exists()) {               
            // set the configuration file here
            config = loader.getResource(FALLBACK_CONFIG);
        }
        // return here
        return config;
    }
    
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        // call parent here
    	super.processProperties(beanFactory, props);
        // store a copy of the property, so the bean can also be used to get the whole thing.
        this.properties = props;
    }

   // @Override
    public Properties getObject() throws Exception {
    	// return here
        return this.properties;
    }

    //@Override
    public Class<?> getObjectType() {
    	// return here
        return Properties.class;
    }

   // @Override
    public boolean isSingleton() {
    	// return here
        return true;
    }
}
