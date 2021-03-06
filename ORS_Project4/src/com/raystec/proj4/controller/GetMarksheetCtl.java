package com.raystec.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.raystec.proj4.bean.BaseBean;
import com.raystec.proj4.bean.MarksheetBean;
import com.raystec.proj4.exception.ApplicationException;
import com.raystec.proj4.model.MarksheetModel;
import com.raystec.proj4.util.DataValidator;
import com.raystec.proj4.util.Datautility;
import com.raystec.proj4.util.PropertyReader;
import com.raystec.proj4.util.ServletUtility;

/**
 * Servlet implementation class GetMarksheetCtl
 */
@ WebServlet(name="GetMarksheetCtl",urlPatterns={"/ctl/GetMarksheetCtl.do"})
public class GetMarksheetCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(GetMarksheetCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("GetMarksheetCTL Method validate Started");
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo",
					PropertyReader.getValue("error.require", "Roll Number"));
			pass = false;
		}
		log.debug("GetMarksheetCTL Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("GetMarksheetCtl Method populatebean Started");
		MarksheetBean bean = new MarksheetBean();
		bean.setId(Datautility.getLong(request.getParameter("id")));
		bean.setRollNo(Datautility.getString(request.getParameter("rollNo")));
		bean.setName(Datautility.getString(request.getParameter("name")));
		bean.setPhysics(Datautility.getInt(request.getParameter("physics")));
		bean.setChemistry(Datautility.getInt(request.getParameter("chemistry")));
		bean.setMaths(Datautility.getInt(request.getParameter("maths")));
		log.debug("GetMarksheetCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	       log.debug("GetMarksheetCtl Method doGet Started");
	        String op = Datautility.getString(request.getParameter("operation"));

	        // get model
	        MarksheetModel model = new MarksheetModel();

	        MarksheetBean bean = (MarksheetBean) populateBean(request);

	        long id = Datautility.getLong(request.getParameter("id"));

	        if (OP_GO.equalsIgnoreCase(op)) {

	            try {
	                bean = model.findByRollNo(bean.getRollNo());
	                if (bean != null) {
	                    ServletUtility.setBean(bean, request);
	                } else {
	                    ServletUtility.setErrorMessage("RollNo Does Not exists",
	                            request);
	                }
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }

	        }
	        ServletUtility.forward(getView(), request, response);
	        log.debug("MarksheetCtl Method doGet Ended");
	    }


	@Override
	protected String getView() {
		return ORSView.GET_MARKSHEET_VIEW;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetMarksheetCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		  ServletUtility.forward(getView(), request, response);
	}

}
