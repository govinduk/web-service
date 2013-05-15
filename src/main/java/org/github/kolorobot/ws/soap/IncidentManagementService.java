package org.github.kolorobot.ws.soap;

import org.github.kolorobot.domain.Audit;
import org.github.kolorobot.domain.Incident;
import org.github.kolorobot.service.IncidentManager;
import org.github.kolorobot.service.IncidentManagerFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class IncidentManagementService {

	private static final IncidentManager incidentManager = IncidentManagerFactory.getIncidentManager();

	@WebMethod
	public List<Incident> getIncidents() {
		return incidentManager.getIncidents();
	}

	@WebMethod
	public Long addIncident(@WebParam(name = "title") String title, @WebParam(name = "description") String description, @WebParam(name = "address") String address) {
		return incidentManager.save(new Incident(title, description, address)).getId();
	}

	@WebMethod
	public Incident getIncident(@WebParam(name = "incidentId") Long incidentId) {
		Incident incident = incidentManager.getIncident(incidentId);
		if (incident.isEmpty()) {
			throw new IncidentManagementException("No incident with id " + incidentId + " was found!");
		}
		return incident;
	}

	@WebMethod
	public void deleteIncident(@WebParam(name = "incidentId") Long incidentId) {
		Incident incident = incidentManager.getIncident(incidentId);
		if (incident.isEmpty()) {
			throw new IncidentManagementException("No incident with id " + incidentId + " was found!");
		}
		incidentManager.delete(incident.getId());
	}

	@WebMethod
	public Long addAudit(@WebParam(name = "incidentId") Long incidentId, @WebParam(name = "description") String description, @WebParam(name = "newStatus") Incident.Status newStatus) {
		Incident incident = getIncident(incidentId);
		return incidentManager.addAudit(incident, description, newStatus).getId();
	}

	@WebMethod
	public List<Audit> getAudits(@WebParam(name = "incidentId") Long incidentId) {
		return incidentManager.getAudits(incidentId);
	}

	@WebMethod
	public Audit getAudit(@WebParam(name = "incidentId") Long incidentId, @WebParam(name = "auditId") Long auditId) {
		Audit audit = incidentManager.getAudit(incidentId, auditId);
		if (audit.isEmpty()) {
			throw new IncidentManagementException("No audit with id " + auditId + " for incident with id " + incidentId + " exists!");
		}
		return audit;
	}

}
