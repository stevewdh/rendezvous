package com.inverse2.rendezvous.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.inverse2.rendezvous.model.BuildingList;
import com.inverse2.rendezvous.util.ToasterServiceHelper;

public class ListBuildingsController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    BuildingList buildingList = (BuildingList) ToasterServiceHelper.getEntity("building/getActiveBuildingList", BuildingList.class);
		return(new ModelAndView("listbuildings", "buildingList", buildingList));
	}
	
}
