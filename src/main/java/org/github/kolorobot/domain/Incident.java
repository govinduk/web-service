package org.github.kolorobot.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "incident")
@XmlRootElement
@XmlType(name = "", namespace = "")
@XmlAccessorType(XmlAccessType.FIELD)
public class Incident {

	public static final Incident EMPTY = new Incident();

	public enum Status {
		NEW, CONFIRMED, NOT_CONFIRMED, SOLVED, CLOSED;
	}

	public Incident() {
	}

	public Incident(String title, String description, String address) {
		this.created = new Date();
		this.status = Status.NEW;
		this.title = title;
		this.description = description;
		this.address = address;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "title")
	@NotBlank
	@Size(max = 50)
	private String title;
	
	@Column
	@Size(max = 255)
	private String address;
	
	@NotBlank
	@Size(max = 255)
	private String description;
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	private Status status;
	
	@OneToMany(mappedBy = "incident", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy(value = "created DESC")
	@XmlTransient
	private List<Audit> audits;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Audit> getAudits() {
		if (audits == null) {
			audits = new ArrayList<Audit>();
		}
		return audits;
	}

	public void setAudits(List<Audit> audits) {
		this.audits = audits;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void addAudit(Audit audit) {
		setStatus(audit.getStatus());
		getAudits().add(audit);
		audit.setIncident(this);
	}

	public boolean isEmpty() {
		return this.equals(EMPTY);
	}

}
