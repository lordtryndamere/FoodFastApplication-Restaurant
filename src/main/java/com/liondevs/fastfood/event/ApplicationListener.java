package com.liondevs.fastfood.event;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;
//Son eventos que escuchan sucesos de la aplicacion
@Component
@Slf4j

public class ApplicationListener implements org.springframework.context.ApplicationListener<ServletRequestHandledEvent> {
    @Override
    public void onApplicationEvent(ServletRequestHandledEvent event){
        log.info("REQUEST RECEIVED :{}",event);
    }
}
