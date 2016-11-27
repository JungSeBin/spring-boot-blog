package com.seb.blog.listener;

import com.seb.blog.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@RequiredArgsConstructor
public class SessionListener implements HttpSessionListener {
    private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);

    private final SessionService sessionService;

    public void sessionCreated(HttpSessionEvent event) {
        logger.info("create: {}", event.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        logger.info("destroy: {}" ,event.getSession().getId());
        sessionService.removeSession(event.getSession().getId());
    }
}
