package com.generic.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class to test the generics 
 * 
 * @author dgunasek
 *
 */
public class Main {
	
	public static void main(String[] args) {
		
		Logger.println(Main.class, "In main method");
		MyRequestHandler myRequestHandler = new MyRequestHandler();
		myRequestHandler.registerlAvailableHandlers();
		
		Customer cus = new Customer("SomeBody", "Nobody", 30);	// create new customer object
		Request<Customer> cusReq= new Request<Customer>();		// Create new request object of customer Erasure
		cusReq.setT(cus);										// set the created customer object in the request
		cusReq.setType("Customer");								// set the type of request 
		
		myRequestHandler.handleRequest(cusReq);					// calling the request handler to assign the request to appropriate handlers
		
		
		Logger.println(Main.class, "Exiting main method");
	}
}

/**
 * 
 * @author dgunasek
 *
 */
class MyRequestHandler 	{
	
	private List<RequestHandler> myHandlers = new ArrayList<RequestHandler>();
	
	
	public <T> void handleRequest(Request<T> request)	{
		
		for (RequestHandler requestHandler : myHandlers) {
			if(requestHandler.isHandler(request.getType())){
				requestHandler.handle(request);
			}
		}
	}
	
	/**
	 * Register all your request handlers using this method
	 * @return
	 */
	public boolean registerlAvailableHandlers()	{
		
		try {
			this.myHandlers.add(new CustomerHandler());
			//add as mush as request handlers as required
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
