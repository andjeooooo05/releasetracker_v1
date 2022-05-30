package com.assignmentNeon.releaseTracker.releasetracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "valid_statuses")
public class ValidStatus {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;

	public ValidStatus() {

	}

	public ValidStatus(String name) {
		this.name = name;
	}
	public ValidStatus(String name,int id) {
		this.name = name;
		this.id = id;
	}
	public ValidStatus(int id) {
	
		this.id = id;
	}
	
	
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

	@Override
	public String toString() {
		return "ValidStatus [id=" + id + ", name=" + name + "]";
	}

}
