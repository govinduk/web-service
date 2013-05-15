package org.github.kolorobot.repository;

import org.github.kolorobot.domain.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditRepository extends JpaRepository<Audit, Long> {

	List<Audit> findAllByIncidentId(Long incidentId);

	Audit findOneByIncidentIdAndId(Long incidentId, Long auditId);
}
