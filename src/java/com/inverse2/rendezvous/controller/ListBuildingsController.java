package com.inverse2.rendezvous.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.inverse2.rendezvous.util.HibernateUtil;

public class ListBuildingsController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    List buildingList = HibernateUtil.getListFromQuery("from Building");
		return(new ModelAndView("listbuildings", "buildingsList", buildingList));
	}
	
}
