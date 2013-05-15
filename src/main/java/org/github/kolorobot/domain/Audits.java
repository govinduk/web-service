package org.github.kolorobot.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement
@XmlType(name = "", namespace = "")
@XmlAccessorType(XmlAccessType.FIELD)
public class Audits {

	private List<Audit> audits;

	public Audits() {
	}

	public Audits(List<Audit> audits) {
		this.audits = audits;
	}

	public List<Audit> getAudits() {
		return audits;
	}

	public void setAudits(List<Audit> audits) {
		this.audits = audits;
	}
}
