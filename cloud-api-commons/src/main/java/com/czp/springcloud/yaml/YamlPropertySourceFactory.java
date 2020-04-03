package com.czp.springcloud.yaml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-17 9:45:59
 * @description : 自定义PropertySourceFactory，实现PropertySource注解读取yml文件
 */
@Component
public class YamlPropertySourceFactory implements PropertySourceFactory {

	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
		Properties yamlProperties = loadYamlProperties(resource);
		String sourceName = name != null ? name : resource.getResource().getFilename();
		assert sourceName != null;
		return new PropertiesPropertySource(sourceName, yamlProperties);

	}

	private Properties loadYamlProperties(EncodedResource resource) throws FileNotFoundException {
		try {
			YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
			factoryBean.setResources(resource.getResource());
			factoryBean.afterPropertiesSet();
			return factoryBean.getObject();
		} catch (IllegalStateException e) {
			// for ignoreResourceNotFound
			Throwable cause = e.getCause();
			if (cause instanceof FileNotFoundException) {
				throw (FileNotFoundException) e.getCause();
			}
			throw e;
		}
	}
}
