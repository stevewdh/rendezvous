package com.inverse2.rendezvous.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.inverse2.rendezvous.context.ApplicationContextManager;
import com.inverse2.rendezvous.model.User;

public class HttpRequestInterceptor extends HandlerInterceptorAdapter {

    private String signInPage;
    private ApplicationContextManager applicationContextManager;

    /**
     * Uses ApplicationSecurityManager to ensure user is logged in; if not,
     * then user is forwarded to the sign-in page.
     * @see ApplicationContextManager
     */

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user = applicationContextManager.getUser(request);

        if (user == null) {
            response.sendRedirect(this.signInPage);
            return(false);
        }

        return(true);
    }

    public String getSignInPage() {
        return(signInPage);
    }

    public void setSignInPage(String signInPage) {
        this.signInPage = signInPage;
    }

    public ApplicationContextManager getApplicationContextManager() {
        return(applicationContextManager);
    }

    public void setApplicationContextManager(ApplicationContextManager applicationContextManager) {
        this.applicationContextManager = applicationContextManager;
    }

}
