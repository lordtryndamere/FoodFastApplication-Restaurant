package com.liondevs.fastfood.event;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.EventObject;

@Component
@Slf4j
public class EventListener {
    @org.springframework.context.event.EventListener //eventListener en java
    public void listen(EventObject e){
        log.info("----EVENT-HANDLE-START---");
        log.info("Event received : {}",e.getClass().getName());
        log.info("Event received : {}",e.toString());
        log.info("----EVENT-HANDLE-FINISH---");
    }
}
