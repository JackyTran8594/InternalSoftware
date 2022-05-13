package com.ansv.internalsoftware.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Slf4j
@Component
public class EndpointsListener implements ApplicationListener<ContextRefreshedEvent> {


    /// listen any endpoint when working with REST Api
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods().forEach((key,item) -> {
            log.info("--------------RequestMappingHandlerMapping {}: {}", key.toString(), item.toString());
        });

    }
}
