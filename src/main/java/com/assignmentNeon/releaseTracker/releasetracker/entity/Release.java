package com.assignmentNeon.releaseTracker.releasetracker.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "release_tracker")
public class Release {

	// define fields

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	
	@Column(name = "status")
	private int status;

	@Column(name = "release_date")
	private Timestamp releaseDate;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "last_update_at")
	private Timestamp lastUpdateAt;
	
	
	
	

	// define constructors

	public Release() {

	}
/*
	public Release(int id, String name, String description, String status, Date releaseDate, Date createdAt,
			Date lastUpdateAt) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.releaseDate = releaseDate;
		this.createdAt = createdAt;
		this.lastUpdateAt = lastUpdateAt;

	}
*/
	public Release(String name, String description, int status, Timestamp releaseDate, Timestamp createdAt,
			Timestamp lastUpdateAt) {

		this.name = name;
		this.description = description;
		this.status = status;
		this.releaseDate = releaseDate;
		this.createdAt = createdAt;
		this.lastUpdateAt = lastUpdateAt;

	}
	
	public Release(int id, String name, String description, int status, Timestamp releaseDate, Timestamp createdAt,
			Timestamp lastUpdateAt) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
	this.status = status;
	this.releaseDate = releaseDate;
	this.createdAt = createdAt;
	this.lastUpdateAt = lastUpdateAt;
}
	// define getter/setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Timestamp releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getLastUpdateAt() {
		return lastUpdateAt;
	}

	public void setLastUpdateAt(Timestamp lastUpdateAt) {
		this.lastUpdateAt = lastUpdateAt;
	}

	// define tostring
	@Override
	public String toString() {
		return "Release [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", releaseDate=" + releaseDate + ", createdAt=" + createdAt + ", lastUpdateAt=" + lastUpdateAt + "]";
	}

}
