//package com.revature.backend;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@WebServlet("/MobileServlet")
//public class MobileServlet extends HttpServlet {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	public MobileServlet() {
//        super();}
//
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		
//		// Here we can deliver a response to our get methods
//		// We'll send back some raw html in this case to show the server is talking directly to the client
//		
//		// Set the content type of our return header to html
//		request.getRequestDispatcher("mobilebanking.jsp").forward(request, response);
//
//}}
