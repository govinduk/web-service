package org.github.kolorobot.runner;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		ServiceRunner runner = null;

		System.out.println("Please select a service type to run and press <Enter>:");
		System.out.println("1: WSDL/SOAP");
		System.out.println("2: WADL/REST");

		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		System.out.println("Initializing... Please wait...");
		if (input.startsWith("1")) {
			runner = new Soap11ServiceRunner();
		}

		if (input.startsWith("2")) {
			runner = new RestServiceRunner();
		}

		runner.start();

		System.out.println("Press <Enter> to stop...");
		System.in.read();
		runner.stop();
		System.exit(0);
	}
}
