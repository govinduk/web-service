package org.github.kolorobot.ws.rest;

import org.github.kolorobot.domain.Audit;
import org.github.kolorobot.domain.Incident;
import org.github.kolorobot.domain.Incidents;
import org.github.kolorobot.service.IncidentManager;
import org.github.kolorobot.service.IncidentManagerFactory;
import org.github.kolorobot.ws.soap.IncidentManagementException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/incident")
public class IncidentManagementService {

	private static final IncidentManager incidentManager = IncidentManagerFactory.getIncidentManager();

	@Path("/")
	@GET
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Incidents getAll() {
		return new Incidents(incidentManager.getIncidents());
	}

	@Path("/")
	@PUT
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Incident add(@QueryParam(value = "title") String title, @QueryParam(value = "description") String description, @QueryParam(value = "address") String address) {
		return incidentManager.save(new Incident(title, description, address));
	}

	@Path("/{incidentId}")
	@POST
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Incident edit(@PathParam("incidentId") Long incidentId, @QueryParam(value = "title") String title, @QueryParam(value = "description") String description, @QueryParam(value = "address") String address) {
		Incident incident = getIncident(incidentId);
		incident.setTitle(title);
		incident.setDescription(description);
		incident.setAddress(address);
		return incidentManager.save(incident);
	}

	@Path("/{incidentId}")
	@GET
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Incident getOne(@PathParam("incidentId") Long incidentId) {
		return getIncident(incidentId);
	}

	@Path("/{incidentId}")
	@DELETE
	public void delete(@PathParam("incidentId") Long incidentId) {
		Incident incident = getIncident(incidentId);
		incidentManager.delete(incident.getId());
	}

	private Incident getIncident(Long incidentId) {
		Incident incident = incidentManager.getIncident(incidentId);
		if (incident.isEmpty()) {
			String message = "No incident with id " + incidentId + " was found!";
			Response response = Response.status(Response.Status.BAD_REQUEST).entity(new Error(message)).build();
			throw new WebApplicationException(response);
		}
		return incident;
	}

	//
	// Audit
	//

	@Path("/{incidentId}/audit")
	@PUT
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Audit addAudit(@PathParam("incidentId") Long incidentId, @QueryParam(value = "description") String description, @QueryParam(value = "newStatus") Incident.Status newStatus) {
		Incident incident = getIncident(incidentId);
		return incidentManager.addAudit(incident, description, newStatus);
	}

	@Path("/{incidentId}/audit")
	@GET
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Audit> getAllAudits(@PathParam(value = "incidentId") Long incidentId) {
		return incidentManager.getAudits(incidentId);
	}

	@Path("/{incidentId}/audit/{auditId}")
	@GET
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Audit getOneAudit(@PathParam(value = "incidentId") Long incidentId, @PathParam(value = "auditId") Long auditId) {
		Audit audit = incidentManager.getAudit(incidentId, auditId);
		if (audit.isEmpty()) {
			String message = "No audit with id " + auditId + " for incident with id " + incidentId + " exists!";
			Response response = Response.status(Response.Status.BAD_REQUEST).entity(new Error(message)).build();
			throw new WebApplicationException(response);
		}
		return audit;
	}
}