package org.github.kolorobot.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;


@Entity
@Table(name = "audit")
@XmlRootElement
@XmlType(name = "", namespace = "")
public class Audit {

	public static final Audit EMPTY = new Audit();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "incident_id")
	private Incident incident;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date created;
	
	@NotBlank
	private String description;
	
	@NotNull
	private Incident.Status status;
		
	@Column(name = "previous_status")
	private Incident.Status previousStatus;

	public Audit(String description, Incident.Status newStatus) {
		this.created = new Date();
		this.description = description;
		this.status = newStatus;
	}

	public Audit() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Incident.Status getStatus() {
		return status;
	}

	public void setStatus(Incident.Status status) {
		this.status = status;
	}

	public Incident.Status getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(Incident.Status previousStatus) {
		this.previousStatus = previousStatus;
	}

	public boolean isEmpty() {
		return this.equals(EMPTY);
	}
}
