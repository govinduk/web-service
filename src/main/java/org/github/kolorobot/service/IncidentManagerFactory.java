package org.github.kolorobot.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IncidentManagerFactory {
	public static IncidentManager getIncidentManager() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.github.kolorobot.config");
		return context.getBean(IncidentManager.class);
	}
}
