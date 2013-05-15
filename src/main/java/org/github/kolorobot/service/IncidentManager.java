package org.github.kolorobot.service;

import org.github.kolorobot.domain.Audit;
import org.github.kolorobot.domain.Incident;
import org.github.kolorobot.repository.AuditRepository;
import org.github.kolorobot.repository.IncidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Service
public class IncidentManager {
	
	@Inject
	private IncidentRepository incidentRepository;

	@Inject
	private AuditRepository auditRepository;
	
	public List<Incident> getIncidents() {
		List<Incident> incidents = incidentRepository.findAll();
		if (incidents == null) {
			return Collections.emptyList();
		}
		return incidents;
	}
	
	public Incident getIncident(Long incidentId) {
		Incident incident = incidentRepository.findOne(incidentId);
		if (incident == null) {
			return Incident.EMPTY;
		}
		return incident;
	}
	
	@Transactional
	public Incident save(Incident incident)  {
		return incidentRepository.save(incident);
	}

	public List<Audit> getAudits(Long incidentId) {
		List<Audit> audits = auditRepository.findAllByIncidentId(incidentId);
		if (audits == null) {
			return Collections.emptyList();
		} else {
			return audits;
		}
	}

	public Audit getAudit(Long incidentId, Long auditId) {
		Audit audit = auditRepository.findOneByIncidentIdAndId(incidentId, auditId);
		if (audit == null) {
			return Audit.EMPTY;
		}
		return audit;
	}

	@Transactional
	public Audit addAudit(Incident incident, String description, Incident.Status newStatus) {
		incident.addAudit(new Audit(description, newStatus));
		return new Audit(description, newStatus);
	}

	@Transactional
	public void delete(Long incidentId) {
		incidentRepository.delete(incidentId);
	}


}
