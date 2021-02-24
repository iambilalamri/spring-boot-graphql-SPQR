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
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer filmId;
	private String name;
	private Date launchDate;

	public Film(String name, Date launchDate) {
		super();
		this.name = name;
		this.launchDate = launchDate;
	}
}
