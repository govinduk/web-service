package org.github.kolorobot.runner;

import org.github.kolorobot.ws.soap.IncidentManagementService;

import javax.xml.ws.Endpoint;

public class Soap11ServiceRunner implements ServiceRunner {

	IncidentManagementService service = new IncidentManagementService();

	@Override
	public void start() throws Exception {

		System.out.println(Soap11ServiceRunner.class.getSimpleName() + " is starting");

		String address = "http://localhost:9000/ws";
		Endpoint.publish(address, service);

		System.out.println("Server running at: " + address);
		System.out.println("WSDL published at: " + address + "?wsdl");
	}

	@Override
	public void stop() {

	}
}