package com.inverse2.rendezvous.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.inverse2.rendezvous.model.User;
import com.inverse2.rendezvous.security.ApplicationSecurityManager;

public class HttpRequestInterceptor extends HandlerInterceptorAdapter {

    private String signInPage;
    private ApplicationSecurityManager applicationSecurityManager;

    /**
     * Uses ApplicationSecurityManager to ensure user is logged in; if not,
     * then user is forwarded to the sign-in page.
     * @see ApplicationSecurityManager
     */

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user = applicationSecurityManager.getUser(request);

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

    public ApplicationSecurityManager getApplicationSecurityManager() {
        return(applicationSecurityManager);
    }

    public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
        this.applicationSecurityManager = applicationSecurityManager;
    }

}
