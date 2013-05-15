package org.github.kolorobot.domain;

import org.github.kolorobot.domain.Incident;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlType(name = "", namespace = "")
@XmlAccessorType(XmlAccessType.FIELD)
public class Incidents {

	private List<Incident> incidents;

	public Incidents() {
	}

	public Incidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}
}
