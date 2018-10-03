package com.csdn.demospringboot.config.websocket;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.websocket.server.ServerEndpointConfig;
//为服务端可以注入其他类实现配置
@Configuration
public class EndpointConfigure extends ServerEndpointConfig.Configurator implements ApplicationContextAware
{
    private static volatile BeanFactory context;
@Override
public <T> T getEndpointInstance(Class<T> clazz)throws InstantiationException {
    return context.getBean(clazz);	}
@Override
public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
EndpointConfigure.context = applicationContext;	}
}


