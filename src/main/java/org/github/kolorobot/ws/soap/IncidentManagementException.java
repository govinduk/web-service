package org.github.kolorobot.ws.soap;

public class IncidentManagementException extends RuntimeException {
	public IncidentManagementException() {
	}

	public IncidentManagementException(String message) {
		super(message);
	}

	public IncidentManagementException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncidentManagementException(Throwable cause) {
		super(cause);
	}
}
