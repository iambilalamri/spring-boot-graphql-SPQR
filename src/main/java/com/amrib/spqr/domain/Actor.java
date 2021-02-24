package com.amrib.spqr.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Actor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer actorId;
	private String firstname;
	private String lastname;
	private Date dob;
	private String address;
	private Integer filmId;

	public Actor(String firstname, String lastname, Date dob, String address, Integer filmId) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.address = address;
		this.filmId = filmId;

	}
}
