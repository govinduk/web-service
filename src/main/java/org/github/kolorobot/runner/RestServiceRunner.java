package org.github.kolorobot.runner;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

public class RestServiceRunner implements ServiceRunner {

	private HttpServer httpServer;

	@Override
	public void start() throws Exception {

		System.out.println(RestServiceRunner.class.getSimpleName() + " is starting");

		String address = "http://localhost:9998/";
		ResourceConfig rc = new PackagesResourceConfig("org.github.kolorobot");

		httpServer = HttpServerFactory.create(address, rc);
		httpServer.start();

		System.out.println("Server running " + address);
		System.out.println("WADL published: http://localhost:9998/application.wadl");
	}

	@Override
	public void stop() {
		httpServer.stop(0);
	}
}